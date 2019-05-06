package com.example.welcomeactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UrlSearch extends AppCompatActivity implements View.OnClickListener
{
    private Button SearchUrlButton;
    private EditText UrlInput;
    private Button HomeButton;
    private WebView SearchWebAdress;

    String url;

    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_search);

        LoadingBar = new ProgressDialog(this);

        SearchUrlButton = (Button) findViewById(R.id.search_url_button);
        UrlInput = (EditText) findViewById(R.id.input_search_url);
        HomeButton = (Button) findViewById(R.id.home_button);
        SearchWebAdress = (WebView) findViewById(R.id.SearchWebsite);

        url = getIntent().getExtras().get("url_address").toString();
        UrlInput.setText(url);

        WebSettings webSettings = SearchWebAdress.getSettings();
        webSettings.setJavaScriptEnabled(true);
        SearchWebAdress.loadUrl(url);
        SearchWebAdress.setWebViewClient(new WebViewClient());

        LoadingBar.setTitle("Loading...");
        LoadingBar.setMessage("Please wait while we are opening your requested web address");
        LoadingBar.show();

        SearchUrlButton.setOnClickListener(this);
        HomeButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed()
    {
        if(SearchWebAdress.canGoBack())
        {
            SearchWebAdress.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view)
    {
        if(view == HomeButton)
        {
            finish();
            Intent homepage = new Intent(UrlSearch.this, HomeActivity.class);
            startActivity(homepage);
        }

        if(view == SearchUrlButton)
        {
            SearchWebAdress();
        }
    }

    private void SearchWebAdress()
    {
        LoadingBar.setTitle("Loading...");
        LoadingBar.setMessage("Please wait while we are opening your requested web address");
        LoadingBar.show();


        String Url_Address = UrlInput.getText().toString();

        if(TextUtils.isEmpty(Url_Address))
        {
            Toast empty =  Toast.makeText(UrlSearch.this, "Please enter Url or web address", Toast.LENGTH_LONG);
            empty.show();
        }
        else
        {
            String url_without_https = Url_Address.replaceAll("https://www.", "");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(UrlSearch.this, UrlSearch.class);
            search.putExtra("url_address", https+www+url_without_https);
            startActivity(search);

            UrlInput.setText("");
            UrlInput.requestFocus();
        }
    }
}
