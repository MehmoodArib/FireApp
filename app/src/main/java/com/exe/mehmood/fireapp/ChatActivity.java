package com.exe.mehmood.fireapp;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    private String chatId;

    private FirebaseListAdapter<Message> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        final User selectedUser = getIntent().getParcelableExtra("SelectedUser");
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        setTitle(selectedUser.getName());
        chatId = makeChatId(selectedUser.getId(), firebaseUser.getUid());

        FloatingActionButton fab = findViewById(R.id.sendMessageButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.editText);
                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                String message = input.getText().toString().trim();
                if (message.length() > 0) {
                    FirebaseDatabase.getInstance()
                            .getReference().child("messages").child(chatId)
                            .push()
                            .setValue(new Message(message,
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName())
                            );
                    // Clear the input
                    input.setText("");
                }
            }
        });


        final ListView listOfMessages = findViewById(R.id.chatListView);
        adapter = new FirebaseListAdapter<Message>(this, Message.class,
                R.layout.message_card, FirebaseDatabase.getInstance().getReference().child("messages").child(chatId)) {
            @Override
            protected void populateView(View v, Message model, int position) {
                // Get references to the views of message.xml
                TextView messageText = v.findViewById(R.id.editTextMessage);
                TextView messageSender = v.findViewById(R.id.editTextSender);
                TextView messageTime = v.findViewById(R.id.editTextTime);

                // Set their text
                messageText.setText(model.getMessageText());
                messageSender.setText(model.getMessageSender());

                if (model.getMessageSender().equals(firebaseUser.getDisplayName()))
                    messageSender.setTextColor(getResources().getColor(R.color.lime));
                if (model.getMessageSender().equals(selectedUser.getName()))
                    messageSender.setTextColor(getResources().getColor(R.color.aqua));

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
                listOfMessages.setSelection(listOfMessages.getAdapter().getCount() - 1);
            }
        };

        listOfMessages.setAdapter(adapter);
    }


    public String makeChatId(String selectedUserId, String MyUserId) {
        if (MyUserId.compareTo(selectedUserId) > 0) return MyUserId.concat(selectedUserId);
        else return selectedUserId.concat(MyUserId);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.deleteChatButton:
                FirebaseDatabase.getInstance().getReference().child("messages").child(chatId).removeValue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
