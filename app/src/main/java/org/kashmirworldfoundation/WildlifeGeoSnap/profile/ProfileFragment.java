package org.kashmirworldfoundation.WildlifeGeoSnap.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.FirebaseHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Fragment;

import java.io.ByteArrayOutputStream;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final int PICK_IMAGE = 100;

    private View fragmentView;

    private TextView nameView, phoneView, emailView, isAdminView;
    private ImageView profilePicView;
    private Button logoutButtonView;
    private ImageButton Mupload;
    private Uri imageurl;
    private String Uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        UserData userData = UserData.getInstance();
        initViews();
        initUI();
        return fragmentView;
    }

    @Override
    protected void initViews() {
        nameView = fragmentView.findViewById(R.id.NameP);
        phoneView = fragmentView.findViewById(R.id.PhoneP);
        emailView = fragmentView.findViewById(R.id.EmailP);
        isAdminView = fragmentView.findViewById(R.id.AdminP);
        profilePicView = fragmentView.findViewById(R.id.ProfilePic);
        Mupload = fragmentView.findViewById(R.id.Pupload);
        logoutButtonView = fragmentView.findViewById(R.id.logoutP);
    }

    private void initUI() {
        fragmentView.setOnClickListener(this);
        UserData userData = UserData.getInstance();
        nameView.setText("Name: " + userData.getFullname());
        phoneView.setText("Phone: " + userData.getPhone());
        emailView.setText("Email: " + userData.getEmail());
        loadProfilePicture();
        isAdminView.setVisibility(View.INVISIBLE);
        if (userData.isAdmin()) {
            isAdminView.setVisibility(View.VISIBLE);
            isAdminView.setText("Admin: True");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == Mupload.getId()) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
        if (view.getId() == logoutButtonView.getId()) {
            FirebaseAuth.getInstance().signOut();
            FirebaseHandler.resetSession();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    private void loadProfilePicture() {
        UserData userData = UserData.getInstance();
        FirebaseHandler.loadImageIntoView(userData.getProfile(), profilePicView, ProfileFragment.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            imageurl = data.getData();
            profilePicView.setImageURI(imageurl);
            profilePicView.setDrawingCacheEnabled(true);
            profilePicView.buildDrawingCache();

            Bitmap bitmap = ((BitmapDrawable) profilePicView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] datan = baos.toByteArray();
            uploadTask_Helper(datan);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadTask_Helper(byte[] datan) {
        Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        StorageReference profile = FirebaseStorage.getInstance().getReference("profile/" + Uid);
        UploadTask uploadTask = profile.putBytes(datan);
        uploadTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseFirestore.getInstance().collection("Member").document(Uid).update("profile", "profile/" + Uid);
                return;
            }
            Toast.makeText(getContext(), "Error adding profile please try again with a stable connection", Toast.LENGTH_LONG).show();
        });
    }

}

