package edu.calvin.cs262.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewPlayerActivity extends AppCompatActivity {

    public static final int NEW_PLAYER_ACTIVITY_REQUEST_CODE = 1;
    private EditText editName;
    private EditText editEmail;
    private EditText editID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.email);
        editID = findViewById(R.id.ID);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editName.getText()) ||
                        TextUtils.isEmpty(editEmail.getText()) ||
                        TextUtils.isEmpty(editID.getText())
                ) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = editName.getText().toString();
                    String ID = editID.getText().toString();
                    String email = editEmail.getText().toString();

                    replyIntent.putExtra("ID", ID);
                    replyIntent.putExtra("Name", name);
                    replyIntent.putExtra("Email", email);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
