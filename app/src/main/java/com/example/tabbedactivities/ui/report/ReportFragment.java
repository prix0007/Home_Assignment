package com.example.tabbedactivities.ui.report;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tabbedactivities.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReportFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    private String userID;
    private EditText email, issue;
    Spinner subject;
    private Button submit_report;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_report, container, false);
        email = v.findViewById(R.id.email);
        subject = v.findViewById(R.id.report_subject);
        issue = v.findViewById(R.id.issue);

        submit_report = v.findViewById(R.id.submit_report);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        submit_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString().trim();
                String ssubject = subject.getSelectedItem().toString().trim();
                String sissue = issue.getText().toString().trim();

                userID = firebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = firebaseFirestore.collection("reports").document(userID);
                final Map<String, Object> reportObj = new HashMap<>();
                reportObj.put("email", semail);
                reportObj.put("subject",ssubject);
                reportObj.put("issue",sissue);
                documentReference.set(reportObj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("SUCCESS","SuccessFully submitted report for "+userID);
                        Toast.makeText(getContext(), "SuccessFully Submitted Your Report . We will contact you in mail", Toast.LENGTH_LONG).show();
                        email.setText("");
                        subject.setSelection(0);
                        issue.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FAILURE","Something Occured and Error Happened");
                        Toast.makeText(getContext(), "Some Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return v;

    }
}
