package com.example.test2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test2.R;
import com.example.test2.model.CountryModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SimpleViewHolder>{


    private LayoutInflater mInflater;
    private List<CountryModel> countryModelList;


    MainAdapter(Context context, List<CountryModel> countryModelList1) {
        this.mInflater = LayoutInflater.from(context);
        this.countryModelList = countryModelList1;
    }

    public void updatecountryModelList(List<CountryModel> countryModelList){
        this.countryModelList = countryModelList;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {

        CountryModel model = this.countryModelList.get(position);
        holder.capitalTextView.setText(model.getCapital());
        holder.nameTextView.setText(model.getName());
        holder.regionTextView.setText(model.getSubregion());
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView capitalTextView;
        private TextView nameTextView;
        private TextView regionTextView;


        public SimpleViewHolder(final View itemView) {
            super(itemView);
            capitalTextView = (TextView) itemView.findViewById(R.id.capital);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            regionTextView = (TextView) itemView.findViewById(R.id.region);


        }
    }
}
