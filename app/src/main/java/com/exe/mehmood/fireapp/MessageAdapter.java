package com.exe.mehmood.fireapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Message> messagesList;

    public MessageAdapter(List<Message> messagesList) {
        this.messagesList = messagesList;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Message message = messagesList.get(position);
        holder.messageSender.setText(message.getMessageSender());
        holder.messageText.setText(message.getMessageText());
        holder.messageTime.setText(String.valueOf(message.getMessageTime()));
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
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
        public TextView messageSender;
        public TextView messageText;
        public TextView messageTime;


        public MyViewHolder(View view) {
            super(view);
            messageSender = view.findViewById(R.id.editTextSender);
            messageText = view.findViewById(R.id.editTextMessage);
            messageTime = view.findViewById(R.id.editTextTime);
        }
    }
}