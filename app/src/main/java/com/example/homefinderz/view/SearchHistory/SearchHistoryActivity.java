package com.example.homefinderz.view.SearchHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.example.homefinderz.R;

public class SearchHistoryActivity extends AppCompatActivity implements SearchHistoryView {

    SearchHistoryPresenter presenter;
    SearchHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        Intent intent = getIntent();
        presenter = new SearchHistoryPresenter(this);
        presenter.setAccountID(intent.getIntExtra("accountID", -1));

        RecyclerView rvSearchHistory = (RecyclerView) findViewById(R.id.rv_searchHistoryRecycler);
        adapter = new SearchHistoryAdapter(presenter.getSearchHistory(), this);
        rvSearchHistory.setAdapter(adapter);
        rvSearchHistory.setLayoutManager(new LinearLayoutManager(this));
    }
}