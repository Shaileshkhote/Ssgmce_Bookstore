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
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;

        import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter;
        import com.google.android.material.textfield.TextInputEditText;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.storage.FirebaseStorage;

        import java.io.IOException;

public class ProductDetails extends AppCompatActivity {
    TextView estimate,condition;
    TextInputEditText book_title,author,book_edition,book_description,original_price,phone_number;
    Button click_here,save;
    RadioButton radio_btn;
    ImageView imageView5;

    RadioGroup radio_grp;

    String booktitlestr,bookauthorstr,bookdesstr,orignalpricestr,phonenostr,bookconditionstr,conditionchecked;

    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    private FirebaseAuth mauth;
    private FirebaseDatabase mydb;
    private FirebaseFirestore fdb;
    private FirebaseStorage mstore;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);



        TextView estimate=findViewById(R.id.estimate);
        TextView condition=findViewById(R.id.condition);

        TextInputEditText book_title=findViewById(R.id.book_title);
        TextInputEditText author=findViewById(R.id.author);
        TextInputEditText book_edition=findViewById(R.id.book_edition);
        TextInputEditText book_description=findViewById(R.id.book_description);
        TextInputEditText original_price=findViewById(R.id.original_price);
        TextInputEditText phone_number=findViewById(R.id.phone_number);

        Button click_here=findViewById(R.id.click_here);
        Button save=findViewById(R.id.save);

         radio_grp=findViewById(R.id.radio_grp);


            imageView5=findViewById(R.id.imageView5);


        radio_grp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected =radio_grp.getCheckedRadioButtonId();
                radio_btn=radio_grp.findViewById(selected);
                conditionchecked=radio_btn.getText().toString();
                Log.e("100", "onClick: "+conditionchecked );


            }
        });

            click_here.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    estimateprice();
                }
            });




        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

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
    private void fetchdatafromlayout(){

        booktitlestr=book_title.getText().toString();
        bookauthorstr=author.getText().toString();
        bookdesstr=book_description.getText().toString();
        orignalpricestr=original_price.getText().toString();
        phonenostr=phone_number.getText().toString();







    }

    private void estimateprice(){
        long estimatedprice=0;
        orignalpricestr=original_price.getText().toString();

        try {
            if(conditionchecked=="Average"){

                estimatedprice= Integer.parseInt(orignalpricestr)*(35/100);
            }
            else if(conditionchecked=="Good"){

                estimatedprice= Integer.parseInt(orignalpricestr)*(50/100);
            }
            else  if(conditionchecked=="Excellent"){
                estimatedprice= Integer.parseInt(orignalpricestr)*(65/100);
            }
            estimate.setText(String.valueOf(estimatedprice));

        }catch(NullPointerException e){}



    }
}