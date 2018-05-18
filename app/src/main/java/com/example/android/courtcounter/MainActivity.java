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

    EditText edit_text_input;
    Button button_start_pause;
    Button button_reset;
    TextView text_view_countdown;
    CountDownTimer count_down_timer;

    boolean timer_running;

    long time_left;
    long end_time;

    // Tracks the score for Team A
    int scoreTeamA = 0;

    // Tracks the score for Team B
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text_input = (EditText) findViewById(R.id.edit_text_input);

        button_start_pause = (Button) findViewById(R.id.button_start_pause);

        button_reset = (Button) findViewById(R.id.button_reset);

        text_view_countdown = (TextView) findViewById(R.id.text_view_countdown);

        button_start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        String text = edit_text_input.getText().toString();
        if (!text.equalsIgnoreCase("")) {
            int minutes = Integer.valueOf(text);
            if (minutes > 60) {
                minutes = 60;
            }
            count_down_timer = new CountDownTimer(minutes * 60000, 1000) {
                @Override
                public void onTick(long millis) {
                    time_left = millis;
                    updateCountDownTimer();
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
        edit_text_input.setText("");
        text_view_countdown.setText("");
        timer_running = false;
        button_reset.setVisibility(View.INVISIBLE);
        button_start_pause.setVisibility(View.VISIBLE);
    }

    /**
     * Updates the count down timer text.
     */
    public void updateCountDownTimer() {
        int minutes = (int) (time_left / 1000) / 60;
        int seconds = (int) (time_left / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        text_view_countdown.setText(timeLeftFormatted);
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
     * Increase the score for Team A by the appropriate points.
     */
    public void addScoreForTeamA(View v) {
        scoreTeamA += Integer.parseInt(v.getTag().toString());
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
     * Increase the score for Team B by the appropriate points.
     */
    public void addScoreForTeamB(View v) {
        scoreTeamB += Integer.parseInt(v.getTag().toString());
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timeLeft", time_left);
        outState.putBoolean("timerRunning", timer_running);
        outState.putLong("endTime", end_time);
        outState.putInt("scoreForTeamA", scoreTeamA);
        outState.putInt("scoreForTeamB", scoreTeamB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        time_left = savedInstanceState.getLong("timeLeft");
        timer_running = savedInstanceState.getBoolean("timerRunning");
    }


}