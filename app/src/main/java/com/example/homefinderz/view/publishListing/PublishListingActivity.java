package com.example.homefinderz.view.publishListing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homefinderz.R;
import com.example.homefinderz.view.Listing.ListingView;

public class PublishListingActivity extends AppCompatActivity implements ListingView {

    private static final int EMPTY_TEXT_FIELD = -685;

    private PublishListingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_listing);

        Button btnConfirm = (Button)findViewById(R.id.btn_plConfirm);
        Button btnCancel =  (Button)findViewById(R.id.btn_plCancel);

        Intent intent = getIntent();
        presenter = new PublishListingPresenter(this);
        presenter.setAccountID(intent.getIntExtra("accountID", -1));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.createListing();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public int getPrice() {
        String price = ((EditText)findViewById(R.id.et_plPrice)).getText().toString();
        if (price.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(price);
        }
    }

    @Override
    public int getSQM() {
        String sqm = ((EditText)findViewById(R.id.et_plSQM)).getText().toString();
        if (sqm.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(sqm);
        }
    }

    @Override
    public int getBedrooms() {
        String beds = ((EditText)findViewById(R.id.et_plBedrooms)).getText().toString();
        if (beds.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(beds);
        }
    }

    @Override
    public int getBathrooms() {
        String baths = ((EditText)findViewById(R.id.et_plBathrooms)).getText().toString();
        if (baths.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(baths);
        }
    }

    @Override
    public int getFloor() {
        String floor = ((EditText)findViewById(R.id.et_plFloor)).getText().toString();
        if (floor.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(floor);
        }
    }

    @Override
    public boolean getHeating() {
        return ((CheckBox)findViewById(R.id.checkBox_plHeating)).isChecked();
    }

    @Override
    public String getLocation() {
        return ((EditText)findViewById(R.id.et_plLocation)).getText().toString();
    }

    @Override
    public String getDescription() {
        return ((EditText)findViewById(R.id.et_plDescription)).getText().toString();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(PublishListingActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getApplicationContext(),"Listing published!", Toast.LENGTH_SHORT).show();
        finish();
    }

    //------------TEST PURPOSES ONLY--------------------------
    @Override
    public void setPrice(int price) {
        //stub for testing purposes
    }

    @Override
    public void setSquareMeters(int squareMeters) {
        //stub for testing purposes
    }

    @Override
    public void setBedrooms(int bedrooms) {
        //stub for testing purposes
    }

    @Override
    public void setBathrooms(int bathrooms) {
        //stub for testing purposes
    }

    @Override
    public void setFloor(int floor) {
        //stub for testing purposes
    }

    @Override
    public void setHeating(boolean heating) {
        //stub for testing purposes
    }

    @Override
    public void setLocation(String location) {
        //stub for testing purposes
    }

    @Override
    public void setDescription(String description) {
        //stub for testing purposes
    }
}