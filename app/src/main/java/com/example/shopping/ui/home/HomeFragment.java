package com.example.shopping.ui.home;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopping.R;
import com.example.shopping.ui.gallery.items;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {


    ImageView addimage;
    @BindView(R.id.planets_array)
    Spinner planetsArray;
    @BindView(R.id.itemname)
    EditText itemname;
    @BindView(R.id.itemdes)
    EditText itemdes;
    @BindView(R.id.itemprice)
    EditText itemprice;
    Button btnAdd ;
    Spinner spinner;
    DatabaseReference reference;
    items item;
    private FirebaseAuth auth;
    String userid;
    StorageReference Storage2;
    private String item_name,item_Des,item_rice;
    String Text;
    private static final int bic=1;
    Uri imageuri;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        spinner = root.findViewById(R.id.planets_array);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnAdd=(Button)root.findViewById(R.id.btn_add);
        itemname=(EditText)root.findViewById(R.id.itemname);
        itemdes=(EditText)root.findViewById(R.id.itemdes);
        itemprice=(EditText)root.findViewById(R.id.itemprice);

        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference().child("items");
        Storage2= FirebaseStorage.getInstance().getReference("images");
        addimage=(ImageView)root.findViewById(R.id.addimage);
        userid=auth.getCurrentUser().getUid();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                 Text = spinner.getSelectedItem().toString();

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), Text, Toast.LENGTH_SHORT).show();
                additemstodatabase();

            }
        });
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);

            }
        });


        return root;
    }
     public void additemstodatabase(){
         final Uri downloadUrl ;
         //  reference.child("items").child(auth.getCurrentUser().getUid()).push().child("images").setValue(downloadUrl[0] +"");


        item_name=itemname.getText().toString();
         item_Des=itemdes.getText().toString();
         item_rice=itemprice.getText().toString();

         if (TextUtils.isEmpty(item_name)){
             Toast.makeText(getContext(), "Please enter the item name", Toast.LENGTH_SHORT).show();
         }
         else if (TextUtils.isEmpty(item_Des)){
             Toast.makeText(getContext(), "Please enter the Description of item ", Toast.LENGTH_SHORT).show();
         }
         else if (TextUtils.isEmpty(item_rice)){
             Toast.makeText(getContext(), "Please enter the price of item ", Toast.LENGTH_SHORT).show();
         }
         else{
             String saveCurrentDate;
             String saveCurrentTime;
             Calendar calendar = Calendar.getInstance();

             SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
             saveCurrentDate = currentDate.format(calendar.getTime());

             SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
             saveCurrentTime = currentTime.format(calendar.getTime());
             StorageReference storageReference=Storage2.child(System.currentTimeMillis()+"."+getExtension(imageuri));
             storageReference.putFile(imageuri)
                     .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                         HashMap<String,Object> map=new HashMap();
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            map.put("id",saveCurrentDate+",,"+saveCurrentTime);
                                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            map.put("item_name",item_name);
                                            map.put("item_price",item_rice);
                                            map.put("item_Des",item_Des);
                                            map.put("item_type",Text);
                                            map.put("images", downloadUrl +"");

                                            reference.child(saveCurrentDate+",,"+saveCurrentTime ).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }

                     })
                     .addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception exception) {
                             // Handle unsuccessful uploads
                             // ...
                         }
                     });


         }
     }
    private String getExtension(Uri uri){
        ContentResolver contentResolver= getContext().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 &&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imageuri=data.getData();
            addimage.setImageURI(imageuri);
        }
    }
}
