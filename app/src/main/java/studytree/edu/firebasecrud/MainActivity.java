package studytree.edu.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insert = findViewById(R.id.btnInsert);
        Button view = findViewById(R.id.btnView);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Save_Data.class);
                startActivity(intent);

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, View_Data.class);
                startActivity(intent);

            }
        });


    }
}