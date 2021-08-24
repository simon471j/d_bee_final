package com.example.d_bee_final.ui.home_page.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.d_bee_final.R;

public class SearchActivity extends AppCompatActivity {

    private EditText editTextInput;
    private ImageView imageViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        ActionBar supportActionBar = getSupportActionBar();
        editTextInput = findViewById(R.id.et_input);
        imageViewSearch = findViewById(R.id.iv_search_activity);
        onSearchClick();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    private void onSearchClick() {
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editTextInput.getText().toString();
                if (!"".equals(key))
                    search(key);
            }
        });
    }

    public void search(String key) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("key", key);
        QueriedArticleFragment queriedArticleFragment = new QueriedArticleFragment();
        queriedArticleFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container_view_search_activity, queriedArticleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public EditText getEditTextInput() {
        return editTextInput;
    }
}