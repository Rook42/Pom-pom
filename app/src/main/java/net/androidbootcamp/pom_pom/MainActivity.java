//DONE: Connect the Activities
//DONE: Include svg icons for pomodoro, short break, and long break.


package net.androidbootcamp.pom_pom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public CountDownTimer countDownTimer;
    public String currentTimerType = null;
    private TextView timerText;
    private ProgressBar progressBar;
    private ToggleButton pomodoroButton;
    private ToggleButton shortBreakButton;
    private ToggleButton longBreakButton;
    private SharedPreferences sharedPreferences;
    private TimerViewModel timerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        //get timer text, buttons, & progressbar for updating
        timerText = (TextView) findViewById(R.id.timerText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pomodoroButton = (ToggleButton) findViewById(R.id.pomodoroButton);
        shortBreakButton = (ToggleButton) findViewById(R.id.shortBreakButton);
        longBreakButton = (ToggleButton) findViewById(R.id.longBreakButton);
        timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);

    }

    public void startTimer(final int duration) {
        countDownTimer = new CountDownTimer(
                duration * 60 * 1000,
                1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerUI(
                        duration * 60,
                        millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                long currentTime = Calendar.getInstance().getTimeInMillis();
                long startedTime = currentTime - (duration * 60 * 1000);
                String timerType =
                        currentTimerType == "pomodoro" ? "Pomodoro" :
                                currentTimerType == "shortBreak" ? "Short Break" :
                                        "Long Break";
                CompletedTimer completedTimer = new CompletedTimer
                        (new Date(startedTime), new Date(currentTime), timerType, 0);
                timerViewModel.insert(completedTimer);
            }
        }.start();

    }

    private void updateTimerUI(int secondsDuration, long secondsRemaining) {
        //set progressBar to xx (/100) for completion
        progressBar.setIndeterminate(false);
        progressBar.setProgress((int) (100 - (secondsRemaining * 100 / secondsDuration)));
        int minutes = (int) secondsRemaining / 60;
        int seconds = (int) secondsRemaining % 60;

        //format text for timerText. Example: 04:32
        String newTime = String.format("%1$02d:%2$02d", minutes, seconds);
        timerText.setText(newTime);
    }

    private void timerPressed(String timer_type) {
        //clear old timers & buttons
        reset();
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        int timerDuration = 0;
        currentTimerType = timer_type;

        //get intended duration from preferences
        switch (timer_type) {
            case "pomodoro":
                pomodoroButton.setChecked(true);
                timerDuration = Integer.valueOf(
                        sharedPreferences.getString("durationPomodoro", "25")
                );
                break;
            case "longBreak":
                longBreakButton.setChecked(true);
                timerDuration = Integer.valueOf(
                        sharedPreferences.getString("durationLongBreak", "15")
                );
                break;
            case "shortBreak":
                shortBreakButton.setChecked(true);
                timerDuration = Integer.valueOf(
                        sharedPreferences.getString("durationShortBreak", "5")
                );
                break;
        }

        startTimer(timerDuration);
    }

    private void reset() {
        timerText.setText("00:00");
        pomodoroButton.setChecked(false);
        shortBreakButton.setChecked(false);
        longBreakButton.setChecked(false);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        currentTimerType = null;
    }

    public void pomodoroButtonHandler(View view) {
        timerPressed("pomodoro");
    }

    public void longBreakButtonHandler(View view) {
        timerPressed("longBreak");
    }

    public void shortBreakButtonHandler(View view) {
        timerPressed("shortBreak");
    }

    public void resetButtonHandler(View view) {
        reset();
    }


    //User navigates to History activity.
    public void viewHistory(View view) {
        startActivity(new Intent(MainActivity.this, HistoryActivity.class));
    }

    //User navigates to Settings activity.
    public void viewSettings(View view) {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }
}