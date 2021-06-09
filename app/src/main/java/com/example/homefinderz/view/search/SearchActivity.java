package com.example.homefinderz.view.search;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.homefinderz.R;
import com.example.homefinderz.view.SavedSearches.SavedSearchesActivity;
import com.example.homefinderz.view.accountUI.AccountUIActivity;


public class SearchActivity extends AppCompatActivity implements SearchView {

    private SearchPresenter presenter;
    private Spinner spinner;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    private static final int EMPTY_TEXT_FIELD = -1;
    private static final String TAG = "Searching";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            presenter.searchSaved(result.getData() != null ? result.getData().getIntExtra("searchID", -1) : -1);
                        }
                    }
                });

        spinner = (Spinner)findViewById(R.id.dropdownLocation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("SOMETHING");
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);
        Button searchButton = (Button)findViewById(R.id.sp_bt_search);

        presenter = new SearchPresenter(this);
        Intent intent = getIntent();
        presenter.setAccount(intent.getIntExtra("accountID", -1));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(SearchActivity.this, view);
                presenter.doSearch();
            }
        });

        findViewById(R.id.sp_bt_myProfile).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onVisitProfile();
            }
        });
        findViewById(R.id.sp_bt_save_this_search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.saveSearch();
            }
        });
        findViewById(R.id.sp_bt_my_saved_searches).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showSavedSearches();
            }
        });
    }

    private void showSavedSearches() {
        Intent intent = new Intent(this, SavedSearchesActivity.class);
        intent.putExtra("accountID", presenter.getAccountLoggedIn());
        activityResultLauncher.launch(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(presenter.accountDeleted()) {
            finish();
        }
    }

    /**
     * this method displays the search results on the screen. Each result is depicted in a popup
     * window.
     */
    @Override
    public void showResults() {
        if(presenter.hasNextResult()) {
            while (presenter.hasNextResult()) {
                PopUpClass popUp = new PopUpClass();
                popUp.showPopupWindow(findViewById(R.id.sp_bt_search), presenter.getNextResult(), presenter);
                if(!presenter.hasNextResult()) {
                    Toast.makeText(getApplicationContext(),"Reached the end of your search", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"No Listings match your search", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSavedMessage() {
        Toast.makeText(getApplicationContext(),"Search Saved!!!", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is called when the user presses the "My Profile" button.
     * It navigates the users to their Account Profile page.
     */
    public void onVisitProfile() {
        Intent intent = new Intent(this, AccountUIActivity.class);
        intent.putExtra("accountID", presenter.getAccountLoggedIn());
        startActivity(intent);
    }

    @Override
    public void setMinPrice(int price) {
        //stub
    }

    @Override
    public void setMaxPrice(int price) {
        //stub
    }

    @Override
    public void setMinSQM(int sqm) {
        //stub
    }

    @Override
    public void setMaxSQM(int sqm) {
        //stub
    }

    @Override
    public void setBedrooms(int beds) {
        //stub
    }

    @Override
    public void setBathrooms(int baths) {
        //stub
    }

    @Override
    public void setFloor(int floor) {
        //stub
    }

    @Override
    public void setHeating(boolean heat) {
        //stub
    }

    @Override
    public void setLocation(String location) {
        //stub
    }

    /**
     * Method to hide the keyboard.
     * Called when the search button is pressed
     * @param activity the activity that calls it
     * @param view the view passed to the onClick method
     */
    public static void hideSoftKeyboard (SearchActivity activity, View view) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    @Override
    public int getMinPrice() {
        String minPrice = ((EditText)findViewById(R.id.sp_in_minPrice)).getText().toString();
        if (minPrice.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(minPrice);
        }
    }

    @Override
    public int getMaxPrice(){
        String maxPrice = ((EditText)findViewById(R.id.sp_in_maxPrice)).getText().toString();
        if (maxPrice.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(maxPrice);
        }
    }

    @Override
    public int getMinSQM() {
        String minSQM = ((EditText)findViewById(R.id.sp_in_minSQM)).getText().toString();
        if (minSQM.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(minSQM);
        }
    }

    @Override
    public int getMaxSQM(){
        String maxSQM = ((EditText)findViewById(R.id.sp_in_maxsqm)).getText().toString();
        if (maxSQM.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(maxSQM);
        }
    }

    @Override
    public int getBedrooms() {
        String bedrooms = ((EditText)findViewById(R.id.sp_in_bedrooms)).getText().toString();
        if (bedrooms.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(bedrooms);
        }
    }

    @Override
    public int getBathrooms() {
        String bathrooms = ((EditText)findViewById(R.id.sp_in_bathrooms)).getText().toString();
        if (bathrooms.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(bathrooms);
        }
    }

    @Override
    public int getFloor() {
        //TODO: if we have time put a "max floor" field
        String floor = ((EditText)findViewById(R.id.sp_in_minFloor)).getText().toString();
        if (floor.equals("")) {
            return EMPTY_TEXT_FIELD;
        }
        else {
            return Integer.parseInt(floor);
        }
    }

    @Override
    public boolean getHeating() {
        boolean heating = ((CheckBox)findViewById(R.id.checkBoxHeating)).isChecked();
        return heating;
    }

    @Override
    public String getLocation() {
        return "Athens";
        //TODO: get value from dropdown
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getApplicationContext(),"Error something went wrong!", Toast.LENGTH_SHORT).show();
    }
}