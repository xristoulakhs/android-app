package com.example.homefinderz.view.SearchHistory;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homefinderz.R;
import com.example.homefinderz.model.ArchivedSearch;

import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryItemViewHolder> {

    private ArchivedSearch archivedSearch;
    private List<ArchivedSearch> history;
    private SearchHistoryView view;

    public class SearchHistoryItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvSearchID;
        TextView tvDate;
        TextView tvMinPrice;
        TextView tvMaxPrice;

        public SearchHistoryItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvSearchID = itemView.findViewById(R.id.tv_sh_id);
            tvDate     = itemView.findViewById(R.id.tv_sh_date);
            tvMinPrice = itemView.findViewById(R.id.tv_sh_minPrice);
            tvMaxPrice = itemView.findViewById(R.id.tv_sh_maxPrice);
        }

        public TextView getTvSearchID() {
            return tvSearchID;
        }

        public TextView getTvDate() {
            return tvDate;
        }

        public TextView getTvMinPrice() {
            return tvMinPrice;
        }

        public TextView getTvMaxPrice() {
            return tvMaxPrice;
        }
    }

    public SearchHistoryAdapter(List<ArchivedSearch> history, SearchHistoryView view) {
        this.history = history;
        this.view = view;
    }

    @NonNull
    @NotNull
    @Override
    public SearchHistoryItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View searchHistoryView = inflater.inflate(R.layout.search_history_item, parent, false);

        return new SearchHistoryAdapter.SearchHistoryItemViewHolder(searchHistoryView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchHistoryItemViewHolder holder, int position) {
        archivedSearch = history.get(position);

        holder.getTvSearchID().setText(String.valueOf(archivedSearch.getSearchId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = archivedSearch.getSearchTime().format(formatter);
        holder.getTvDate().setText(formattedString);
        holder.getTvMinPrice().setText(String.valueOf(archivedSearch.getMinPrice()));
        holder.getTvMaxPrice().setText(String.valueOf(archivedSearch.getMaxPrice()));

    }

    @Override
    public int getItemCount() {
        return history.size();
    }

}
