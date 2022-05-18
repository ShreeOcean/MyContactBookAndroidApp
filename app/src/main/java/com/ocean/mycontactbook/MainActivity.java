package com.ocean.mycontactbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.ocean.mycontactbook.adapter.CustomListAdapter;
import com.ocean.mycontactbook.databinding.ActivityMainBinding;
import com.ocean.mycontactbook.databinding.CustomContactDetailsDialogBinding;
import com.ocean.mycontactbook.db.ContactDatabase;
import com.ocean.mycontactbook.model.ContactDetailDataModel;
import com.ocean.mycontactbook.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ActivityMainBinding mainBinding;
    CustomContactDetailsDialogBinding dialogBinding, dialogBindingupdating;
    ContactDatabase contactDatabase;
    private String imageInBase64;
    private boolean isUpdate = false;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        contactDatabase =new ContactDatabase(this);
        contactDatabase.openDatabase();

        loadDataInListview();

        mainBinding.floatingActionButton.setOnClickListener(view -> {

            dialogBinding = CustomContactDetailsDialogBinding.inflate(getLayoutInflater());
            Dialog dialog =new Dialog(this);
            dialog.setContentView(dialogBinding.getRoot());
            dialog.setCancelable(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            dialogBinding.btnCaptureImage.setOnClickListener(view1 -> {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    callCamera();
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                }
            });

            dialogBinding.btnCreateContact.setOnClickListener(view1 -> {
                //contactDatabase.insertData(MainActivity.this, di.etFirstName.getText().toString(), profileBinding.etLastName.getText().toString(), imageInBase64);
                contactDatabase.insertData(MainActivity.this, dialogBinding.etContactName.getText().toString(),
                                            dialogBinding.etEmail.getText().toString(),
                                            dialogBinding.etConNum.getText().toString(),
                                            dialogBinding.etAddress.getText().toString(),imageInBase64);
                dialog.dismiss();
                loadDataInListview();
            });

        });
    }

    private void loadDataInListview() {
        CustomListAdapter adapter = new CustomListAdapter(getallContactList(), this);
        mainBinding.listViewContact.setAdapter(adapter);
    }

    private List<ContactDetailDataModel> getallContactList() {

        List<ContactDetailDataModel> contactList = new ArrayList<>();
        cursor = contactDatabase.getAllData();
        if (cursor.getCount() > 0) {
            Log.d("DATA", "" + cursor);
            cursor.moveToFirst();
            do {
                ContactDetailDataModel dataModel = new ContactDetailDataModel();
                dataModel.setRowId(cursor.getString(0));
                dataModel.setName(cursor.getString(1));
                dataModel.setEmail(cursor.getString(2));
                dataModel.setImage(cursor.getString(3));
                dataModel.setAddress(cursor.getString(4));
                dataModel.setContact_num(cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return contactList;
    }

    private void callCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 101);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // TODO: DO UR JOB
                callCamera();
            } else {
                Utility.showLongToast(this, "");

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageInBase64 = Utility.convertBitmaptoBase64(bitmap);
            if (isUpdate){
                dialogBindingupdating.contactProfileImage.setImageBitmap(bitmap);
            }else {
                dialogBindingupdating.contactProfileImage.setImageBitmap(bitmap);
            }

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}