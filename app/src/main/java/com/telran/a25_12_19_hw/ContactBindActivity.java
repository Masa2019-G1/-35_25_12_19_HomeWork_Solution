package com.telran.a25_12_19_hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.telran.a25_12_19_hw.databinding.ActivityContactBindBinding;

public class ContactBindActivity extends AppCompatActivity implements View.OnClickListener /*, ContactViewModel.ViewModelListener*/ {

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

//        viewModel.setListener(this);
//        viewModel.setListener(bundle -> {
//            Intent intent = new Intent();
//            intent.putExtras(bundle);
//            setResult(RESULT_OK,intent);
//            finish();
//        });
        viewModel.liveData.observe(this, bundle -> {
            load();
            if(!bundle.getString("ACTION").equals("REMOVE")){
                save(bundle.getString("NAME"),bundle.getString("PHONE"));
            }
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        });
        binding.setViewModel(viewModel);
//        binding.setEditClickListener(v -> {
//            Toast.makeText(this, "Save btn clicked", Toast.LENGTH_SHORT).show();
//        });
//        binding.setEditClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.editBtn){
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
//            binding.getViewModel().isEdit = true;
//            binding.invalidateAll();
        }
    }

    private void save(String name, String phone){
        SharedPreferences sp = getSharedPreferences("CONTACT",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NAME",name);
        editor.putString("PHONE",phone);
        boolean res = editor.commit();
    }

    private void load(){
        SharedPreferences sp = getSharedPreferences("CONTACT",MODE_PRIVATE);
        String name = sp.getString("NAME","NoName");
        String phone = sp.getString("PHONE","NoPhone");
        Toast.makeText(this, name + " " + phone, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onSave(Bundle bundle) {
//        Intent intent = new Intent();
//        intent.putExtras(bundle);
//        setResult(RESULT_OK,intent);
//        finish();
//    }
//
//    @Override
//    public void onRemove(Bundle bundle) {
//        Intent intent = new Intent();
//        intent.putExtras(bundle);
//        setResult(RESULT_OK,intent);
//        finish();
//    }

//    @Override
//    public void onSave(String name, String email, String phone, String address, int position) {
//        Intent intent = new Intent();
//        intent.putExtra("NAME",name);
//        intent.putExtra("EMAIL",email);
//        intent.putExtra("PHONE",phone);
//        intent.putExtra("ADDRESS",address);
//        intent.putExtra("POSITION",position);
//        intent.putExtra("ACTION", position < 0 ? "ADD" : "UPDATE");
//        setResult(RESULT_OK,intent);
//        finish();
//    }
//
//    @Override
//    public void onRemove(int position) {
//        Intent intent = new Intent();
//        intent.putExtra("ACTION","REMOVE");
//        intent.putExtra("POSITION",position);
//        setResult(RESULT_OK,intent);
//        finish();
//    }


}
