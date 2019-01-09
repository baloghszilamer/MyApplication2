package com.example.windows10.myapplication;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class AddFragment extends Fragment {

    private  static final int PICK_IMAGE_REQUEST =1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName,mEditTextDescription,mEditTextFirstrune,mEditTextFirstsp1;
    private EditText mEditTextFirstsp2,mEditTextFirstsp3,mEditTextFirstsp4,mEditTextSecondrune,mEditTextSecondsp1,mEditTextSecondsp2;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private String phonenumber;


    private Uri image;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        mButtonChooseImage = view.findViewById(R.id.button_choose_image);
        mButtonUpload = view.findViewById(R.id.button_upload);
        mTextViewShowUploads = view.findViewById(R.id.text_view_show_uploads);
        mEditTextFileName = view.findViewById(R.id.edit_text_file_name);
        mImageView = view.findViewById(R.id.image_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mEditTextDescription = view.findViewById(R.id.description_ed);



        mStorageRef = FirebaseStorage.getInstance().getReference("Data");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Data");

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = mSettings.edit();
        editor.apply();

        final String phnum = mSettings.getString("telefon", "ures");
        phonenumber = phnum;
        Log.d("masodik", "Profile telefonszam:" + phonenumber);

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            image = data.getData();

            Picasso.get().load(image).into(mImageView);
            // mImageView.setImageURI(mImageUri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = Objects.requireNonNull(getActivity()).getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        /*Elso Valtozat
        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
            +"."+getFileExtension(mImageUri));
            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },5000);
                            Toast.makeText(getActivity(),"Upload sikerult",Toast.LENGTH_SHORT).show();
                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    });
        }else{
            Toast.makeText(getActivity(),"No file selected",Toast.LENGTH_SHORT).show();
        }
   */

        //Masodik Valtozat


        if (image != null)
        {
            mStorageRef.putFile(image).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return mStorageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        //Log.e(TAG, "then: " + downloadUri.toString());


                        Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                downloadUri.toString(),mEditTextDescription.getText().toString());

                        mDatabaseRef.push().setValue(upload);
                    } else
                    {
                        //Toast.makeText(MainActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}