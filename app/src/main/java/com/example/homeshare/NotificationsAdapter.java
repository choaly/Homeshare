package com.example.homeshare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.io.*;

public class NotificationsAdapter extends RecyclerView.Adapter{

    ArrayList<Object> notifs;
    final static int RESPONSE_VIEW=1;
    final static int MATCHED_VIEW=2;

    public NotificationsAdapter(ArrayList<Object> notifs) {
        this.notifs = notifs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case RESPONSE_VIEW:return new ResponseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_responded_notif,parent,false));
            case MATCHED_VIEW:return new MatchedHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.match_user_notif,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(notifs.get(position) instanceof ResponseNotif)
            ((ResponseHolder)holder).Bind((ResponseNotif)notifs.get(position));
        if(notifs.get(position) instanceof MatchedNotif)
            ((MatchedHolder)holder).Bind((MatchedNotif)notifs.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(notifs.get(position)instanceof ResponseNotif)
            return RESPONSE_VIEW;
        else
            return MATCHED_VIEW;
    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }

    public class ResponseHolder extends RecyclerView.ViewHolder {
        TextView name,title;

        public ResponseHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.responseNotifName);
            title=itemView.findViewById(R.id.responseNotifPostTitle);

        }
        public void Bind(ResponseNotif n)
        {
            String nameStr =  n.getResponderName() +  " sent you a response!";
            name.setText(nameStr);
            String titleStr = "For \"" + n.getPostTitle()   + "\" listing";
            title.setText(titleStr);
        }
    }

    public class MatchedHolder extends RecyclerView.ViewHolder {
        TextView mC;
        public MatchedHolder(@NonNull View itemView) {
            super(itemView);
            mC=itemView.findViewById(R.id.matchedContact);
        }
        public void Bind(MatchedNotif n)
        {
            String mcStr = "Contact "+ n.getMatchedName() + " at " + n.getContact();
            mC.setText(mcStr);
        }
    }

}
