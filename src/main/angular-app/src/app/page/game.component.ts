import { Component } from "@angular/core";
import { GameService } from "../service/game.service";
import { Game } from "../model/game";
import { Attempt } from "../model/attempt";
import { Check } from "../model/check";
import { Player } from "../model/player";

//интерфейс для отображения попыток с результатами игрока
interface IAttempt{
  guess: number;
  bulls: number;
  cows: number;
}

@Component({
  selector: 'game-comp',
  templateUrl: 'game.component.html',
  styleUrls: ['game.component.css'],
  providers: [GameService]
})
export class GameComponent{
  game: Game = new Game(null, 0, 0, 0);
  player: Player = new Player(0, "", "");
  gameId: number = 0;
  attemptId: number = 0;
  answer: number = 0;
  guess: number = 0;
  checkRes: Check = new Check(0, 0);
  showInput: boolean = false;
  logged: boolean = false;
  wrongPassword: boolean = false;
  attemptArr: IAttempt[] = [];
  gameArr: Game[] = [];
  startTime: number = 0;
  endTime: number = 0;
  limitTime: boolean = false;
  timeOut: boolean = false;
  timeLimits: number[] = [];
  amountTime: number = 0;
  timer: any = null;
  limitAttempts: boolean = false;
  attemptsOut: boolean = false;
  attemptLimits: number[] = [];
  amountAttempts: number = 0;

  constructor(private gameService: GameService){}

  //загрузка ограничений из конфигурационного файла с сервера
  ngOnInit(){
    this.getLimits();
  }

  getLimits(){
    this.gameService.getAttemptsLimit()
    .subscribe((data:any) => {
      this.attemptLimits = data;
      //вариант 0 - игра без ограничений и значение по умолчанию
      this.attemptLimits.push(0);
    })
    this.gameService.getTimeLimit()
    .subscribe((data:any) => {
      this.timeLimits = data;
      this.timeLimits.push(0);
    })
  }

  savePlayer(){
    this.gameService.savePlayer(this.player)
    .subscribe((data: any) => {
      this.player.id = data;
      //новый пользователь может начинать игру
      this.logged = true;
      this.game.player = this.player.id;
    })
  }

  checkPassword(){
    this.gameService.findByNicknameAndPassword(this.player.nickname, this.player.password)
    .subscribe((data: any) => {
      this.player.id = data;
      if(this.player.id === 0){
        //id равен 0, если пользователь ошибся в пароле
        this.wrongPassword = true;
      }
      else{
        //иначе пользователь авторизован
        this.logged = true;
        this.game.player = this.player.id;
        this.wrongPassword = false;
      }
    })
  }

  authorize(){
    this.gameService.findByNickname(this.player.nickname)
    .subscribe((data: number) => {
      this.player.id = data;
      if(this.player.id === 0){
        //новый пользователь сохраняется в базу
        this.savePlayer();
      }
      else{
        //иначе проверяется на соответствие пароля
        this.checkPassword();
      }
    })
  }

  generateNumber(){
    //получение загаданного числа
    this.gameService.generateNumber()
    .subscribe((data: any) => {
      this.answer = data;
    })
  }

  saveGame(){
    this.gameService.saveGame(this.game)
    .subscribe((data: any) => {
      this.gameId = data;
      this.game.id = this.gameId;
    })
  }

  startTimer(){
    this.timer = setInterval(() => {
      if(this.amountTime > 0){
        this.amountTime--;
      }
      else{
        this.pauseTimer();
      }
    }, 1000)
  }

  pauseTimer(){
    clearInterval(this.timer);
  }

  start(){
    this.game.id = null;
    this.attemptArr = [];
    this.gameArr = [];
    if(this.amountTime !== 0){
      this.limitTime = true;
      //таймер, если есть ограничение по времени
      this.startTimer();
    } else this.limitTime = false;
    if(this.amountAttempts !== 0){
      this.limitAttempts = true;
    }
    else{
      this.limitAttempts = false;
    }
    this.generateNumber();
    this.saveGame();
    //появляется форма для ввода чисел
    this.showInput = true;
    this.startTime = (new Date()).getTime();
  }

  saveAttempt(){
    if(this.limitAttempts) this.amountAttempts--;
    this.endTime = (new Date()).getTime();
    let attempt: Attempt = new Attempt(null, this.gameId, this.guess, (Math.floor(this.endTime - this.startTime)/1000));
    //попытка сохраняется в массив для отображения на странице
    let iattempt: IAttempt = {
      guess: this.guess,
      bulls: this.checkRes.bulls,
      cows: this.checkRes.cows
    }
    this.attemptArr.push(iattempt);
    this.gameService.saveAttempt(attempt)
    .subscribe((data:any) => {this.attemptId = data;});
  }

  updateGame(){
    this.game.attempts = this.attemptArr.length;
    //когда пользователь отгадывает число, рассчитывается итоговое время
    this.game.time = (Math.floor(this.endTime - this.startTime)/1000);
    this.gameService.saveGame(this.game)
    .subscribe((data: any) => {
      this.gameId = data;
      this.getStats();
    })
  }

  check(){
    this.gameService.checkAnswer(this.answer, this.guess)
    .subscribe((data:any) => {
      this.checkRes = data;
      this.saveAttempt();
      //если число угадано, то обновляется информация об игре
      if(this.checkRes.bulls === 4){
        this.updateGame();
        this.showInput = false;
      }
    })

  }

  //вывод статистики для игрока
  getStats(){
    this.gameService.findByPlayer(this.game.player)
    .subscribe((data: any) => {this.gameArr = data;})
  }
}
