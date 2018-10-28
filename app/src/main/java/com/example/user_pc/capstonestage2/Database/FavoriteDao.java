package com.example.user_pc.capstonestage2.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite ORDER BY id")
    LiveData<List<Favorites>> loadAllFavorites();

    @Insert
    void insert(Favorites favorite);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Favorites favoriteEntry);

    @Delete
    void delete(Favorites favorite);

    @Query("SELECT * FROM favorite WHERE id = :id")
    LiveData<Favorites> loadFavoriteById(int id);

}
