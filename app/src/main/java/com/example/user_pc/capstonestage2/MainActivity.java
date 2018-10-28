package com.example.user_pc.capstonestage2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user_pc.capstonestage2.Adapters.MainAdapter;
import com.example.user_pc.capstonestage2.Models.Schedule;
import com.example.user_pc.capstonestage2.Models.SchedulesResponse;
import com.example.user_pc.capstonestage2.Retrofit.ApiClient;
import com.example.user_pc.capstonestage2.Retrofit.ApiInterface;
import com.example.user_pc.capstonestage2.Widget.UpdateService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        MainAdapter.MainAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.search_edit_text)
    EditText searchEditText;
    @BindView(R.id.search_button)
    Button searchButton;

    @BindView(R.id.monday_text_view)
    TextView mondayTextView;
    @BindView(R.id.tuesday_text_view)
    TextView tuesdayTextView;
    @BindView(R.id.wednesday_text_view)
    TextView wednesdayTextView;
    @BindView(R.id.thursday_text_view)
    TextView thursdayTextView;
    @BindView(R.id.friday_text_view)
    TextView fridayTextView;
    @BindView(R.id.saturday_text_view)
    TextView saturdayTextView;
    @BindView(R.id.sunday_text_view)
    TextView sundayTextView;

    @BindView(R.id.monday_recycler_view)
    RecyclerView mondayRecyclerView;
    @BindView(R.id.tuesday_recycler_view)
    RecyclerView tuesdayRecyclerView;
    @BindView(R.id.wednesday_recycler_view)
    RecyclerView wednesdayRecyclerView;
    @BindView(R.id.thursday_recycler_view)
    RecyclerView thursdayRecyclerView;
    @BindView(R.id.friday_recycler_view)
    RecyclerView fridayRecyclerView;
    @BindView(R.id.saturday_recycler_view)
    RecyclerView saturdayRecyclerView;
    @BindView(R.id.sunday_recycler_view)
    RecyclerView sundayRecyclerView;

    @BindView(R.id.ad_view)
    AdView adView;

    ArrayList<SchedulesResponse> schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                String searchQuery = searchEditText.getText().toString();
                intent.putExtra("search", searchQuery);
                startActivity(intent);
            }
        });

        mondayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        tuesdayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        wednesdayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        thursdayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fridayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        saturdayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        sundayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SchedulesResponse> call = apiService.getSchedule();
        call.enqueue(new Callback<SchedulesResponse>() {
            @Override
            public void onResponse(Call<SchedulesResponse> call, Response<SchedulesResponse> response) {

                mondayTextView.setVisibility(View.VISIBLE);
                tuesdayTextView.setVisibility(View.VISIBLE);
                wednesdayTextView.setVisibility(View.VISIBLE);
                thursdayTextView.setVisibility(View.VISIBLE);
                fridayTextView.setVisibility(View.VISIBLE);
                saturdayTextView.setVisibility(View.VISIBLE);
                sundayTextView.setVisibility(View.VISIBLE);

                List<Schedule> monday = response.body().getMonday();
                mondayRecyclerView.setAdapter(new MainAdapter(monday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                List<Schedule> tuesday = response.body().getTuesday();
                tuesdayRecyclerView.setAdapter(new MainAdapter(tuesday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                List<Schedule> wednesday = response.body().getWednesday();
                wednesdayRecyclerView.setAdapter(new MainAdapter(wednesday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                List<Schedule> thursday = response.body().getThursday();
                thursdayRecyclerView.setAdapter(new MainAdapter(thursday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                List<Schedule> friday = response.body().getFriday();
                fridayRecyclerView.setAdapter(new MainAdapter(friday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                List<Schedule> saturday = response.body().getSaturday();
                saturdayRecyclerView.setAdapter(new MainAdapter(saturday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                List<Schedule> sunday = response.body().getSunday();
                sundayRecyclerView.setAdapter(new MainAdapter(sunday, R.layout.list_item_schedule, getApplicationContext(), MainActivity.this));

                schedule = new ArrayList<>();

                List<Schedule> mondayList = monday;
                List<Schedule> tuesdayList = tuesday;
                List<Schedule> wednesdayList = wednesday;
                List<Schedule> thursdayList = thursday;
                List<Schedule> fridayList = friday;
                List<Schedule> saturdayList = saturday;
                List<Schedule> sundayList = sunday;

                ArrayList<String> scheduleListWidgets = new ArrayList<>();

                StringBuilder schedulesBuilder = new StringBuilder();

                for (Schedule mon : mondayList) {
                    schedulesBuilder.append(mon.getTitle() + "\n");
                    scheduleListWidgets.add("Monday:" + "\n" + mon.getTitle() + "\n");
                }

                for (Schedule tues : tuesdayList) {
                    schedulesBuilder.append(tues.getTitle() + "\n");
                    scheduleListWidgets.add("Tuesday:" + "\n" + tues.getTitle() + "\n");
                }

                for (Schedule wed : wednesdayList) {
                    schedulesBuilder.append(wed.getTitle() + "\n");
                    scheduleListWidgets.add("Wednesday:" + "\n" + wed.getTitle() + "\n");
                }

                for (Schedule thurs : thursdayList) {
                    schedulesBuilder.append(thurs.getTitle() + "\n");
                    scheduleListWidgets.add("Thursday:" + "\n" + thurs.getTitle() + "\n");
                }

                for (Schedule fri : fridayList) {
                    schedulesBuilder.append(fri.getTitle() + "\n");
                    scheduleListWidgets.add("Friday:" + "\n" + fri.getTitle() + "\n");
                }

                for (Schedule sat : saturdayList) {
                    schedulesBuilder.append(sat.getTitle() + "\n");
                    scheduleListWidgets.add("Saturday:" + "\n" + sat.getTitle() + "\n");
                }

                for (Schedule sun : sundayList) {
                    schedulesBuilder.append(sun.getTitle() + "\n");
                    scheduleListWidgets.add("Sunday:" + "\n" + sun.getTitle() + "\n");
                }

                UpdateService.startService(MainActivity.this, scheduleListWidgets);

            }

            @Override
            public void onFailure(Call<SchedulesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

    }

    @Override
    public void onClick(Schedule schedule, View view) {
        Context context = this;
        Class destinationClass = ScheduleDetailsActivity.class;
        final Intent scheduleDetailsIntent = new Intent(context, destinationClass);
        scheduleDetailsIntent.putExtra("Schedule", schedule);
        startActivity(scheduleDetailsIntent);
    }

    public void search(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorites:
                Intent aboutIntent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(aboutIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

