package com.aneeqshah.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView correctWrongView;
    TextView scoreView;
    TextView mathView;
    TextView timerView;
    RelativeLayout gameLayout;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    Button playAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int indexOfCorrectAnswer;
    int score = 0;
    int questions = 0;

    public void reset(View view){

        score=0;
        questions = 0;
        timerView.setText("30s");
        scoreView.setText("0/0");
        correctWrongView.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        generate();

        new CountDownTimer(30100, 1000) {


            @Override
            public void onTick(long l) {

                timerView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);
                timerView.setText("0s");
                correctWrongView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(questions));

            }
        }.start();

    }

    public void generate(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);
        indexOfCorrectAnswer = random.nextInt(4);
        int wrongAns;

        mathView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();

        for(int i = 0; i < 4; i++){

            if(i == indexOfCorrectAnswer) {

                answers.add(a + b);

            } else{

                wrongAns = random.nextInt(41);

                while(wrongAns == a + b){

                    wrongAns = random.nextInt(41);

                }

                answers.add(wrongAns);
            }

        }

        ans1.setText(Integer.toString(answers.get(0)));
        ans2.setText(Integer.toString(answers.get(1)));
        ans3.setText(Integer.toString(answers.get(2)));
        ans4.setText(Integer.toString(answers.get(3)));

    }

    public void playing(View view){

        if(view.getTag().toString().equals(Integer.toString(indexOfCorrectAnswer))){

            score++;
            correctWrongView.setText("Correct!");

        }else{

            correctWrongView.setText("Wrong!");
        }

        questions++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(questions));
        generate();
    }

    public void start(View view) {

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        reset(findViewById(R.id.playAgain));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        mathView = findViewById(R.id.mathView);
        correctWrongView = findViewById(R.id.correctWrongView);
        scoreView = findViewById(R.id.scoreView);
        timerView = findViewById(R.id.timerView);
        playAgain = findViewById(R.id.playAgain);
        gameLayout = findViewById(R.id.gameLayout);

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);


    }


}
