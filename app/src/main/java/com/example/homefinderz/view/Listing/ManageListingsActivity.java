package com.example.homefinderz.view.Listing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homefinderz.R;
import com.example.homefinderz.view.modifyListing.ModifyListingActivity;
import com.example.homefinderz.view.publishListing.PublishListingActivity;

public class ManageListingsActivity extends AppCompatActivity implements ManageListingsView {

    private ManageListingsPresenter presenter;
    private ListingItemAdapter adapter;
    private Button btnNewListing;
    private int modifiedPosition;
    private boolean modified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_listings);

        presenter = new ManageListingsPresenter(ManageListingsActivity.this);
        Intent intent = getIntent();
        presenter.setAccountLoggedIn(intent.getIntExtra("accountID", -1));

        RecyclerView rvListings = (RecyclerView) findViewById(R.id.rv_mngLRecycler);
        adapter = new ListingItemAdapter(presenter.getListings(), this);
        rvListings.setAdapter(adapter);
        rvListings.setLayoutManager(new LinearLayoutManager(this));

        btnNewListing = (Button) findViewById(R.id.btnNewListing);
        btnNewListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishListing();
            }
        });
    }

    private void publishListing() {
        Intent intent = new Intent(this, PublishListingActivity.class);
        intent.putExtra("accountID", presenter.getAccountID());
        startActivity(intent);
    }

    /**
     * This method is called when the user presses the button "ADD HERE" !
     * @param listingID is the listing to be deleted
     */
    @Override
    public void deleteListing(int listingID, int position) {
        presenter.deleteListing(listingID);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void modifyListing(int listingID, int position) {
        Intent intent = new Intent(ManageListingsActivity.this, ModifyListingActivity.class);
        intent.putExtra("accountID", presenter.getAccountID());
        intent.putExtra("listingID", listingID);
        modified = true;
        modifiedPosition = position;
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(modified) {
            adapter.notifyItemChanged(modifiedPosition);
            modified = false;
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }
}