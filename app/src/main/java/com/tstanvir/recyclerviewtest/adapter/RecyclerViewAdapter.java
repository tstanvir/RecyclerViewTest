package com.tstanvir.recyclerviewtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tstanvir.recyclerviewtest.R;
import com.tstanvir.recyclerviewtest.ShowUserStat;
import com.tstanvir.recyclerviewtest.model.UserInfo;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<UserInfo>userList;

    public RecyclerViewAdapter(Context context, List<UserInfo> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInfo user=userList.get(position);
        holder.userNameView.setText(user.getName());
        holder.userHandleView.setText(user.getHandle());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userNameView;
        private TextView userHandleView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            userNameView=itemView.findViewById(R.id.user_name);
            userHandleView=itemView.findViewById(R.id.user_handle);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            UserInfo userInfo=userList.get(position);

            Intent intent=new Intent(context, ShowUserStat.class);
            intent.putExtra("userName",userInfo.getName());
            intent.putExtra("userHandle",userInfo.getHandle());
            context.startActivity(intent);

        }
    }
}
