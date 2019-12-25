package com.telran.a25_12_19_hw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter {
    List<ContactRow> contacts;

    public ContactAdapter() {
        contacts = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    public void add(ContactRow contact){
        contacts.add(contact);
        notifyDataSetChanged();
    }

    public void remove(int position){
        contacts.remove(position);
        notifyDataSetChanged();
    }

    public void update(int position, ContactRow contact){
        contacts.set(position,contact);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.contact_row,parent,false);
        }
        ContactRow row = contacts.get(position);
        TextView nameTxt = convertView.findViewById(R.id.nameTxt);
        TextView phoneTxt = convertView.findViewById(R.id.phoneTxt);
        nameTxt.setText(row.name);
        phoneTxt.setText(row.phone);
        return convertView;
    }
}
