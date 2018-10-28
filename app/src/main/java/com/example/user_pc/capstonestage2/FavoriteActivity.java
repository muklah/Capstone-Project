package com.example.user_pc.capstonestage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user_pc.capstonestage2.Adapters.FavoriteAdapter;
import com.example.user_pc.capstonestage2.Database.AppDatabase;
import com.example.user_pc.capstonestage2.Database.Favorite;
import com.example.user_pc.capstonestage2.Database.Favorites;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class FavoriteActivity extends AppCompatActivity implements
        FavoriteAdapter.ItemClickListener{

    private RecyclerView favoriteRecyclerView;
    private FavoriteAdapter favoriteAdapter;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteRecyclerView = findViewById(R.id.recyclerViewFavorites);

        favoriteRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        favoriteAdapter = new FavoriteAdapter(this, this);
        favoriteRecyclerView.setAdapter(favoriteAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        favoriteRecyclerView.addItemDecoration(decoration);

        mDb = AppDatabase.getInstance(getApplicationContext());
        setupViewModel();
    }

    private void setupViewModel() {
        FavoriteViewModel viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        viewModel.getFavorites().observe(this, new Observer<List<Favorites>>() {
            @Override
            public void onChanged(@Nullable List<Favorites> favoriteEntries) {
                favoriteAdapter.setFavorites(favoriteEntries);
            }
        });
    }

    @Override
    public void onItemClickListener(int itemId) {

        Intent intent = new Intent(FavoriteActivity.this, ScheduleDetailsActivity.class);
        intent.putExtra(ScheduleDetailsActivity.EXTRA_FAVORITE_ID, itemId);
        startActivity(intent);
    }
}