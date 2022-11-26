package com.nejidev.mybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ViewActivity extends AppCompatActivity {
    private static final String TAG = "MyBook";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        String file_path = intent.getStringExtra("file_path");
        Log.i(TAG, "view file_path:" + file_path);

        TextView content_text_view = findViewById(R.id.content_text_view);
        try {
            InputStream file_stream = getAssets().open(file_path);
            InputStreamReader inputReader = new InputStreamReader(file_stream);
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String text = "";
            while(null != (line = bufReader.readLine())){
                text += line + "\n";
            }
            Log.i(TAG, "view text:" + text);
            content_text_view.setText(text);
            bufReader.close();
            inputReader.close();
            file_stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
