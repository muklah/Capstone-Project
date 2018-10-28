package com.example.user_pc.capstonestage2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.user_pc.capstonestage2.Database.AppDatabase;
import com.example.user_pc.capstonestage2.Database.Favorite;
import com.example.user_pc.capstonestage2.Database.Favorites;

public class ScheduleDetailsViewModel extends ViewModel {

    private LiveData<Favorites> favorite;

    public ScheduleDetailsViewModel(AppDatabase database, int favorieId) {
        favorite = database.favoriteDao().loadFavoriteById(favorieId);
    }

    public LiveData<Favorites> getFavorites() {
        return favorite;
    }
}
