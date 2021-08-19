package net.androidbootcamp.pom_pom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TimerRepository {
    private final CompletedTimerDAO mCompletedTimerDao;
    private final LiveData<List<CompletedTimer>> mAllTimers;

    TimerRepository(Application application) {
        TimerRoomDatabase db = TimerRoomDatabase.getDatabase(application);
        mCompletedTimerDao = db.completedTimerDAO();
        mAllTimers = mCompletedTimerDao.getCompletedTimers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<CompletedTimer>> getAllTimers() {
        return mAllTimers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(CompletedTimer completedTimer) {
        TimerRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCompletedTimerDao.insert(completedTimer);
        });
    }
}
