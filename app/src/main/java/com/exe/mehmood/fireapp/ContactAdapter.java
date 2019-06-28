package com.exe.mehmood.fireapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends
        RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<User> usersList;
    private Context context;

    public ContactAdapter(List<User> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.userName.setText(user.getName());
        holder.userId.setText(user.getId());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_card, parent, false);
        return new MyViewHolder(v);
    }

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView userId;

        public MyViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.userName);
            userId = view.findViewById(R.id.userId);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        User clickedUser = usersList.get(pos);
                        Intent intent = new Intent(context, ChatActivity.class);
                        intent.putExtra("SelectedUser", clickedUser);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}