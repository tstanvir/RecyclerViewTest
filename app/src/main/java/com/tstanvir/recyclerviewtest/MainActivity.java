package com.tstanvir.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tstanvir.recyclerviewtest.adapter.RecyclerViewAdapter;
import com.tstanvir.recyclerviewtest.data.DatabaseHandler;
import com.tstanvir.recyclerviewtest.model.UserInfo;
import com.tstanvir.recyclerviewtest.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<UserInfo> userList;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseHandler db=new DatabaseHandler(MainActivity.this);

        List<UserInfo> allUser=db.getAllUsers();
        for(UserInfo user:allUser){
            // Log.d("Info: ",""+user.getName()+" "+user.getHandle());
            userList.add(user);
        }
        //setting up adapter
        recyclerViewAdapter= new RecyclerViewAdapter(this,userList);
        recyclerView.setAdapter(recyclerViewAdapter);






        //for listView test
        /*
            //Building the arrayAdapter for putting the user names on our list view

            arrayAdapter= new ArrayAdapter<>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    userNames
                    );

            //adding the arrayAdapter to listView in order to show the contents
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        */
    }
}