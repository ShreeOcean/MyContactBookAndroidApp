package com.ocean.mycontactbook.model;

public class ContactDetailDataModel {

    private String rowId;
    private String name;
    private String contact_num;
    private String image;
    private String email;
    private String address;

//    public ContactDetailDataModel(String rowId, String name, String contact_num, String image, String email, String address) {
//        this.rowId = rowId;
//        this.name = name;
//        this.contact_num = contact_num;
//        this.image = image;
//        this.email = email;
//        this.address = address;
//    }
//
//    public ContactDetailDataModel() {
//
//    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
