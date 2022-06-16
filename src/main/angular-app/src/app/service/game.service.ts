import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Game } from "../model/game";
import { Attempt } from "../model/attempt";
import { Check } from "../model/check";
import { Player } from "../model/player";

@Injectable()
export class GameService{
  mapping: string = 'http://localhost:8080/api/';
  constructor(private http: HttpClient){}

  saveGame(game: Game){
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type':  'application/json'
      })
    };
    const body = {id: game.id, player: game.player, attempts: game.attempts, time: game.time};
    return this.http.post<Game>(this.mapping + 'game/save', body, httpOptions);
  }

  getGames(){
    return this.http.get(this.mapping + 'game');
  }

  findByPlayer(player: number){
    return this.http.get(this.mapping + `game/player/${player}`);
  }

  getGameById(id: number){
    return this.http.get(this.mapping + `game/${id}`);
  }

  saveAttempt(attempt: Attempt){
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type':  'application/json'
      })
    };
    const body = {game: attempt.game, guess: attempt.guess, time: attempt.time};
    return this.http.post<Attempt>(this.mapping + 'attempt/save', body, httpOptions);
  }

  getAttempts(){
    return this.http.get(this.mapping + 'attempt');
  }

  getAttemptById(id: number){
    return this.http.get(this.mapping + `attempt/${id}`);
  }

  getByGameId(game: number){
    return this.http.get(this.mapping + `attempt/game/${game}`);
  }

  generateNumber(){
    return this.http.get<number>(this.mapping + 'logic/generate');
  }

  checkAnswer(answer: number, guess: number){
    const params = new HttpParams()
    .set('answer', answer.toString())
    .set('guess', guess.toString());
    return this.http.get<Check>(this.mapping + 'logic/check', {params});
  }

  getAttemptsLimit(){
    return this.http.get(this.mapping + 'logic/attempts_limit');
  }

  getTimeLimit(){
    return this.http.get(this.mapping + 'logic/time_limit');
  }

  savePlayer(player: Player){
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type':  'application/json'
      })
    };
    const body = {nickname: player.nickname, password: player.password};
    return this.http.post<Player>(this.mapping + 'player/save', body, httpOptions);
  }

  findByNickname(nickname: string){
    const params = new HttpParams()
    .set('nickname', nickname)
    return this.http.get<number>(this.mapping + 'player/nickname', {params});
  }

  findByNicknameAndPassword(nickname: string, password: string){
    const params = new HttpParams()
    .set('nickname', nickname)
    .set('password', password);
    return this.http.get<number>(this.mapping + 'player/nick_pass', {params});
  }
}
