package com.example.dmac1.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addQuestions();

    }


        private void addQuestions(){
            //find the linear layout
            LinearLayout titleRow = findViewById(R.id.list);

            //add a text view for each item in the array
            questions = getResources().getStringArray(R.array.math_questions);

            for(int i = 0; i<questions.length;i++){

                //adds a text view component
                TextView questionView = (TextView) View.inflate(this, R.layout.text_view, null);
                titleRow.addView(questionView);

                //add question details to question view
                TextView question = findViewById(R.id.question);
                question.setText(questions[i]);
                question.setId(i);

                //add the answer component
                EditText answerView = (EditText) View.inflate(this, R.layout.edit_text, null);
                titleRow.addView(answerView);

                //set id on the new view
                EditText answer = findViewById(R.id.answer);
                answer.setId(R.id.answer+i);

            }



    }


}
