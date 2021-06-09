package com.example.homefinderz.view.search;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.homefinderz.R;
import com.example.homefinderz.model.Listing;

public class PopUpClass {

    //PopupWindow display method
    Listing listing;
    View parentView;

    public void showPopupWindow(final View view, Listing data, final SearchPresenter presenter) {


        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler
        listing = data;
        parentView = view;

        TextView tvLocation = popupView.findViewById(R.id.popupLocation);
        TextView tvPrice = popupView.findViewById(R.id.popupPrice);
        TextView tvSQM = popupView.findViewById(R.id.popupSQM);
        TextView tvDescription = popupView.findViewById(R.id.popupDescription);

        tvLocation.setText(listing.getLocation());
        tvPrice.setText(String.valueOf(listing.getPrice()));
        tvSQM.setText(String.valueOf(listing.getSqm()));
        tvDescription.setText(listing.getDescription());

        Button skip = popupView.findViewById(R.id.popupSkip);
        Button accept = popupView.findViewById(R.id.popupAccept);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addAcceptedListing(listing);
                popupWindow.dismiss();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });



        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked. We don't want it to do anything here
                //popupWindow.dismiss();
                return true;
            }
        });
    }

}
