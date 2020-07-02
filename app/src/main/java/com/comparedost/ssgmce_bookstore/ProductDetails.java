package com.comparedost.ssgmce_bookstore;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.media.Image;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.material.textfield.TextInputEditText;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;

        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.UUID;

public class ProductDetails extends AppCompatActivity {
    TextView estimate, condition;
    TextInputEditText book_title, author, book_edition, book_description, original_price, phone_number;
    Button click_here, save;
    RadioButton radio_btn;
    ImageView imageView5;

    RadioGroup radio_grp;

    private String booktitlestr, bookauthorstr, bookdesstr, orignalpricestr, phonenostr, bookconditionstr, conditionchecked, estimatestr, bookeditionstr;
    double estimatedprice;
    private String CurrentUser, ProductId;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath, photourl;

    private FirebaseAuth mauth;
    private FirebaseDatabase mydb;
    private FirebaseFirestore fdb;
    private FirebaseStorage mstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        estimate = findViewById(R.id.estimate);
        condition = findViewById(R.id.condition);

        book_title = findViewById(R.id.book_title);
        author = findViewById(R.id.author);
        book_edition = findViewById(R.id.book_edition);
        book_description = findViewById(R.id.book_description);
        original_price = findViewById(R.id.original_price);
        phone_number = findViewById(R.id.phone_number);

        Button click_here = findViewById(R.id.click_here);
        Button save = findViewById(R.id.save);

        radio_grp = findViewById(R.id.radio_grp);


        imageView5 = findViewById(R.id.imageView5);

        mstore = FirebaseStorage.getInstance();
        mauth = FirebaseAuth.getInstance();
        mydb = FirebaseDatabase.getInstance();
        fdb = FirebaseFirestore.getInstance();


        click_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected = radio_grp.getCheckedRadioButtonId();
                radio_btn = findViewById(selected);
                conditionchecked = radio_btn.getText().toString();
                Log.e("100", "onClick: " + conditionchecked);


                orignalpricestr = original_price.getText().toString();
                int oprice = Integer.parseInt(orignalpricestr);
                Log.e("100", "onClick: " + orignalpricestr);

                if (conditionchecked.equalsIgnoreCase("Average")) {

                    estimatedprice = oprice * 0.35;
                } else if (conditionchecked.equalsIgnoreCase("Good")) {

                    estimatedprice = oprice * 0.5;
                } else if (conditionchecked.equalsIgnoreCase("Excellent")) {
                    estimatedprice = oprice * 0.65;
                }

                Log.e("100", "onClick: " + estimatedprice);

                estimate.setText(String.valueOf(estimatedprice));


            }
        });


        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String photouid;
                photouid= UUID.randomUUID().toString();
                final StorageReference mstoreref=mstore.getReference().child("Product-Images/"+photouid+".jpg");
                CurrentUser=mauth.getCurrentUser().getUid().toString();
                DatabaseReference myref=mydb.getReference("Users");
                DatabaseReference user=myref.child(CurrentUser);
                final DatabaseReference listing=user.child("Listings");


                booktitlestr=book_title.getText().toString();
                bookauthorstr=author.getText().toString();
                bookdesstr=book_description.getText().toString();
                orignalpricestr=original_price.getText().toString();
                phonenostr=phone_number.getText().toString();
                estimatestr=estimate.getText().toString();
                bookeditionstr=book_edition.getText().toString();

                Log.e("200", "Booktitle"+booktitlestr );
                Log.e("200", "Bookauthor"+bookauthorstr );
                Log.e("200", "des"+bookdesstr );
                Log.e("200", "orignal"+orignalpricestr );
                Log.e("200", "phone"+phonenostr );
                Log.e("200", "estimate"+estimatestr );









                if(!(bookauthorstr.isEmpty()) && !(booktitlestr.isEmpty()) && !(bookdesstr.isEmpty()) && !(orignalpricestr.isEmpty()) &&!(phonenostr.isEmpty())

                        && !(bookeditionstr.isEmpty())  &&!(estimatestr.isEmpty())  && !(conditionchecked.isEmpty())){




                    mstoreref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mstoreref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    photourl=uri;

                                    Log.e("300", "Photo Url :"+photourl.toString() );

                                    if(!(photourl.toString().isEmpty())){
                                        Map<String,Object> storemap=new HashMap<>();
                                        storemap.put("Book Title", booktitlestr);
                                        storemap.put("Book Author", bookauthorstr);
                                        storemap.put("Book Description", bookdesstr);
                                        storemap.put("Book Edition", bookeditionstr);
                                        storemap.put("Orignal Price", orignalpricestr);
                                        storemap.put("Selling Price", estimatestr);
                                        storemap.put("Phone No", phonenostr);
                                        storemap.put("Book Condition", conditionchecked);
                                        storemap.put("Listed By", CurrentUser);

                                        fdb.collection("Products").add(storemap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                ProductId=documentReference.getId();
                                                if(!(ProductId.isEmpty()) && !(photourl.toString().isEmpty())){

                                                    listing.child(ProductId).setValue(ProductId).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(ProductDetails.this, "Product Added Succesfully", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }

                                            }
                                        });
                                    }




                                }
                            });
                        }
                    });




                }

                else{
                    Toast.makeText(ProductDetails.this, "Fields Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void selectImage() {
        Intent i = new Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(
                i,
                "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            filePath = data.getData();
            try {


                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView5.setImageBitmap(bitmap);
                Log.e("select Image", "onActivityResult: " + filePath);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}