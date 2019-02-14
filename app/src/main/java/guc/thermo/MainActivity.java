package guc.thermo;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private EditText e1, e2;
    private TextView t1;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("Thermomonitor");


        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        e1 = findViewById(R.id.Email);
        e2 = findViewById(R.id.password);
        t1 = findViewById(R.id.text);
        mAuth = FirebaseAuth.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail =  e1.getText().toString();
                String password = e2.getText().toString();
                if(mail.isEmpty()){
                    e1.setError("Email required");
                    e1.requestFocus();
                }
                else if(password.isEmpty() || password.length() < 6){
                    e2.setError("Password required(Min 6 Chars.)");
                    e2.requestFocus();
                }
                else{
                    mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),listActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),register_Activity.class);
                startActivity(intent2);
            }
        });
    }



    @Override
    public void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(getApplicationContext(), listActivity.class);
            startActivity(intent);
            finish();
        }
    }
}


