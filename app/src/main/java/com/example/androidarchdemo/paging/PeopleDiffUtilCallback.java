package com.example.androidarchdemo.paging;

import androidx.recyclerview.widget.DiffUtil;

import com.example.androidarchdemo.entity.ResultPeople;

import java.util.List;

public class PeopleDiffUtilCallback extends DiffUtil.Callback {

    private final List<ResultPeople> oldList;
    private final List<ResultPeople> newList;

    public PeopleDiffUtilCallback(List<ResultPeople> oldList, List<ResultPeople> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ResultPeople oldPeople = oldList.get(oldItemPosition);
        ResultPeople newPeople = newList.get(newItemPosition);
        return oldPeople.getName().equals(newPeople.getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ResultPeople oldPeople = oldList.get(oldItemPosition);
        ResultPeople newPeople = newList.get(newItemPosition);
        return oldPeople.getUrl().equals(newPeople.getUrl()) && oldPeople.getGender().equals(newPeople.getGender());
    }
}
