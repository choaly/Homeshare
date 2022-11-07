package com.example.homeshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.MyViewHolder>{

    Context context;
    ArrayList<Response> list;
    private OnResponseListener onResponseListenerG;

    public ResponseAdapter(Context context, ArrayList<Response> list, OnResponseListener onResponseListener) {
        this.context = context;
        this.list = list;
        this.onResponseListenerG = onResponseListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.response_item, parent, false);
        return new MyViewHolder(v, onResponseListenerG);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Response r = list.get(position);
        holder.name.setText(r.getName());
        holder.message.setText(r.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, message;
        OnResponseListener onResponseListener;

        public MyViewHolder(@NonNull View itemView, OnResponseListener onResponseListener) {
            super(itemView);
            this.onResponseListener = onResponseListener;

            name = itemView.findViewById(R.id.responderName);
            message = itemView.findViewById(R.id.responderMessage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onResponseListener.onResponseClick( getAdapterPosition() );
        }
    }

    public interface OnResponseListener{
        void onResponseClick( int position );
    }
}
