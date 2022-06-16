package ru.invbeans.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.invbeans.model.domain.Check;
import ru.invbeans.service.GameLogicService;

import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GameLogicServiceImpl implements GameLogicService {
    @Override
    public int generateNumber(){
        int[] arr = new int[4];
        ArrayList numbers = new ArrayList();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for(int i = 0; i < 4; i++){
            arr[i] = (int) numbers.get(i);
        }
        int result = 0;
        //число получается из массива arr, arr[3] - первая цифра
        //если она равна 0, то 0 перемещается в другое место числа
        if(arr[3] == 0){
            int ind = (int) (Math.random() * 2);
            arr[3] = arr[ind];
            arr[ind] = 0;
        }
        for(int i = 0; i < 4; i++){
            result += arr[i] * Math.pow(10, i);
        }
        return result;
    }

    @Override
    public Check checkAnswer(int answer, int guess){
        int bulls = 0;
        int cows = 0;
        String answerStr = String.valueOf(answer);
        String guessStr = String.valueOf(guess);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(answerStr.charAt(i) == guessStr.charAt(j)){
                    if(i == j) bulls++;
                    else cows++;
                }
            }
        }
        //в структуре Check сохраняется количество быков и коров
        Check check = new Check(bulls, cows);
        return check;
    }
}
