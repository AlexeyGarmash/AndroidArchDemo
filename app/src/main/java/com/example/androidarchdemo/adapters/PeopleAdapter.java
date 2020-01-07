package com.example.androidarchdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidarchdemo.R;
import com.example.androidarchdemo.entity.ResultPeople;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleAdapter extends PagedListAdapter<ResultPeople, PeopleAdapter.PeopleViewHolder> {

    class PeopleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvPeopleName)
        TextView tvPeopleName;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(ResultPeople people){
            tvPeopleName.setText(people.getName());
        }
    }

    public PeopleAdapter(@NonNull DiffUtil.ItemCallback<ResultPeople> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_item, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


}
