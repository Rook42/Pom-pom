package net.androidbootcamp.pom_pom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompletedTimerDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CompletedTimer completedTimer);

    @Query("DELETE FROM completed_timer_table")
    void deleteAll();

    @Query("SELECT * FROM completed_timer_table ORDER BY timerFinish ASC")
    LiveData<List<CompletedTimer>> getCompletedTimers();
}
