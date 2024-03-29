package com.example.lawapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.option_main_page:
                Intent intent=new Intent(AboutUsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.option_about_us:
                Intent intent2=new Intent(AboutUsActivity.this,AboutUsActivity.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.option_share:
                Intent sendIntent=new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain")
                        .putExtra(Intent.EXTRA_SUBJECT,"قانون مدنی")
                        .putExtra(Intent.EXTRA_TEXT,"http://www.google.com/");
                startActivity(Intent.createChooser(sendIntent,"share"));
                break;

            case R.id.option_exit:
                finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }
}