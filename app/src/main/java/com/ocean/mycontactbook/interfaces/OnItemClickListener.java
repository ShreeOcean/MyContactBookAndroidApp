package com.ocean.mycontactbook.interfaces;

import com.ocean.mycontactbook.db.ContactDatabase;

import java.util.List;

public interface OnItemClickListener {

    void onItemClick(List<ContactDatabase> contactData, int position);
}
