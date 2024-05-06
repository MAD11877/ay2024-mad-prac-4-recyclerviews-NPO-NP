package sg.edu.np.mad.madpractical4;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> list_objects;
    private ListActivity activity;
    public UserAdapter(ArrayList<User> list_objects, ListActivity activity){
        this.list_objects = list_objects;
        this.activity = activity;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(UserViewHolder holder, int position){
        User theUser = list_objects.get(position);
        holder.name.setText(theUser.getName());
        holder.description.setText(theUser.getDescription());

        holder.bigImage.setVisibility(theUser.getName().endsWith("7") ? View.VISIBLE : View.GONE);
        holder.smallImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Profile");
                builder.setMessage(String.valueOf(holder.name.getText()));
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //send data to main activity
                        Intent profile = new Intent(activity, MainActivity.class);
                        profile.putExtra("name", holder.name.getText());
                        profile.putExtra("description", holder.description.getText());
                        profile.putExtra("followed", theUser.getFollowed());
                        activity.startActivity(profile);
                    }
                });
                builder.setNegativeButton("Close", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return list_objects.size();
    }
}
