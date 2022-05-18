package com.ocean.mycontactbook.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.mycontactbook.R;
import com.ocean.mycontactbook.databinding.CustomListviewItemsBinding;
import com.ocean.mycontactbook.model.ContactDetailDataModel;
import com.ocean.mycontactbook.utility.Utility;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomListAdapter extends BaseAdapter {

    CustomListviewItemsBinding listviewItemsBinding;
    private List<ContactDetailDataModel> list;
    private Context context;

    public CustomListAdapter(List<ContactDetailDataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview_items, viewGroup, false);
//        ImageView imageView = convertView.findViewById(R.id.item_image_view);
//        TextView tvName = convertView.findViewById(R.id.item_name);

        CircleImageView imageView = convertView.findViewById(R.id.contact_profile_image_listView);
        TextView tvName = convertView.findViewById(R.id.tv_ContactName);
        TextView tvConNum = convertView.findViewById(R.id.tv_ContactPhoneNum);

        ContactDetailDataModel data = list.get(position);

        Bitmap bitmap = Utility.convertBase64ToBitmap(data.getImage());
        imageView.setImageBitmap(bitmap);
        tvName.setText(data.getName());
        tvConNum.setText(data.getContact_num());

        return convertView;
    }
}
