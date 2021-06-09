package com.example.homefinderz.view.accountUI;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.homefinderz.R;
import com.example.homefinderz.view.AcceptedListings.AcceptedListingsListActivity;
import com.example.homefinderz.view.Listing.ManageListingsActivity;
import com.example.homefinderz.view.login.LoginActivity;
import com.example.homefinderz.view.modifyAccount.ModifyAccountActivity;

public class AccountUIActivity extends AppCompatActivity implements AccountUIView{

    private Button btEdit, btPremium,btListings;
    private TextView logout;
    private ImageView backArrow;
    private TextView tvEmail;
    private String email;

    private AccountUIPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_ui);

        btEdit     = (Button) findViewById(R.id.bt_edit);
        btPremium  = (Button) findViewById(R.id.bt_upgrade);
        btListings = (Button) findViewById(R.id.bt_listings);
        logout     = (TextView) findViewById(R.id.logout);
        backArrow  = (ImageView) findViewById(R.id.back);

        presenter = new AccountUIPresenter(this);
        Intent intent = getIntent();
        presenter.setAccountLoggedIn(intent.getIntExtra("accountID", -1));

        TextView etFirstName = (TextView) findViewById(R.id.firstname);
        etFirstName.setText(presenter.getAcc().getUserName());

        TextView tvLastName = (TextView) findViewById(R.id.lastname);
        tvLastName.setText(presenter.getAcc().getUserSurname());

        tvEmail = (TextView) findViewById(R.id.email);
        tvEmail.setText(presenter.getAcc().getEmail());

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountUIActivity.this, ModifyAccountActivity.class);
                intent.putExtra("accountID", presenter.getAccountID());
                startActivity(intent);
            }
        });

        btListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountUIActivity.this, ManageListingsActivity.class);
                intent.putExtra("accountID", presenter.getAccountID());
                startActivity(intent);
            }
        });

        btPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upgrade();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountUIActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.ac_my_accepted_listings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitMyAcceptedListings();
            }
        });
    }

    private void visitMyAcceptedListings() {
        Intent intent = new Intent(AccountUIActivity.this, AcceptedListingsListActivity.class);
        intent.putExtra("accountID", presenter.getAccountID());
        startActivity(intent);
    }

    /**
     * this method is called when the user navigates back to Search Page from another page.
     * Since that other page may be the ModifyAccount page and since the user may have deleted their
     * account while in that page, we check if the account was deleted.
     */
    @Override
    public void onResume() {
        super.onResume();
        if(presenter.accountDeleted()) {
            finish();
        }
    }

    @Override
    public String getEmail() {
        email = tvEmail.getText().toString();
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPresenter(AccountUIPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * This method upgrades an account to premium
     * The method is called when the user presses the button "Go Premium"
     */
    private void upgrade() {
        if(presenter.checkPremium() == 0) {
            new AlertDialog.Builder(AccountUIActivity.this)
                    .setCancelable(true)
                    .setTitle("Gooooo Premium!!!!\n")
                    .setMessage("You are now a Premium user!")
                    .setPositiveButton(R.string.ok, null).create().show();
//            Toast.makeText(getApplicationContext(),"Gooooo Premium!!!! \nYou are now a Premium user!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Account already premium.", Toast.LENGTH_SHORT).show();
        }
    }
}
