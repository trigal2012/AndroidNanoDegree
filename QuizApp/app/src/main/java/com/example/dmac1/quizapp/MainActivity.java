package com.example.dmac1.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String[] questions;
    private String[] answers = new String[2];
    LinearLayout titleRow;
    String a1;
    int a2;

    int[] correctAnswers={-253, 300};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addQuestions();
        addSubmitBtn();
    }

    private void addQuestions(){
            //find the linear layout
            titleRow = findViewById(R.id.list);

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
                Log.i("my tag", "id is: " + question.getId());

                //for even numbered index questions, use EditText component for the answer
                if(i % 2 == 0) {
                    //add the answer component
                    EditText answerView = (EditText) View.inflate(this, R.layout.edit_text, null);
                    titleRow.addView(answerView);

                    //set id on the new view
                    EditText answer = findViewById(R.id.answer);
                    answer.setId(100+i);
                    Log.i("my tag", "id is: " + answer.getId());

                    //for odd numbered index questions use Radiobutton component for the answer
                } else {

                    String[] radioButtons = getResources().getStringArray(R.array.radio_button_1);

                    //add the answer group component
                    RadioGroup answerView = (RadioGroup)View.inflate(this, R.layout.radiogroup, null);
                    titleRow.addView(answerView);
                    answerView.setId(200+i);
                    Log.i("my tag", "id is: " + answerView.getId());


                    for(int x = 0; x<radioButtons.length;x++) {
                        //add the radio buttons to the group
                        RadioButton answerGroup = (RadioButton) View.inflate(this, R.layout.radiobutton, null);
                        answerView.addView(answerGroup);

                        //add text value of radio button
                        RadioButton answerButton = findViewById(R.id.radiobutton_answer);
                        answerButton.setText(radioButtons[x]);
                        answerButton.setId(300+x);
                        Log.i("my tag", "id is: " + answerButton.getId());

                    }

                }
            }



    }

    private void addSubmitBtn(){
        TextView submitBtnView = (TextView) View.inflate(this, R.layout.submit_button, null);
        titleRow.addView(submitBtnView);

    }
    public void submit(View view) {
        Log.i("my tag", "submit button clicked");
        a1 = getEditTextAnswers(100 + 0);
        Log.i("my tag", "a1 answer is: " + a1);
        a2 = getRBAnswers();
        Log.i("my tag", "a2 answer is: " + a2);

        answers[0] = a1;
        answers[1] = Integer.toString(a2);

        Integer.parseInt(a1);
        Log.i("my tag", "a1 answer is: " + a1);

        Log.i("my tag", "my array: " + Arrays.toString(answers));

        //compare arrays


    }

    private String getEditTextAnswers(int answerId){
        EditText tempAns = (EditText)findViewById(answerId);
        return tempAns.getText().toString().toLowerCase();
    }

   private int getRBAnswers(){
        int rgId = 201;
        RadioGroup radioGroup = findViewById(rgId);
        int selectedId = radioGroup.getCheckedRadioButtonId();
       String radiovalue = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        Log.i("my tag", "value is: " + radiovalue);
       return selectedId;
    }

}