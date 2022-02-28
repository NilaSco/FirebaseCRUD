package studytree.edu.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Data extends AppCompatActivity {

    ProgressDialog progressDialog;
    ListView items;

    ArrayList<Register_Model> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        init();
        getAllData();

    }

    void init(){

        items = findViewById(R.id.lstItem);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading..");

        itemList = new ArrayList<>();

    }


    void getAllData(){
        progressDialog.show();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("tblRegister");

        FirebaseDataAdapter adapter = new FirebaseDataAdapter(this, R.layout.ly_view_item, itemList);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        items.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                itemList.clear();

                for(DataSnapshot snapshot1: snapshot.getChildren()){

                    String getKey = snapshot1.getRef().getKey();

                    Register_Model register_model = snapshot1.getValue(Register_Model.class);

                    String getFirstName = register_model.getFirstName();
                    String getLastName = register_model.getLastName();
                    String getAge = register_model.getAge();
                    String getCity = register_model.getCity();

                    itemList.add(new Register_Model(getFirstName, getLastName, getAge, getCity, getKey));

                    System.out.println(getFirstName);

                }

                progressDialog.dismiss();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(View_Data.this, "Error. Please Try Again.!!", Toast.LENGTH_SHORT).show();
            }
        });


    }


}