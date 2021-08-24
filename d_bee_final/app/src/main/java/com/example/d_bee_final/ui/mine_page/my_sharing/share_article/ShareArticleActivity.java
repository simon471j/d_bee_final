package com.example.d_bee_final.ui.mine_page.my_sharing.share_article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.d_bee_final.R;

import java.util.Objects;

public class ShareArticleActivity extends AppCompatActivity {

    private EditText title;
    private EditText website;
    private SharingArticleViewModel sharingArticleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_article);
        hideActionBar();
        sharingArticleViewModel = new ViewModelProvider(this).get(SharingArticleViewModel.class);
        initView();
    }

    private void initView() {
        title = findViewById(R.id.et_title);
        website = findViewById(R.id.et_website);
        Button buttonShare = findViewById(R.id.btn_share);
        onClickShare(buttonShare);
    }

    private void onClickShare(Button buttonShare) {
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharingTitle = title.getText().toString();
                String sharingWebsite = website.getText().toString();
                sharingArticleViewModel.share(sharingTitle, sharingWebsite);
            }
        });
    }

    private void hideActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}