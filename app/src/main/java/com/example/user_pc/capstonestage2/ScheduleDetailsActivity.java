package com.example.user_pc.capstonestage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user_pc.capstonestage2.Models.Schedule;
import com.example.user_pc.capstonestage2.Models.Search;
import com.example.user_pc.capstonestage2.Database.AppDatabase;
import com.example.user_pc.capstonestage2.Database.Favorites;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleDetailsActivity extends AppCompatActivity {

    public Schedule scheduleObject;
    public Search searchObject;

    String mUrl;
    String mTitle;
    String mImageUrl;
    String mSynopsis;
    String mType;
    String mEpisodes;
    String mScore;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image_url)
    ImageView imageUrl;
    @BindView(R.id.synopsis)
    TextView synopsis;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.episodes)
    TextView episodes;
    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.shareButton)
    Button shareButton;

    private AppDatabase mDb;

    public static final String EXTRA_FAVORITE_ID = "extraFavoriteId";
    public static final String INSTANCE_FAVORITE_ID = "instanceFavoriteId";
    private static final int DEFAULT_FAVORITE_ID = -1;

    private int mFavoriteId = DEFAULT_FAVORITE_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scheduleObject = (Schedule) getIntent().getSerializableExtra("Schedule");
        searchObject = getIntent().getParcelableExtra("Search");

        if (scheduleObject != null) {
            mUrl = scheduleObject.getUrl();
            mTitle = scheduleObject.getTitle();
            mImageUrl = scheduleObject.getImageUrl();
            mSynopsis = scheduleObject.getSynopsis();
            mType = scheduleObject.getType();
            mEpisodes = String.valueOf(scheduleObject.getEpisodes());
            mScore = String.valueOf(scheduleObject.getScore());
        } else if (searchObject != null) {
            mUrl = searchObject.getUrl();
            mTitle = searchObject.getTitle();
            mImageUrl = searchObject.getImageUrl();
            mSynopsis = searchObject.getSynopsis();
            mType = searchObject.getType();
            mEpisodes = String.valueOf(searchObject.getEpisodes());
            mScore = String.valueOf(searchObject.getScore());
            Log.d("scoretest", mScore);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_FAVORITE_ID)) {
            mFavoriteId = savedInstanceState.getInt(INSTANCE_FAVORITE_ID, DEFAULT_FAVORITE_ID);
        }

        title.setText(mTitle);
        Picasso.get()
                .load(mImageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageUrl);
        synopsis.setText(mSynopsis);
        type.setText(mType);
        episodes.setText(mEpisodes);
        score.setText(mScore);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShareButtonClicked();
            }
        });

        mDb = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_FAVORITE_ID)) {
            saveButton.setText(R.string.unfavorite);
            if (mFavoriteId == DEFAULT_FAVORITE_ID) {
                mFavoriteId = intent.getIntExtra(EXTRA_FAVORITE_ID, DEFAULT_FAVORITE_ID);

                ScheduleDetailsViewModelFactory factory = new ScheduleDetailsViewModelFactory(mDb, mFavoriteId);
                final ScheduleDetailsViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(ScheduleDetailsViewModel.class);

                viewModel.getFavorites().observe(this, new Observer<Favorites>() {
                    @Override
                    public void onChanged(@Nullable Favorites favoriteEntry) {
                        viewModel.getFavorites().removeObserver(this);
                        populateUI(favoriteEntry);
                    }
                });
            }
        }

    }

    private void populateUI(Favorites favorite) {
        if (favorite == null) {
            return;
        }

        title.setText(favorite.getTitle());
        Picasso.get().load(favorite.getImageUrl()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(imageUrl);
        synopsis.setText(favorite.getSynopsis());
        type.setText(favorite.getType());
        episodes.setText(String.valueOf(favorite.getEpisodes()));
        score.setText(String.valueOf(favorite.getScore()));
    }

    public void onSaveButtonClicked() {
        String title = mTitle;
        String imageUrl = mImageUrl;
        String synopsis = mSynopsis;
        String type = mType;
        String episodes = mEpisodes;
        String score = mScore;

        final Favorites favorite = new Favorites(title, imageUrl, synopsis, type, episodes, score);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mFavoriteId == DEFAULT_FAVORITE_ID) {
                    mDb.favoriteDao().insert(favorite);
                } else {
                    favorite.setId(mFavoriteId);
                    mDb.favoriteDao().delete(favorite);
                }
                finish();
            }
        });
    }

    public void onShareButtonClicked() {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String urlShare = mUrl;
        sharingIntent.putExtra(Intent.EXTRA_TEXT, urlShare);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}
