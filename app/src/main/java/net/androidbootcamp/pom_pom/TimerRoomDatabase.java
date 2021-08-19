package net.androidbootcamp.pom_pom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CompletedTimer.class}, version = 1, exportSchema = false)
public abstract class TimerRoomDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile TimerRoomDatabase INSTANCE;

    static TimerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TimerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TimerRoomDatabase.class, "timer_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CompletedTimerDAO completedTimerDAO();
}
