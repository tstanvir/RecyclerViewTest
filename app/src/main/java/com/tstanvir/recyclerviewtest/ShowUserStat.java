package com.tstanvir.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowUserStat extends AppCompatActivity {
    private TextView userSolved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_stat);
        userSolved=findViewById(R.id.user_solved);
        Bundle bundle=getIntent().getExtras();
        userSolved.setText("User Name: "+bundle.getString("userName")+", "+"User Handle: "+
                bundle.getString("userHandle")+" solved = X problems.");
    }
}