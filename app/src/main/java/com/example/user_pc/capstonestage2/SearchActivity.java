package com.example.user_pc.capstonestage2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user_pc.capstonestage2.Adapters.SearchAdapter;
import com.example.user_pc.capstonestage2.Models.Search;
import com.example.user_pc.capstonestage2.Utilities.JsonSearch;
import com.example.user_pc.capstonestage2.Utilities.NetworkSearch;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements
        SearchAdapter.SearchAdapterOnClickHandler {

    @BindView(R.id.search_edit_text)
    EditText searchEditText;

    @BindView(R.id.search_recycle)
    RecyclerView searchRecyclerView;

    @BindView(R.id.error_message)
    TextView errorMessage;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    String search;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            search = extras.getString("search");
            loadSearchQuery(String.valueOf(search));
        }

        LinearLayoutManager LayoutManagerSearch = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(LayoutManagerSearch);
        searchRecyclerView.setHasFixedSize(true);
        searchAdapter = new SearchAdapter(this);
        searchRecyclerView.setAdapter(searchAdapter);
    }

    private void loadSearchQuery(String searchBundle) {
        URL searchUrl = NetworkSearch.buildUrl(searchBundle);
        new SearchQueryTask().execute(searchUrl);
    }

    private void searchQuery() {
        String searchQuery = searchEditText.getText().toString();
        URL searchUrl = NetworkSearch.buildUrl(searchQuery);
        new SearchQueryTask().execute(searchUrl);
    }

    private void showJsonDataView() {
        errorMessage.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        errorMessage.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(Search search) {
        Context context = this;
        Class destinationClass = ScheduleDetailsActivity.class;
        final Intent scheduleDetailsIntent = new Intent(context, destinationClass);
        scheduleDetailsIntent.putExtra("Search", search);
        startActivity(scheduleDetailsIntent);
    }

    public class SearchQueryTask extends AsyncTask<URL, Void, ArrayList<Search>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Search> doInBackground(URL... params) {
            URL searchUrl = params[0];
            try {
                String searchResults = NetworkSearch.getResponseFromHttpUrl(searchUrl);
                ArrayList<Search> simpleJsonSearchData = JsonSearch.getSearchStringsFromJson(SearchActivity.this, searchResults);
                return simpleJsonSearchData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Search> searchResults) {
            progressBar.setVisibility(View.INVISIBLE);
            if (searchResults != null) {
                showJsonDataView();
                searchAdapter.setSearchData(searchResults);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            searchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
