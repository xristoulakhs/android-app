package com.example.homefinderz.view.Listing;

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

import java.util.List;


public class ListingItemAdapter extends RecyclerView.Adapter<ListingItemAdapter.ListingItemViewHolder>{

    private List<Listing> listings;
    private Listing lis;
    private ManageListingsView view;

    public class ListingItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPrice;
        private TextView tvSQM;
        private TextView tvLocation;

        public ListingItemViewHolder(View itemView) {
            super(itemView);

            tvPrice    = (TextView)itemView.findViewById(R.id.tv_lisPrice);
            tvSQM      = (TextView)itemView.findViewById(R.id.tv_lisSQM);
            tvLocation = (TextView)itemView.findViewById(R.id.tv_lisLoc);

            ImageView image = (ImageView)itemView.findViewById(R.id.item_image);

            Button btnEdit   = (Button)itemView.findViewById(R.id.btnEditLItem);
            Button btnDelete = (Button)itemView.findViewById(R.id.btnLItemDelete);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifyListing(getAdapterPosition());
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteListing(getAdapterPosition());
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

    public ListingItemAdapter(List<Listing> listings, ManageListingsView view) {
        this.listings = listings;
        this.view = view;
    }

    @NonNull
    @Override
    public ListingItemAdapter.ListingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listingView = inflater.inflate(R.layout.listing_list_item, parent, false);

        return new ListingItemViewHolder(listingView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListingItemAdapter.ListingItemViewHolder holder, int position) {
        lis = listings.get(position);

        holder.getTvPrice().setText(String.valueOf(lis.getPrice()));
        holder.getTvSQM().setText(String.valueOf(lis.getSqm()));
        holder.getTvLocation().setText(lis.getLocation());
    }

    @Override
    public int getItemCount() {
        return listings.size();
    }

    /**
     * Method used to request the modification of a specific listing
     * @param adapterPosition the position of the listing in the data set the adapter uses
     */
    public void modifyListing(int adapterPosition) {
        view.modifyListing(lis.getId(), adapterPosition);
    }

    /**
     * Method used to request the deletion of a specific listing
     * @param adapterPosition the position of the listing in the data set the adapter uses
     */
    public void deleteListing(int adapterPosition) {
        view.deleteListing(lis.getId(), adapterPosition);
    }
}
