package com.telran.a25_12_19_hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.telran.a25_12_19_hw.databinding.ActivityContactBindBinding;

public class ContactBindActivity extends AppCompatActivity {

    private ActivityContactBindBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_bind);
        ContactViewModel viewModel;
        if (getIntent().getExtras() == null) {
            viewModel = new ContactViewModel("",
                    "",
                    "",
                    "",
                    -1);
        }else{
            viewModel = new ContactViewModel(getIntent().getStringExtra("NAME"),
                    getIntent().getStringExtra("EMAIL"),
                    getIntent().getStringExtra("PHONE"),
                    getIntent().getStringExtra("ADDRESS"),
                    getIntent().getIntExtra("POSITION",-1));
        }

        binding.setViewModel(viewModel);
    }
}
