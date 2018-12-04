package com.example.fox.app.ui.main;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox.app.R;
import com.example.fox.app.data.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public MainAdapter(List<Item> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(String title, String message);
    }

    private List<Item> items;
    private Listener listener;

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_view, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.itemTextTitle.setText(item.getTitle());
        holder.itemTextMessage.setText(item.getMessage());
        String url = "https://cs22.babysfera.ru/1/8/1/0/114624047.69.jpeg";
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_hourglass)
                .into(holder.itemImageView);
        holder.constraintLayout.setOnClickListener(v -> listener.onItemClicked(item.getTitle(), item.getMessage()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextTitle;
        TextView itemTextMessage;
        ImageView itemImageView;
        ConstraintLayout constraintLayout;

        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.item_imageview);
            constraintLayout = itemView.findViewById(R.id.item_row_layout);
            itemTextTitle = itemView.findViewById(R.id.item_title);
            itemTextMessage = itemView.findViewById(R.id.item_message);
        }
    }
}
