package com.example.homeshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.lang.String;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.MyViewHolder>{

    Context context;
    ArrayList<Listing> list;
    private OnListingListener onListingListenerG;

    public ListingAdapter(Context context, ArrayList<Listing> list, OnListingListener onListingListener) {
        this.context = context;
        this.list = list;
        this.onListingListenerG = onListingListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listing_item, parent, false);
        return new MyViewHolder(v, onListingListenerG);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Listing l = list.get(position);
        holder.postTitle.setText(l.getTitle());
        String nameText = "Posted by " + l.getPosterName();
        holder.name.setText(nameText);
        holder.address.setText(l.getAddress());
        String roomsText = l.getNumBed() + "B" + l.getNumBath() + "B";
        holder.rooms.setText(roomsText);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView postTitle, name, address, rooms;
        OnListingListener onListingListener;

        public MyViewHolder(@NonNull View itemView, OnListingListener onListingListener) {
            super(itemView);
            this.onListingListener = onListingListener;

            postTitle = itemView.findViewById(R.id.listingPostTitle);
            name = itemView.findViewById(R.id.posterName);
            address = itemView.findViewById(R.id.listingAddress);
            rooms = itemView.findViewById(R.id.listingRooms);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListingListener.onListingClick( getAdapterPosition() );
        }
    }

    public interface OnListingListener{
        void onListingClick( int position );
    }
}
