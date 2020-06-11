package com.example.tabbedactivities.faculty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tabbedactivities.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ExtensionForm extends AppCompatActivity {
    private static final String TAG = "Individual Form";
    Button success, reject;
    TextView enrollmentNo, formId, name, reason, text_reason;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_extension_form);

        initializeViews();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            setTitle(extras.get("enrollment_no").toString());
            enrollmentNo.setText( extras.get("enrollment_no").toString());
            firebaseFirestore.collection(extras.get("collection").toString()).whereEqualTo("enrollment_no", extras.get("enrollment_no").toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                QuerySnapshot documents = task.getResult();
                                for(DocumentSnapshot doc : documents) {
                                    enrollmentNo.setText(doc.get("enrollment_no").toString());
                                    formId.setText(doc.getId().toString());
                                    name.setText(doc.get("name").toString());
                                    reason.setText(doc.get("reason").toString());
                                    text_reason.setText(doc.get("text_reason").toString());
                                    if((Boolean) doc.get("approved") == true){
                                        success.setVisibility(View.GONE);
                                        reject.setVisibility(View.GONE);
                                    };
                                }
                            }
                        }
                    });
        }
    }

    private void initializeViews(){
        success = findViewById(R.id.approveExtensionForm);
        reject = findViewById(R.id.rejectExtensionForm);
        formId = findViewById(R.id.extensionFormId);
        name = findViewById(R.id.extensionName);
        enrollmentNo = findViewById(R.id.enrollmentNo);
        reason = findViewById(R.id.extensionReason);
        text_reason = findViewById(R.id.extensionTextReason);
    }
}
