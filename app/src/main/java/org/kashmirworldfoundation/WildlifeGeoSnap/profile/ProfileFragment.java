package org.kashmirworldfoundation.WildlifeGeoSnap.profile;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.FirebaseHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Member;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;

public class ProfileFragment extends Fragment {
    private static final int PICK_IMAGE = 100;
    private TextView Pname,Pphone,Pemail,Padmin;
    private View fragmentView;
    private ImageView Ppic;
    private Button logout;
    private FirebaseFirestore Fstore;
    private FirebaseAuth Fauth;
    private FirebaseStorage Fstorage;
    private ImageButton Mupload;
    private Uri imageurl;
    private String Uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fstorage = FirebaseStorage.getInstance();
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        Fstore = FirebaseFirestore.getInstance();
        Fauth = FirebaseAuth.getInstance();

        Member member = Member.getInstance();
        update_Pname(member);
        update_Pphone(member);
        update_Pemail(member);
        update_Padmin(member);

        update_ProfilePic(member);
        update_Pupload();
        logoutUser();
        return fragmentView;
    }

    public void update_Pname(Member member) {
        Pname = (TextView)fragmentView.findViewById(R.id.NameP);
        Pname.setText("Name: " + member.getFullname());
    }

    public void update_Pphone(Member member) {
        Pphone = (TextView)fragmentView.findViewById(R.id.PhoneP);
        Pphone.setText("Phone: " + member.getPhone());
    }

    public void update_Pemail(Member member) {
        Pemail = (TextView)fragmentView.findViewById(R.id.EmailP);
        Pemail.setText("Email: " + member.getEmail());
    }

    public void update_Padmin(Member member) {
        Padmin = (TextView)fragmentView.findViewById(R.id.AdminP);
        if (member.isAdmin()) {
            Padmin.setText("Admin: True");
        }
        else {
            Padmin.setVisibility(View.INVISIBLE);
        }
    }

    public void update_ProfilePic(Member member) {
        Ppic = (ImageView)fragmentView.findViewById(R.id.ProfilePic);
        FirebaseHandler.loadImageIntoView(member.getProfile(), Ppic, ProfileFragment.this);
    }

    public void update_Pupload() {
        Mupload = (ImageButton)fragmentView.findViewById(R.id.Pupload);
        Mupload.setOnClickListener(new View.OnClickListener() {
            @Override // Opens gallery
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    public void logoutUser() {
        logout = (Button) fragmentView.findViewById(R.id.logoutP);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                FirebaseHandler.resetSession();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            imageurl = data.getData();
            Ppic.setImageURI(imageurl);
            Ppic.setDrawingCacheEnabled(true);
            Ppic.buildDrawingCache();

            Bitmap bitmap = ((BitmapDrawable) Ppic.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] datan = baos.toByteArray();
            uploadTask_Helper(datan);
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    public void uploadTask_Helper(byte[] datan) {
        Uid = Fauth.getCurrentUser().getUid();
        StorageReference profile= Fstorage.getReference("profile/" +Uid);
        UploadTask uploadTask = profile.putBytes(datan);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    Fstore.collection("Member").document(Uid).update("profile","profile/"+Uid);
                }
                else {
                    Toast.makeText(getContext(),"Error adding profile please try again with a stable connection",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

