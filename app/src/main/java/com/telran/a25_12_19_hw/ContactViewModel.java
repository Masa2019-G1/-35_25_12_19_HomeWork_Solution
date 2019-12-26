package com.telran.a25_12_19_hw;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

public class ContactViewModel {
    public String name;
    public String email;
    public String phone;
    public String address;
    public int position;
    public ObservableBoolean isEdit = new ObservableBoolean();
    public MutableLiveData<Bundle> liveData = new MutableLiveData<>();
//    private ViewModelListener listener;

    public ContactViewModel(String name,
                            String email,
                            String phone,
                            String address,
                            int position) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.position = position;
//        isEdit = position < 0;
        isEdit.set(position < 0);
    }

//    public void setListener(ViewModelListener listener) {
//        this.listener = listener;
//    }

    public void setEdit(boolean edit) {
//       isEdit = edit;
        isEdit.set(edit);
    }

    public void save(){
//        if(listener != null){
//            listener.onSave(name,email,phone,address,position);
            Bundle bundle = new Bundle();
            bundle.putString("NAME",name);
            bundle.putString("EMAIL",email);
            bundle.putString("PHONE",phone);
            bundle.putString("ADDRESS",address);
            bundle.putInt("POSITION",position);
            bundle.putString("ACTION",position < 0 ? "ADD":"UPDATE");
            liveData.setValue(bundle);
//            listener.onSave(bundle);
//            listener.onAction(bundle);
//        }
    }

    public void remove(){
//        if(listener != null){
//            listener.onRemove(position);
            Bundle bundle = new Bundle();
            bundle.putInt("POSITION",position);
            bundle.putString("ACTION","REMOVE");
            liveData.setValue(bundle);
//            listener.onRemove(bundle);
//            listener.onAction(bundle);
//        }
    }

//    public static interface ViewModelListener{
//        void onSave(String name, String email, String phone, String address, int position);
//        void onRemove(int position);

//        void onSave(Bundle bundle);
//        void onRemove(Bundle bundle);

//        void onAction(Bundle bundle);
//    }
}
