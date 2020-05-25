package com.example.tabbedactivities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends Fragment {

    public Register() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FirebaseAuth firebaseAuth;
    Button register_btn;
    private EditText email, pwd, cnfrmpwd;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        register_btn = (Button) v.findViewById(R.id.button);
        email = v.findViewById(R.id.email);
        pwd = v.findViewById(R.id.pwd);
        cnfrmpwd = v.findViewById(R.id.cnfrmpwd);

        progressBar = v.findViewById(R.id.progress);

        firebaseAuth = FirebaseAuth.getInstance();

       register_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar.setVisibility(View.VISIBLE);
               register_btn.setClickable(false);

               String em = email.getText().toString();
               String pw = pwd.getText().toString();
               String cnfpwd = cnfrmpwd.getText().toString();
               if(!pw.equals(cnfpwd)) {
                   progressBar.setVisibility(View.GONE);
                   register_btn.setClickable(true);
                   Toast.makeText(getActivity(), "Password doesn't match.", Toast.LENGTH_LONG).show();
                   return;
               }
               if(pw.length() < 8){
                   progressBar.setVisibility(View.GONE);
                   register_btn.setClickable(true);
                   Toast.makeText(getActivity(), "Password must be 8 character long.", Toast.LENGTH_LONG).show();
                   return;
               }
               firebaseAuth.createUserWithEmailAndPassword( em, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       register_btn.setClickable(true);
                                       Toast.makeText(getActivity(), "SuccessFully Registered. Please Verify your Email.\n Check your Registered Email", Toast.LENGTH_LONG).show();
                                   } else {
                                       Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                   }
                               }
                           });
                           resetInputForm();
                       } else {
                           Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                           resetInputForm();
                       }
                       progressBar.setVisibility(View.GONE);
                   }
               });
           }
       });
        return v;
    }
    private  void resetInputForm(){
         email.setText(""); pwd.setText(""); cnfrmpwd.setText("");
    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button:
//                Toast.makeText(getActivity(), "Register Clicked", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
}
