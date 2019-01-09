package com.example.windows10.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView mTitle,mDetail;
    ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mTitle=findViewById(R.id.rtitle);
        mDetail=findViewById(R.id.rdescription);
        mImage=findViewById(R.id.rimageView);
       // Picasso.get().load(image).into(mImage);

//        byte[] bytes = getIntent().getByteArrayExtra("image");
//        String title = getIntent().getStringExtra("title");
//        String desc = getIntent().getStringExtra("description");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        String image=getIntent().getStringExtra("image");
        String title=getIntent().getStringExtra("title");
        String desc=getIntent().getStringExtra("description");


        //set data to views
        mTitle.setText(title);
        mDetail.setText(desc);
        Picasso.get().load(image).into(mImage);
        //mImage.setImageBitmap(bmp);


    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


