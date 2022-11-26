package com.nejidev.mybook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyBook";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load_assets_file_list("");
    }

    private void load_assets_file_list(String path)
    {
        LinearLayout file_list_layout = findViewById(R.id.file_list_layout);
        file_list_layout.removeAllViews();
        try {
            String file_list[] = getAssets().list(path);
            Log.i(TAG, "file_list length:" + file_list.length);
            for (String file_path : file_list) {
                Log.i(TAG, "file_path:" + file_path);
                TextView file_text_view = new TextView(this);
                file_text_view.setText(path + (0 == path.length() ? "" : "/") + file_path);
                if(file_path.contains(".")){
                    //file_text_view.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                    file_text_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView file_text_view = (TextView)v;
                            Log.i(TAG, "view file_path:" + file_text_view.getText().toString());
                            Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                            intent.putExtra("file_path", file_text_view.getText().toString());
                            startActivity(intent);
                        }
                    });
                }
                else{
                    file_text_view.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                    file_text_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView file_text_view = (TextView)v;
                            Log.i(TAG, "open file_path:" + file_text_view.getText().toString());
                            load_assets_file_list(file_text_view.getText().toString());
                        }
                    });
                }
                file_list_layout.addView(file_text_view);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load_root_onclick(View view) {
        load_assets_file_list("");
    }
}
