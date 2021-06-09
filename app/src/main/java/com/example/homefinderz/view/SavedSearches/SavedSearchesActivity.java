package com.example.homefinderz.view.SavedSearches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homefinderz.R;
import com.example.homefinderz.view.SearchHistory.SearchHistoryActivity;

public class SavedSearchesActivity extends AppCompatActivity implements SavedSearchesView{

    SavedSearchesPresenter presenter;
    SavedSearchesAdapter adapter;
    Intent callerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_searches);

        callerIntent = getIntent();
        presenter = new SavedSearchesPresenter(this);
        presenter.setAccountID(callerIntent.getIntExtra("accountID", -1));

        RecyclerView rvSavedSearches = (RecyclerView)findViewById(R.id.rv_savedSearchesRecycler);
        adapter = new SavedSearchesAdapter(presenter.getStoredSearches(), this);
        rvSavedSearches.setAdapter(adapter);
        rvSavedSearches.setLayoutManager(new LinearLayoutManager(this));

        Button btnArchivedSearches = (Button) findViewById(R.id.btnArchivedSearches);
        btnArchivedSearches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSearchHistory();
            }
        });
    }

    private void viewSearchHistory() {
        Intent intent = new Intent(SavedSearchesActivity.this, SearchHistoryActivity.class);
        intent.putExtra("accountID", presenter.getAccountID());
        startActivity(intent);
    }

    @Override
    public void search(int searchId) {
        callerIntent.putExtra("searchID", searchId);
        setResult(RESULT_OK, callerIntent);
        finish();
    }

    @Override
    public void deleteSearch(int searchId, int adapterPosition) {
        presenter.deleteSearch(searchId);
        adapter.notifyItemRemoved(adapterPosition);
    }
}