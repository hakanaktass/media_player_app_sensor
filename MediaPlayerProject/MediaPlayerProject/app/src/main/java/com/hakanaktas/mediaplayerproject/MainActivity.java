package com.hakanaktas.mediaplayerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hakanaktas.mediaplayerproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding ;
    private FirebaseAuth auth;
    int count;
    ArrayList<Audio> songsList = new ArrayList<>();
    Audio audio ;
    MediaPlayer music;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        int count =0 ;


        music = MediaPlayer.create(MainActivity.this,R.raw.blindidlights);
        audio = new Audio(Integer.toString(R.raw.blindidlights),"Blindid Lights",Integer.toString(music.getDuration()),R.drawable.weekndalbum,"The Weeknd" );
        songsList.add(audio);

        music = MediaPlayer.create(MainActivity.this,R.raw.daylight);
        audio = new Audio(Integer.toString(R.raw.daylight),"Daylight", Integer.toString(music.getDuration()),R.drawable.daylightalbum,"Joji" );
        songsList.add(audio);

        music = MediaPlayer.create(MainActivity.this,R.raw.song2);
        audio = new Audio(Integer.toString(R.raw.song2),"Song 2", Integer.toString(music.getDuration()),R.drawable.blur,"Blur" );
        songsList.add(audio);

        music = MediaPlayer.create(MainActivity.this,R.raw.thelessiknow);
        audio = new Audio(Integer.toString(R.raw.thelessiknow),"The Less I Know", Integer.toString(music.getDuration()),R.drawable.letithappenalbum,"Tame Impala" );
        songsList.add(audio);

    }



    public void onSignInClick(View view){
        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();

        if (email.isEmpty()){
            binding.emailText.setError("Email is required!");
            binding.emailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailText.setError("Please provide valid email adress.");
            binding.emailText.requestFocus();
            return;
        }

        if (password.isEmpty()){
            binding.passwordText.setError("Password is required!");
            binding.passwordText.requestFocus();
            return;
        }

        if (password.length() < 6){
            binding.passwordText.setError("Min password length should be 6 characters!");
            binding.passwordText.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()){
                        Toast.makeText(MainActivity.this,"Sign in process is succesfull.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,MediaGallery.class);
                        intent.putExtra("LIST",songsList);
                        startActivity(intent);
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"You need to verify your email address.Please Check your email to verify account",Toast.LENGTH_LONG).show();
                    }


                }else{
                    count++;
                    int temp=count;
                    temp = 3-temp;
                    Toast.makeText(MainActivity.this,"Failed to login! Please Try Again. You have "+temp+" login attemps.",Toast.LENGTH_SHORT).show();


                }
            }

        });

        if(count >= 2)
        {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

    }

    public void onSignUpClick(View view){

        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);


    }

}


