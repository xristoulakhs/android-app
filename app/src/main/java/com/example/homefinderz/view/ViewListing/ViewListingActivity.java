package com.example.homefinderz.view.ViewListing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.homefinderz.R;

public class ViewListingActivity extends AppCompatActivity implements ViewListingView{

    ViewListingPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listing);

        Intent intent = getIntent();
        presenter = new ViewListingPresenter(this);
        presenter.setAccountID(intent.getIntExtra("accountID", -1));
        presenter.setListingID(intent.getIntExtra("listingID", -1));

        ((TextView)findViewById(R.id.tv_vl_ID2)).setText(presenter.getID());
        ((TextView)findViewById(R.id.tv_vl_Price2)).setText(presenter.getPrice());
        ((TextView)findViewById(R.id.tv_vl_Location2)).setText(presenter.getLocation());
        ((TextView)findViewById(R.id.tv_vl_beds2)).setText(presenter.getBeds());
        ((TextView)findViewById(R.id.tv_vl_baths2)).setText(presenter.getBaths());
        ((TextView)findViewById(R.id.tv_vl_heating2)).setText(presenter.getHeat());
        ((TextView)findViewById(R.id.tv_vl_desc2)).setText(presenter.getDescription());
        ((TextView)findViewById(R.id.tv_vl_SQM2)).setText(presenter.getSQM());
        ((TextView)findViewById(R.id.tv_vl_floor2)).setText(presenter.getFloor());
    }
}