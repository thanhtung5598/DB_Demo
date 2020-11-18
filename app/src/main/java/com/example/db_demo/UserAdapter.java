package com.example.db_demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    Context context;
    ArrayList<User> list;
    LayoutInflater layoutInflater;
    ItemClicked itemClicked;
    int selected_position = -1;

    public UserAdapter(Context context, ArrayList<User> list,ItemClicked itemClicked) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_rcv,parent,false);
        return new UserViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        holder.txtItem.setText(list.get(position).getName());
        holder.txtId.setText(list.get(position).getId());
        holder.itemView.setSelected(selected_position == position);
        if(selected_position == position){
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = selected_position;
                selected_position = position;
                UserAdapter.this.notifyItemChanged(previousItem);
                UserAdapter.this.notifyItemChanged(position);
                itemClicked.deleteUser(String.valueOf(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txtItem,txtId;
        UserAdapter userAdapter;
        public UserViewHolder(@NonNull View itemView,UserAdapter adapter) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItemName);
            txtId = itemView.findViewById(R.id.txtId);
            this.userAdapter = adapter;
        }
    }
}
