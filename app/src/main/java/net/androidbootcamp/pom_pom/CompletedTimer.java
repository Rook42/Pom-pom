package net.androidbootcamp.pom_pom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "completed_timer_table")
@TypeConverters(DateConverter.class)
public class CompletedTimer {

    @PrimaryKey(autoGenerate = true)
    private final int id;

    @NonNull
    @ColumnInfo(name = "timerStart")
    private final Date timerStart;

    @NonNull
    @ColumnInfo(name = "timerFinish")
    private final Date timerFinish;

    @NonNull
    @ColumnInfo(name = "timerType")
    private final String timerType;


    public CompletedTimer(Date timerStart, Date timerFinish, String timerType, int id) {
        this.id = 0;
        this.timerStart = timerStart;
        this.timerFinish = timerFinish;
        this.timerType = timerType;
    }


    public int getId() {
        return this.id;
    }

    public Date getTimerStart() {
        return this.timerStart;
    }

    public Date getTimerFinish() {
        return this.timerFinish;
    }

    public String getTimerType() {
        return this.timerType;
    }

    public String getTimerTimes() {
        long start = timerStart.getTime();
        long finish = timerFinish.getTime();
        DateFormat format = new SimpleDateFormat("hh:mm");
        String out = format.format(start) + "-" + format.format(finish);
        return out;
    }

    public int getTimerImage() {
        int out;
        switch (timerType) {
            case "Pomodoro":
                out = R.drawable.tomato_empty;
                break;
            case "Short Break":
                out = R.drawable.time;
                break;
            default:
                out = R.drawable.time_smile;
        }
        return out;
    }
}
