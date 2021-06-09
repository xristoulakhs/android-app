package com.example.homefinderz.view.SavedSearches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homefinderz.R;
import com.example.homefinderz.model.Search;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public class SavedSearchesAdapter extends RecyclerView.Adapter<SavedSearchesAdapter.SearchesItemViewHolder>{

    private List<Search> searchesList;
    private Search search;
    private SavedSearchesView view;

    public class SearchesItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvID;
        private TextView tvMinPrice;
        private TextView tvMaxPrice;
        private TextView tvMinSQM;
        private TextView tvMaxSQM;
        private TextView tvLocation;
        private TextView tvHeating;

        public SearchesItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvID       = (TextView)itemView.findViewById(R.id.tv_ssi_ID);
            tvMinPrice = (TextView)itemView.findViewById(R.id.tv_ssi_minPrice);
            tvMaxPrice = (TextView)itemView.findViewById(R.id.tv_ssi_maxPrice);
            tvMinSQM   = (TextView)itemView.findViewById(R.id.tv_ssi_minSQM);
            tvMaxSQM   = (TextView)itemView.findViewById(R.id.tv_ssi_maxSQM);
            tvLocation = (TextView)itemView.findViewById(R.id.tv_ssi_Location);
            tvHeating  = (TextView)itemView.findViewById(R.id.tv_ssi_Heating);

            (itemView.findViewById(R.id.btn_ssi_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteSearch(getAdapterPosition());
                }
            });
            (itemView.findViewById(R.id.btn_ssi_search)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    executeSearch();
                }
            });
        }

        public TextView getTvID() {
            return tvID;
        }

        public TextView getTvMinPrice() {
            return tvMinPrice;
        }

        public TextView getTvMaxPrice() {
            return tvMaxPrice;
        }

        public TextView getTvMinSQM() {
            return tvMinSQM;
        }

        public TextView getTvMaxSQM() {
            return tvMaxSQM;
        }

        public TextView getTvLocation() {
            return tvLocation;
        }

        public TextView getTvHeating() {
            return tvHeating;
        }
    }

    public SavedSearchesAdapter(List<Search> storedSearches, SavedSearchesActivity savedSearchesActivity) {
        this.searchesList = storedSearches;
        this.view = savedSearchesActivity;
    }

    @NonNull
    @NotNull
    @Override
    public SearchesItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View storedSearchesView = inflater.inflate(R.layout.stored_searches_item, parent, false);

        return new SavedSearchesAdapter.SearchesItemViewHolder(storedSearchesView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchesItemViewHolder holder, int position) {
        search = searchesList.get(position);

        holder.getTvID().setText(String.valueOf(search.getSearchId()));
        holder.getTvMinPrice().setText(String.valueOf(search.getMinPrice()));
        holder.getTvMaxPrice().setText(String.valueOf(search.getMaxPrice()));
        holder.getTvMinSQM().setText(String.valueOf(search.getMinSqm()));
        holder.getTvMaxSQM().setText(String.valueOf(search.getMaxSqm()));
        holder.getTvLocation().setText(search.getLocation());
        if (search.getHeat()) {
            holder.getTvHeating().setText(R.string.ssi_heating);
        }
        else {
            holder.getTvHeating().setText(R.string.ssi_noHeating);
        }

    }

    @Override
    public int getItemCount() {
        return searchesList.size();
    }

    protected void executeSearch() {
        view.search(search.getSearchId());
    }
    protected void deleteSearch(int adapterPosition) {
        view.deleteSearch(search.getSearchId(), adapterPosition);
    }
}
