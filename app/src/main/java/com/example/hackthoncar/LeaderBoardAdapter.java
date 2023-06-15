package com.example.hackthoncar;

import android.content.Context;
import android.graphics.Bitmap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.projectem.R;

import java.util.List;

import kotlinx.coroutines.scheduling.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private Bitmap bitmap1;
    private FirebaseServices fbs;
    private List<Profile> data;

    private Context context;
    public TaskAdapter(List<Profile> data, Context context) {
        this.data = data;
        this.context=context;
    }

    public void setData(List<Profile> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Porfile p =data.get(position);
        fbs =FirebaseServices.getInstance();
        holder.name.setText(Profile.getname);
        holder.number.setText(Profile.getnumber);
        holder.check.setText(Profile.getphoto);
        StorageReference storageRef= fbs.getStorage().getInstance().getReference().child(profile.getImage());

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getContext())
                        .load(uri)
                        .into(proimg);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle any errors that occur when downloading the image
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , number ;
        ImageView check;

        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.TVName);
            number=itemView.findViewById(R.id.TVNumber);
            check=itemView.findViewById(R.id.IVItem);
        }
    }
}

