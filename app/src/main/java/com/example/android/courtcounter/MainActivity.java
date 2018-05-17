package com.example.android.courtcounter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    Button button_start_pause;

    Button button_reset;

    TextView text_view_countdown;

    CountDownTimer count_down_timer;

    boolean timer_running;

    // Tracks the score for Team A
    int scoreTeamA = 0;

    // Tracks the score for Team B
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        button_start_pause = (Button) findViewById(R.id.button_start_pause);

        button_reset = (Button) findViewById(R.id.button_reset);

        text_view_countdown = (TextView) findViewById(R.id.text_view_countdown);

        button_start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if (timer_running) {
                    pauseTimer();
                } else {
                    startTimer();
                }

            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

    }

    /**
     * Starts the countdown timer.
     */
    public void startTimer() {
        String text = editText.getText().toString();
        if (!text.equalsIgnoreCase("")) {
            int minutes = Integer.valueOf(text);
            count_down_timer = new CountDownTimer(minutes * 1000, 1000) {
                @Override
                public void onTick(long millis) {
                    int minutes = (int) (millis / 1000) / 60;
                    int seconds = (int) (millis / 1000) % 60;

                    String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                    text_view_countdown.setText(timeLeftFormatted);
                }

                @Override
                public void onFinish() {
                    timer_running = false;
                    button_start_pause.setText("Start");
                    button_start_pause.setVisibility(View.INVISIBLE);
                    button_reset.setVisibility(View.VISIBLE);
                    text_view_countdown.setText("Finished");
                }
            }.start();
        }

        timer_running = true;
        button_start_pause.setText("Pause");
        button_reset.setVisibility(View.VISIBLE);
    }

    /**
     * Pauses the countdown timer if timer is currently running.
     */
    public void pauseTimer() {
        count_down_timer.cancel();
        timer_running = false;
        button_start_pause.setText("Start");
        button_reset.setVisibility(View.VISIBLE);
    }

    /**
     * Resets the countdown timer.
     */
    public void resetTimer() {
        editText.setText("");
        text_view_countdown.setText("");
        timer_running = false;
        button_reset.setVisibility(View.INVISIBLE);
        button_start_pause.setVisibility(View.VISIBLE);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void addThreeForTeamA(View v) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int scoreTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Increase the score for Team B by 1 point.
     */
    public void addOneForTeamB(View v) {
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team B by 2 points.
     */
    public void addTwoForTeamB(View v) {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamB(View v) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int scoreTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));
    }

    /**
     * Reset the scores for Team A and Team B back to 0.
     */
    public void resetAllScores(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}