package com.example.post;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private TextView commentCountTextView;
    private EditText commentInputEditText;
    private Button sendCommentButton;
    private RecyclerView commentRecyclerView;
    private CommentAdapter commentAdapter;
    private ArrayList<String> comments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        commentCountTextView = findViewById(R.id.comment_count);
        commentInputEditText = findViewById(R.id.comment_input);
        sendCommentButton = findViewById(R.id.send_comment);
        commentRecyclerView = findViewById(R.id.comment_list);

        comments = new ArrayList<>();
        commentAdapter = new CommentAdapter(comments);

        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentAdapter);

        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commentInputEditText.getText().toString().trim();
                if (!commentText.isEmpty()) {
                    comments.add(commentText);
                    commentAdapter.notifyItemInserted(comments.size() - 1);
                    commentInputEditText.setText("");
                    updateCommentCount();
                }
            }
        });
    }

    private void updateCommentCount() {
        commentCountTextView.setText("Comments: " + comments.size());
    }
}
