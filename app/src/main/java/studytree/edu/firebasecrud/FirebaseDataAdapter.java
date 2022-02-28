package studytree.edu.firebasecrud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FirebaseDataAdapter extends ArrayAdapter<Register_Model> {

    ArrayList<Register_Model> dataList;
    Context context;
    int resource;

    public FirebaseDataAdapter(@NonNull Context context, int resource,  @NonNull List<Register_Model> objects) {
        super(context, resource, objects);

        this.dataList = (ArrayList<Register_Model>) objects;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Nullable
    @Override
    public Register_Model getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ly_view_item, null, true);
        }

        Register_Model register_model = getItem(position);

        LinearLayout main = convertView.findViewById(R.id.lyMain);

        TextView firstName = convertView.findViewById(R.id.tvFirstName);
        TextView lastName = convertView.findViewById(R.id.tvLastName);
        TextView age = convertView.findViewById(R.id.tvAge);
        TextView city = convertView.findViewById(R.id.tvCity);

        firstName.setText(register_model.getFirstName());
        lastName.setText(register_model.getLastName());
        age.setText(register_model.getAge());
        city.setText(register_model.getCity());

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,Edit_Data.class);
                intent.putExtra("key", register_model.getKey());
                context.startActivity(intent);

                System.out.println(register_model.getKey());

            }
        });


        return convertView;
    }
}
