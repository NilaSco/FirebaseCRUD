package studytree.edu.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Data extends AppCompatActivity {

    EditText key, firstName, lastName, age, city;
    Button update, delete;

    DatabaseReference mDatabase;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        String getKey = getIntent().getStringExtra("key");

        init();
        buttonAction();

        key.setText(getKey);
        getDataByKey(getKey);

    }

    void getDataByKey(String key){
        progressDialog.show();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("tblRegister").child(key).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){

                    Register_Model register_model = task.getResult().getValue(Register_Model.class);

                    firstName.setText(register_model.getFirstName());
                    lastName.setText(register_model.getLastName());
                    city.setText(register_model.getCity());
                    age.setText(register_model.getAge());

                }else{
                    Toast.makeText(Edit_Data.this, "Fail..", Toast.LENGTH_SHORT).show();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Edit_Data.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    void init(){

        key = findViewById(R.id.etKey);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        age = findViewById(R.id.etAge);
        city = findViewById(R.id.etCity);

        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Updating.. ");

    }

    void buttonAction(){

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmation();

            }
        });

    }

    void confirmation(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_Data.this);
        builder.setCancelable(false);
        builder.setTitle("Confirmation");
        builder.setMessage("Are You Sure");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                delete();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    void delete(){
        progressDialog.show();
        String getKey = key.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("tblRegister").child(getKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(Edit_Data.this, "Deleted!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Edit_Data.this, "Error!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    void validation(){

        String getKey = key.getText().toString().trim();
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
            update(getKey, getFirstName,getLastName, getAge, getCity);
        }

    }

    void update(String key,String getFirstName, String getLastName, String getAge , String getCity){
        progressDialog.show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Register_Model register_model = new Register_Model(getFirstName, getLastName, getAge, getCity);

        reference.child("tblRegister").child(key).setValue(register_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(Edit_Data.this, "Updated!!", Toast.LENGTH_SHORT).show();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(Edit_Data.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}