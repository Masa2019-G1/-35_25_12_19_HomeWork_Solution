package com.telran.a25_12_19_hw;

public class ContactViewModel {
    public String name;
    public String email;
    public String phone;
    public String address;
    public int position;
    public boolean isEdit;

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
        isEdit = position < 0;
    }
}
