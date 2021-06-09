package com.example.homefinderz.view.AcceptedListings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.homefinderz.R;
import com.example.homefinderz.view.ViewListing.ViewListingActivity;

public class AcceptedListingsListActivity extends AppCompatActivity implements AcceptedListingsListView {

    AcceptedListingsListPresenter presenter;
    AcceptedListingsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_listings_list);

        presenter = new AcceptedListingsListPresenter(AcceptedListingsListActivity.this);
        Intent intent = getIntent();
        presenter.setAccountID(intent.getIntExtra("accountID", -1));

        RecyclerView rvListings = (RecyclerView) findViewById(R.id.rv_al_Recycler);
        adapter = new AcceptedListingsListAdapter(presenter.getAcceptedListings(), this);
        rvListings.setAdapter(adapter);
        rvListings.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void deleteAccepted(int id, int adapterPosition) {
        presenter.deleteApprovedListing(id);
        adapter.notifyItemRemoved(adapterPosition);
    }

    @Override
    public void viewListingDetails(int id) {
        Intent intent = new Intent(AcceptedListingsListActivity.this, ViewListingActivity.class);
        intent.putExtra("accountID", presenter.getAccountID());
        intent.putExtra("listingID", id);
        startActivity(intent);
    }
}