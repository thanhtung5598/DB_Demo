package com.example.db_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClicked {
    DBUser dbUser;
    Button btnAdd,btnRemove,btnUpdate;
    TextView txtName;
    RecyclerView rcv;
    ArrayList<User> userArrayList;
    UserAdapter userAdapter;
    String indexDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbUser = new DBUser(this,"DB_USER",null,1);
        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        btnUpdate = findViewById(R.id.btn_update);

        txtName = findViewById(R.id.txtName);
        rcv = findViewById(R.id.rcv);
        onLoadData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(String.valueOf(txtName.getText()));
                dbUser.addUser(user);
                onLoadData();
                txtName.setText("");
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"index "+userArrayList.get(Integer.parseInt(indexDelete)).getId(),Toast.LENGTH_SHORT).show();
                dbUser.removeUser(userArrayList.get(Integer.parseInt(indexDelete)).getId());
                onLoadData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setText(userArrayList.get(Integer.parseInt(indexDelete)).getName());
            }
        });

    }

    public void onLoadData(){
        userArrayList = dbUser.getAll();
        userAdapter = new UserAdapter(this,userArrayList,this);
        rcv.setAdapter(userAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void deleteUser(String index) {
        indexDelete = index;
    }
}