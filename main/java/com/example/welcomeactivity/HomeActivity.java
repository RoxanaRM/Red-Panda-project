package com.example.welcomeactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button SearchButtonHome;
    private EditText InputURL;

    private Button facebook_btn;
    private Button youtube_btn;
    private Button instagram_btn;
    private Button snapchat_btn;
    private Button yahoo_btn;
    private Button google_btn;
    private Button btnImage;

    private ProgressDialog LoadingBar;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
		
        LoadingBar = new ProgressDialog(this);

        SearchButtonHome = (Button) findViewById(R.id.search_button_home);
        InputURL = (EditText) findViewById(R.id.search_url_home);

        facebook_btn = (Button) findViewById(R.id.facebook);
        youtube_btn = (Button) findViewById(R.id.youtube);
        instagram_btn = (Button) findViewById(R.id.instagram);
        snapchat_btn = (Button) findViewById(R.id.snapchat);
        yahoo_btn = (Button) findViewById(R.id.yahoo);
        google_btn = (Button) findViewById(R.id.google);

        btnImage = (Button) findViewById(R.id.btnCaptureImage);

        LoadingBar.setTitle("Red Panda Browser");
        LoadingBar.setMessage("Welcome to the world fastest browser!");
        LoadingBar.show();

        SearchButtonHome.setOnClickListener(this);
        facebook_btn.setOnClickListener(this);
        youtube_btn.setOnClickListener(this);
        instagram_btn.setOnClickListener(this);
        snapchat_btn.setOnClickListener(this);
        yahoo_btn.setOnClickListener(this);
        google_btn.setOnClickListener(this);
        google_btn.setOnClickListener(this);
        btnImage.setOnClickListener(this);
		

    }

    @Override
    public void onClick(View view)
    {
        if(view == SearchButtonHome)
        {
            OpenWebSite();
        }

        if(view == facebook_btn)
        {
            Intent Open_facebook = new Intent(HomeActivity.this, UrlSearch.class);
            Open_facebook.putExtra("url_address", "https://www.facebook.com");
            startActivity(Open_facebook);
        }

        if(view == youtube_btn)
        {
            Intent Open_youtube = new Intent(HomeActivity.this, UrlSearch.class);
            Open_youtube.putExtra("url_address", "https://www.youtube.com");
            startActivity(Open_youtube);
        }

        if(view == snapchat_btn)
        {
            Intent Open_snapchat = new Intent(HomeActivity.this, UrlSearch.class);
            Open_snapchat.putExtra("url_address", "https://www.snapchat.com");
            startActivity(Open_snapchat);
        }

        if(view == instagram_btn)
        {
            Intent Open_instagram = new Intent(HomeActivity.this, UrlSearch.class);
            Open_instagram.putExtra("url_address", "https://www.instagram.com");
            startActivity(Open_instagram);
        }

        if(view == yahoo_btn)
        {
            Intent Open_yahoo = new Intent(HomeActivity.this, UrlSearch.class);
            Open_yahoo.putExtra("url_address", "https://www.yahoo.com");
            startActivity(Open_yahoo);
        }

        if(view == google_btn)
        {
            Intent Open_google = new Intent(HomeActivity.this, UrlSearch.class);
            Open_google.putExtra("url_address", "https://www.google.com");
            startActivity(Open_google);


        }

        if(view == btnImage)
        {
            finish();
            Intent Image = new Intent(HomeActivity.this, Capture.class);
            startActivity(Image);
        }




    }

    private void OpenWebSite()
    {
        LoadingBar.setTitle("Loading...");
        LoadingBar.setMessage("Please wait while we are opening your requested web address");
        LoadingBar.show();


        String Url_Address = InputURL.getText().toString();

        if(TextUtils.isEmpty(Url_Address))
        {
            Toast empty =  Toast.makeText(HomeActivity.this, "Please enter Url or web address", Toast.LENGTH_LONG);
            empty.show();
        }
        else
        {
            String url_without_https = Url_Address.replaceAll("https://www.", "");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(HomeActivity.this, UrlSearch.class);
            search.putExtra("url_address", https+www+url_without_https);
            startActivity(search);

            InputURL.setText("");
            InputURL.requestFocus();
        }
    }
}
