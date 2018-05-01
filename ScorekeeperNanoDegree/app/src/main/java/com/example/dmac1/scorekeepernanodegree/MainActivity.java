package com.example.dmac1.scorekeepernanodegree;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {

    int team1_score = 0;
    int team2_score = 0;
    int team1ViewId = 0;
    int team2ViewId = 0;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("team1_score", team1_score);
        outState.putInt("team2_score", team2_score);
        outState.putInt("team1ViewId", team1ViewId);
        outState.putInt("team2ViewId", team2ViewId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            team1_score = savedInstanceState.getInt("team1_score");
            team2_score = savedInstanceState.getInt("team2_score");
            team1ViewId = savedInstanceState.getInt("team1ViewId");
            team2ViewId = savedInstanceState.getInt("team2ViewId");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the view ids on page load and save them
       team1ViewId = R.id.team1_score;
       team2ViewId = R.id.team2_score;


        //set score for team 1
        updateDisplay(team1ViewId, team1_score);

        //set score for team 2
        updateDisplay(team2ViewId, team2_score);
    }

    //onClick method to add 1 to the score for team1
    public void team1_button1(View view) {
        team1_score += 1;
        updateDisplay(team1ViewId, team1_score);
    }

    //onClick method to add, subtract, or not change the score for team 1
    //based on the results of the "lucky" method
    public void team1_button2(View view){
        team1_score += lucky();
        updateDisplay(team1ViewId, team1_score);
    }

    //onClick method to add 1 to the score for team2
    public void team2_button1(View view) {
        team2_score += 1;
        updateDisplay(team2ViewId, team2_score);
    }

    //onClick method to add, subtract, or not change the score for team 2
    //based on the results of the "lucky" method
    public void team2_button2(View view){
        team2_score += lucky();
        updateDisplay(team2ViewId, team2_score);
    }

    //returns a random number between -5 and 20
    private int lucky(){
        int tmpNum = (int )(Math.random() * 20 + -5);
        if (tmpNum == -1) {
            displayToast("bummer, you lost " + tmpNum * -1 + " point :-(");
        } else if (tmpNum < -1){
            displayToast("bummer, you lost " + tmpNum * -1 + " points :-(");
        }else if(tmpNum == 0){
            displayToast("well, could be worse, you could have lost points");
        }else if (tmpNum == 1){
            displayToast("luck is on your side, you won " + tmpNum + " point :-)");
        }else{
            displayToast("luck is on your side, you won " + tmpNum + " points :-)");
        }
        return tmpNum;
    }

    public void reset(View view){
        team1_score = 0;
        team2_score = 0;
        updateDisplay(team1ViewId, team1_score);
        updateDisplay(team2ViewId, team2_score);
    }

    private void updateDisplay(Integer team, Integer score){
        TextView scoreView = findViewById(team);
        String score2String = Integer.toString(score);
        scoreView.setText(score2String);
    }


    //toast message creation and update based on results of lucky method
    private void displayToast(String message){

        //get the LayoutInflater and inflate the custom_toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, null);

        //get the TextView from the custom_toast layout
        TextView text = (TextView) layout.findViewById(R.id.txtMessage);
        text.setText(message);

        //create the toast object, set display duration,
        //set the view as layout that's inflated above and then call show()
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
