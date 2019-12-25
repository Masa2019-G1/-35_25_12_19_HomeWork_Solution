package com.telran.a25_12_19_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    TextView nameTxt, emailTxt, phoneTxt, addressTxt;
    EditText editName, editEmail, editPhone, editAddress;
    Button saveBtn, editBtn, removeBtn;
    ViewGroup editGroup, viewGroup;
    int position = -1;
    ContactRow curr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        addressTxt = findViewById(R.id.addressTxt);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editAddress = findViewById(R.id.editAddress);

        editBtn = findViewById(R.id.editBtn);
        saveBtn = findViewById(R.id.saveBtn);
        removeBtn = findViewById(R.id.removeBtn);

        editGroup = findViewById(R.id.editGroup);
        viewGroup = findViewById(R.id.viewGroup);

        if(getIntent().getExtras() == null){
            curr = new ContactRow("","","","");
            showEdit();
        }else{
            position = getIntent().getIntExtra("POSITION",-1);
            curr = new ContactRow(getIntent().getStringExtra("NAME"),
                    getIntent().getStringExtra("PHONE"),
                    getIntent().getStringExtra("EMAIL"),
                    getIntent().getStringExtra("ADDRESS"));
            showView();
        }

        editBtn.setOnClickListener(v->{
            showEdit();
        });

        saveBtn.setOnClickListener(v ->{
            Intent intent = new Intent();
            intent.putExtra("POSITION",position);
            intent.putExtra("NAME",editName.getText().toString());
            intent.putExtra("PHONE",editPhone.getText().toString());
            intent.putExtra("ADDRESS",editAddress.getText().toString());
            intent.putExtra("EMAIL",editEmail.getText().toString());
            if(position < 0){
                intent.putExtra("ACTION","ADD");
            }else {
                intent.putExtra("ACTION","UPDATE");
            }
            setResult(RESULT_OK,intent);
            finish();
        });

        removeBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("POSITION",position);
            intent.putExtra("ACTION","REMOVE");
            setResult(RESULT_OK,intent);
            finish();
        });
    }

    private void showEdit(){
        viewGroup.setVisibility(View.GONE);
        editGroup.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
        removeBtn.setVisibility(View.GONE);

        editName.setText(curr.name);
        editPhone.setText(curr.phone);
        editEmail.setText(curr.email);
        editAddress.setText(curr.address);
    }

    private void showView(){
        viewGroup.setVisibility(View.VISIBLE);
        editGroup.setVisibility(View.GONE);
        saveBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
        removeBtn.setVisibility(View.VISIBLE);

        nameTxt.setText(curr.name);
        phoneTxt.setText(curr.phone);
        emailTxt.setText(curr.email);
        addressTxt.setText(curr.address);
    }

}
