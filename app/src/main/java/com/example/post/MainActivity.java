package com.example.post;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private ImageView shareIcon;
    private ImageView likeIcon;
    private ImageView downloadIcon;
    private ImageView commentIcon;
    private int likeCount=0;
    private TextView likeCountText;
    private int commentCount=0;
    private TextView commentCountText;
    private Handler handler = new Handler();
    private Runnable resetBackgroundRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        shareIcon = findViewById(R.id.share_icon);
        likeIcon = findViewById(R.id.like_icon);
        downloadIcon = findViewById(R.id.download_icon);
        commentIcon = findViewById(R.id.comment_icon);
        likeCountText = findViewById(R.id.like);
        commentCountText = findViewById(R.id.comment);

        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Sharelink();
            }

        });
        likeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeCount++;
                likeCountText.setText(String.valueOf(likeCount));


            }
        });
        commentIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();

            }
        });
        downloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Download proceeding", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Sharelink() {
        String imageUrl = "https://www.example.com/path/to/image.jpg";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain"); // Setting MIME type for text content
        intent.putExtra(Intent.EXTRA_TEXT, imageUrl);

        Intent chooser = Intent.createChooser(intent, "Share Image Link");
        startActivity(chooser);
    }



    private void showCommentDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_comment, null);
            builder.setView(dialogView);

            final EditText commentInput = dialogView.findViewById(R.id.comment_input);

            builder.setTitle("Write a Comment")
                    .setPositiveButton("Send", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            String comment = commentInput.getText().toString();
                            if (!comment.isEmpty()) {
                                commentCount++;
                                commentCountText.setText(String.valueOf(commentCount));
                                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                                intent.putExtra("message", comment);

                                dialog.dismiss();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
        }


    }





