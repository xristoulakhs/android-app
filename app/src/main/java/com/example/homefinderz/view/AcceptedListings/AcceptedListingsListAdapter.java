package com.example.homefinderz.view.AcceptedListings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homefinderz.R;
import com.example.homefinderz.model.Listing;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AcceptedListingsListAdapter extends RecyclerView.Adapter<AcceptedListingsListAdapter.AcceptedListingItemViewHolder> {

    private List<Listing> listings;
    private Listing lis;
    private AcceptedListingsListView view;

    public class AcceptedListingItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPrice;
        private TextView tvSQM;
        private TextView tvLocation;

        public AcceptedListingItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvPrice    = (TextView)itemView.findViewById(R.id.tv_al_Price);
            tvSQM      = (TextView)itemView.findViewById(R.id.tv_al_SQM);
            tvLocation = (TextView)itemView.findViewById(R.id.tv_al_Location);

            ImageView image = (ImageView)itemView.findViewById(R.id.img_al_Image);

            Button btnDetails = (Button)itemView.findViewById(R.id.btn_al_ViewItem);
            Button btnDelete  = (Button)itemView.findViewById(R.id.btn_al_Delete);

            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewListingDetails();
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteAcceptedListing(getAdapterPosition());
                }
            });
        }

        public TextView getTvPrice() {
            return tvPrice;
        }
        public TextView getTvSQM() {
            return tvSQM;
        }
        public TextView getTvLocation() {
            return tvLocation;
        }
    }

    public AcceptedListingsListAdapter(List<Listing> listings, AcceptedListingsListView view) {
        this.listings = listings;
        this.view = view;
    }

    private void viewListingDetails() {
        view.viewListingDetails(lis.getId());
    }

    private void deleteAcceptedListing(int adapterPosition) {
        view.deleteAccepted(lis.getId(), adapterPosition);
    }


    @NonNull
    @NotNull
    @Override
    public AcceptedListingItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listingView = inflater.inflate(R.layout.accepted_listing_list_item, parent, false);

        return new AcceptedListingsListAdapter.AcceptedListingItemViewHolder(listingView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AcceptedListingItemViewHolder holder, int position) {
        lis = listings.get(position);

        holder.getTvPrice().setText(String.valueOf(lis.getPrice()));
        holder.getTvSQM().setText(String.valueOf(lis.getSqm()));
        holder.getTvLocation().setText(lis.getLocation());
    }

    @Override
    public int getItemCount() {
        return listings.size();
    }


}
