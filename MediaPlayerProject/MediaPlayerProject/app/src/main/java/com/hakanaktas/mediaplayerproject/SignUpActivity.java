package com.hakanaktas.mediaplayerproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakanaktas.mediaplayerproject.databinding.ActivityMainBinding;
import com.hakanaktas.mediaplayerproject.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        binding.selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            Uri selectImage = data.getData();
            binding.selectImage.setImageURI(selectImage);
        }
    }

    public void onCreateButtonClick (View view){
        String nameSurname= binding.nameSurnameText2.getText().toString();
        String email = binding.emailText2.getText().toString();
        String password = binding.passwordText2.getText().toString();
        String cpassword = binding.cpasswordText2.getText().toString();
        String mobile = binding.mobileText2.getText().toString();
        Integer image = binding.selectImage.getId();

        if(nameSurname.isEmpty()){
            binding.nameSurnameText2.setError("Full name is required!");
            binding.nameSurnameText2.requestFocus();
            return;
        }

        if (email.isEmpty()){
            binding.emailText2.setError("Email is required!");
            binding.emailText2.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailText2.setError("Please provide valid email adress.");
            binding.emailText2.requestFocus();
            return;
        }

        if (password.isEmpty()){
            binding.passwordText2.setError("Password is required!");
            binding.passwordText2.requestFocus();
            return;
        }

        if (!password.equals(cpassword)){
            binding.passwordText2.setError("Passwords are not matched!");
            binding.passwordText2.requestFocus();
            return;
        }

        if (password.length() < 6){
            binding.passwordText2.setError("Min password length should be 6 characters!");
            binding.passwordText2.requestFocus();
            return;

        }

        if (mobile.isEmpty()){
            binding.mobileText2.setError("Mobile number is required!");
            binding.mobileText2.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(nameSurname, email, password, mobile);
                    FirebaseDatabase.getInstance().getReference("Profiles").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"User has been created.",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this,"Failed to Sign Up.Try Again!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this,"The email may be already taken!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    }

