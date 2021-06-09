package com.example.homefinderz.view.modifyListing;

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


public class ModifyListingActivity extends AppCompatActivity implements ListingView {

    public static final int EMPTY_TEXT_FIELD = 2147483647;

    private ModifyListingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_listing);

        Intent intent = getIntent();
        presenter = new ModifyListingPresenter(this);
        presenter.setAccountID(intent.getIntExtra("accountID", -1));
        presenter.setListingID(intent.getIntExtra("listingID", -1));

        EditText etPrice        = (EditText) findViewById(R.id.et_mlPrice);
        EditText etLocation     = (EditText) findViewById(R.id.et_mlLocation);
        EditText etDescription  = (EditText) findViewById(R.id.et_mlDescription);
        EditText etSquareMeters = (EditText) findViewById(R.id.et_mlSQM);
        EditText etBedrooms     = (EditText) findViewById(R.id.et_mlBedrooms);
        EditText etBathrooms    = (EditText) findViewById(R.id.et_mlBathrooms);
        EditText etFloor        = (EditText) findViewById(R.id.et_mlFloor);

        etPrice.setHint(String.valueOf(presenter.getPrice()));
        etLocation.setHint(presenter.getLocation());
        etDescription.setHint(presenter.getDescription());
        etSquareMeters.setHint(String.valueOf(presenter.getSQM()));
        etBedrooms.setHint(String.valueOf(presenter.getBeds()));
        etBathrooms.setHint(String.valueOf(presenter.getBaths()));
        etFloor.setHint(String.valueOf(presenter.getFloor()));


        CheckBox heating  = (CheckBox) findViewById(R.id.checkBox_mlHeating);
        heating.setChecked(presenter.getHeat());

        Button btnConfirm = (Button) findViewById(R.id.btn_mlConfirm);
        Button btnCancel  = (Button) findViewById(R.id.btn_mlCancel);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveChanges();
                finish();
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
        String price = ((EditText)findViewById(R.id.et_mlPrice)).getText().toString();
        if (price.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(price);
        }
    }

    @Override
    public int getSQM() {
        String sqm = ((EditText)findViewById(R.id.et_mlSQM)).getText().toString();
        if (sqm.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(sqm);
        }
    }

    @Override
    public int getBedrooms() {
        String beds = ((EditText)findViewById(R.id.et_mlBedrooms)).getText().toString();
        if (beds.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(beds);
        }
    }

    @Override
    public int getBathrooms() {
        String baths = ((EditText)findViewById(R.id.et_mlBathrooms)).getText().toString();
        if (baths.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(baths);
        }
    }

    @Override
    public int getFloor() {
        String floor = ((EditText)findViewById(R.id.et_mlFloor)).getText().toString();
        if (floor.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(floor);
        }
    }

    @Override
    public boolean getHeating() {
        return ((CheckBox)findViewById(R.id.checkBox_mlHeating)).isChecked();
    }

    @Override
    public String getLocation() {
        return ((EditText)findViewById(R.id.et_mlLocation)).getText().toString();
    }

    @Override
    public String getDescription() {
        return ((EditText)findViewById(R.id.et_mlDescription)).getText().toString();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(ModifyListingActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getApplicationContext(),"Listing modified!", Toast.LENGTH_SHORT).show();
        finish();
    }

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