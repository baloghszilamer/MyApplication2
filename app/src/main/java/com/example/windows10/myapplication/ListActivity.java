package com.example.windows10.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.myapplication.MainActivity;
import com.example.windows10.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.v7.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class  ListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Posts List");
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true) ;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Data");


    }
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Data,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Data, ViewHolder>(
                        Data.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ){

                protected void populateViewHolder(ViewHolder viewHolder,Data data,int position){
                   // Data data1 = new Data(data1.getTitle(),data1.getImage(),data1.getDescription());

                    viewHolder.setDetails(getApplicationContext(),data.getTitle(),data.getImage(),data.getDescription());
                }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                //Views
//                                TextView mTitleTv = view.findViewById(R.id.rTitle);
//                                TextView mDescTv = view.findViewById(R.id.rDescription);
//                                ImageView mImageView = view.findViewById(R.id.rImageView);
//
//                                //get data from views
//                                String mTitle = mTitleTv.getText().toString();
//                                String mDesc = mDescTv.getText().toString();
//
//                                Drawable mDrawable = mImageView.getDrawable();
//                                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
//
                                String mTitle=getItem(position).getTitle();
                                String mDesc=getItem(position).getDescription();
                                String mImage=getItem(position).getImage();

//
//                                //pass this data to new Activity
//
                            Intent intent = new Intent(view.getContext(), DetailActivity.class);
//                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                                byte[] bytes = stream.toByteArray();
                                //put bitmap image as arrayl of bytes
                                intent.putExtra("title", mTitle);
                                //put title
                                intent.putExtra("description", mDesc);
                                //put description
                                intent.putExtra("image", mImage);







                                startActivity(intent);//Start activity

                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }

                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
     //   Log.d("asd","ez is");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item =menu.findItem(R.id.action_add);
        return super.onCreateOptionsMenu(menu);
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_add){
            startActivity(new Intent(ListActivity.this,AddAdActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
