package guc.thermo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_Activity extends AppCompatActivity {

    private Button regBtn,lgnBtn;
    private EditText useret,emailet,passwordet,passwordet1;
    private FirebaseAuth mAuth;
    private TextView t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);


        useret=findViewById(R.id.useret);
        emailet=findViewById(R.id.emailet);
        passwordet=findViewById(R.id.passwordet);
        passwordet1=findViewById(R.id.passwordet1);
        regBtn=findViewById(R.id.button);
        lgnBtn=findViewById(R.id.button2);
        t2= findViewById(R.id.text2);
        mAuth = FirebaseAuth.getInstance();

         lgnBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent3);
                }
         });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String username = useret.getText().toString();
            String mail =     emailet.getText().toString();
            String password = passwordet.getText().toString();
            String Confirm = passwordet1.getText().toString();

            if(username.equals("")){

              useret.setError("Username required");
              useret.requestFocus();
            }

            else if(mail.equals("")){

                emailet.setError("Email required");
                emailet.requestFocus();
            }
            else if(password.equals("")){

                passwordet.setError("Password required");
                passwordet.requestFocus();
            }
            else if(!password.equals(Confirm)){
                passwordet1.setError("Password doesn't Match");
                passwordet1.requestFocus();
            }
            else{
                mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User Created Successfully", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }

            }});
    }
}
