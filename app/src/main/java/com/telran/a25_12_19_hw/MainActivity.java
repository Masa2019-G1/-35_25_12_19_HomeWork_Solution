package com.telran.a25_12_19_hw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    TextView emptyTxt;
    FloatingActionButton addBtn;
    ListView contactList;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyTxt = findViewById(R.id.emptyTxt);
        addBtn = findViewById(R.id.addBtn);
        contactList = findViewById(R.id.contactList);
        adapter = new ContactAdapter();
        contactList.setAdapter(adapter);

        showEmpty(adapter.getCount() == 0);

        addBtn.setOnClickListener(v->{
            showEditView(null,-1);
        });

        contactList.setOnItemClickListener((parent, view, position, id) -> {
            ContactRow contact = (ContactRow) adapter.getItem(position);
            showEditView(contact,position);
        });
    }

    private void showEditView(ContactRow contact, int position) {
        Intent intent = new Intent(this, ContactBindActivity.class);
        if (contact != null) {
            intent.putExtra("NAME", contact.name);
            intent.putExtra("PHONE", contact.phone);
            intent.putExtra("EMAIL", contact.email);
            intent.putExtra("ADDRESS", contact.address);
            intent.putExtra("POSITION", position);
        }

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
            switch (data.getStringExtra("ACTION")) {
                case "ADD":
                    addContactToList(data.getStringExtra("NAME"),
                            data.getStringExtra("PHONE"),
                            data.getStringExtra("EMAIL"),
                            data.getStringExtra("ADDRESS"));
                    break;
                case "UPDATE":
                    updateContact(data.getIntExtra("POSITION",-1),
                            data.getStringExtra("NAME"),
                            data.getStringExtra("EMAIL"),
                            data.getStringExtra("PHONE"),
                            data.getStringExtra("ADDRESS"));
                    break;
                case "REMOVE":
                    removeContactFromList(data.getIntExtra("POSITION",-1));
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void addContactToList(String name,
                                  String email,
                                  String phone,
                                  String address) {
        ContactRow contact = new ContactRow(name, phone, email, address);
        adapter.add(contact);
        showEmpty(false);
    }

    private void removeContactFromList(int position) {
        adapter.remove(position);
        showEmpty(adapter.getCount() == 0);
    }

    private void updateContact(int position, String name, String email, String phone, String address) {
        ContactRow contact = new ContactRow(name, phone, email, address);
        adapter.update(position, contact);
    }

    private void showEmpty(boolean isShow) {
        emptyTxt.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
