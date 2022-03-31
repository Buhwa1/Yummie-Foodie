package com.example.yummiefoodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yummiefoodie.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    Button btnsignin;
    EditText phone, password;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yummie-foodie-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnsignin = findViewById(R.id.btnsignin);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);

        //Firebase
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_user = database.getReference();

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number1 = phone.getText().toString();
                final String password1 = password.getText().toString();
//                ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
//                progressDialog.setMessage("Please Wait");
//                progressDialog.show();

                databaseReference.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(number1)) {
////                            progressDialog.dismiss();
                            final String password2 = snapshot.child(number1).child("Password").getValue(String.class);
//
                            if (password2.equals(password1)) {
                                Toast.makeText(SignIn.this, "Sign in yes!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignIn.this,signup.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}

