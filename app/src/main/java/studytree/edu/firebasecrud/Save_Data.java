package studytree.edu.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Save_Data extends AppCompatActivity {

    EditText firstName, lastName, age, city;
    Button save;

    DatabaseReference mDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        init();
        buttonAction();

    }

    void init(){

        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        age = findViewById(R.id.etAge);
        city = findViewById(R.id.etCity);

        save = findViewById(R.id.btnSave);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Saving.......");

    }

    void buttonAction(){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

    }

    void validation(){

        String getFirstName =firstName.getText().toString().trim();
        String getLastName = lastName.getText().toString().trim();
        String getAge = age.getText().toString().trim();
        String getCity = city.getText().toString().trim().toUpperCase();

        if(getFirstName.isEmpty()){
            firstName.setError("Please Enter First Name");
        }else if(getLastName.isEmpty()){
            lastName.setError("Please Enter Last Name");
        }else if(getAge.isEmpty()){
            age.setError("Please Enter Age");
        }else if(getCity.isEmpty()){
            city.setError("Please Enter City");
        }else{
            save(getFirstName,getLastName, getAge, getCity);
        }

    }

    void save(String getFirstName, String getLastName, String getAge , String getCity){

        progressDialog.show();

        Register_Model register_model = new Register_Model(getFirstName, getLastName, getAge, getCity);

        String key = mDatabase.child("tblRegister").push().getKey();

        mDatabase.child("tblRegister").child(key).setValue(register_model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(Save_Data.this, "Success", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Save_Data.this, "Error, Try Again.", Toast.LENGTH_SHORT).show();

            }
        });


    }

}