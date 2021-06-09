package com.example.homefinderz.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;


public class ArchivedSearch extends Search{

    private LocalDateTime searchTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArchivedSearch(Search executedSearch) {
        super(executedSearch.getSearchId(), executedSearch.getMinPrice(), executedSearch.getMaxPrice(),
                executedSearch.getLocation(), executedSearch.getMaxSqm(), executedSearch.getMinSqm(),
                executedSearch.getBedrooms(), executedSearch.getBathrooms(), executedSearch.getFloor(), executedSearch.getHeat());
        this.searchTime = LocalDateTime.now();
    }

    public LocalDateTime getSearchTime() {
        return searchTime;
    }

}
