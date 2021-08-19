package net.androidbootcamp.pom_pom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TimerViewModel extends AndroidViewModel {
    private final TimerRepository mRepository;

    private final LiveData<List<CompletedTimer>> mAllTimers;

    public TimerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TimerRepository(application);
        mAllTimers = mRepository.getAllTimers();
    }

    LiveData<List<CompletedTimer>> getAllTimers() {
        return mAllTimers;
    }

    public void insert(CompletedTimer timer) {
        mRepository.insert(timer);
    }
}
