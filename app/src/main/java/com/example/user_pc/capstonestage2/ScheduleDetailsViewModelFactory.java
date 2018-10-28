package com.example.user_pc.capstonestage2;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.user_pc.capstonestage2.Database.AppDatabase;

public class ScheduleDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mFavorieId;

    public ScheduleDetailsViewModelFactory(AppDatabase database, int favoriteId) {
        mDb = database;
        mFavorieId = favoriteId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ScheduleDetailsViewModel(mDb, mFavorieId);
    }
}
