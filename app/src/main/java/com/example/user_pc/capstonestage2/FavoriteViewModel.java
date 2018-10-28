package com.example.user_pc.capstonestage2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.user_pc.capstonestage2.Database.AppDatabase;
import com.example.user_pc.capstonestage2.Database.Favorite;
import com.example.user_pc.capstonestage2.Database.Favorites;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private LiveData<List<Favorites>> favorites;

    public FavoriteViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        favorites = database.favoriteDao().loadAllFavorites();
    }

    public LiveData<List<Favorites>> getFavorites() {
        return favorites;
    }
}
