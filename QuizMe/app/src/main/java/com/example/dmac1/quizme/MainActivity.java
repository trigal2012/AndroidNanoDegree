package com.example.dmac1.quizme;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method to determine the user's score
    private double getScore() {
        double score = 0;
        score += getRBAnswers(R.id.radiogroup1, R.id.radiobutton_answer12);
        score += getRBAnswers(R.id.radiogroup2, R.id.radiobutton_answer21);
        score += getRBAnswers(R.id.radiogroup3, R.id.radiobutton_answer33);
        score += getRBAnswers(R.id.radiogroup4, R.id.radiobutton_answer41);
        score += getCBAnswers();
        score += getTextAns();
        score = Math.round(score / .0006);
        score = score / 100;
        return score;
    }

    //method to get user input to text question
    private int getTextAns() {
        EditText tempAns = findViewById(R.id.answer0);
        if (tempAns.getText().toString().toLowerCase().equals("addition")) {
            return 1;
        } else {
            return 0;
        }
    }

    //method to determine if the checkbox question was answered correctly
    private int getCBAnswers() {
        CheckBox cbAns1 = findViewById(R.id.multiplication);
        CheckBox cbAns2 = findViewById(R.id.subtraction);
        CheckBox cbAns3 = findViewById(R.id.addition);
        CheckBox cbAns4 = findViewById(R.id.division);
        if (cbAns2.isChecked() && cbAns3.isChecked() && cbAns4.isChecked() && !cbAns1.isChecked()) {
            return 1;
        } else {
            return 0;
        }
    }

    //method to determine if the Radio button questions were answered correctly
    private int getRBAnswers(int radioGroupId, int correctAnswer) {
        RadioGroup radioGroup = findViewById(radioGroupId);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == correctAnswer) {
            return 1;
        } else {
            return 0;
        }
    }

    //form the toast message string using the player name and the grade
    @SuppressLint("StringFormatMatches")
    private String createToast(double grade) {
        String message;
        Resources res = getResources();
        message = res.getString(R.string.toast_message, grade);
        return message;
    }

    //takes a message string and triggers a toast message, made using a toast xml layout file
    private void displayToast(String message) {

        //get the LayoutInflater and inflate the custom_toast layout
        LayoutInflater view = getLayoutInflater();
        View layout = view.inflate(R.layout.toast, null);

        //get the TextView from the custom_toast layout
        TextView text = layout.findViewById(R.id.txtMessage);
        text.setText(message);

        //create the toast object, set display duration,
        //set the view as layout that's inflated above and then call show()
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    //clear all values so the quiz can be taken again
    private void reset() {
        setContentView(R.layout.activity_main);
    }

    //called when user clicks the "Check" button
    //calls the display method, which in turn calls the generate the toast method, get player name method, and get score method
    //then resets everything and starts the quiz over
    public void checkScore(View view) {
        displayToast(createToast(getScore()));
        reset();
    }
}
