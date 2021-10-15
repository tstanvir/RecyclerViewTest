package com.tstanvir.recyclerviewtest.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tstanvir.recyclerviewtest.R;
import com.tstanvir.recyclerviewtest.model.UserInfo;
import com.tstanvir.recyclerviewtest.util.Util;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
       // Log.d("database: ","Created ");
    }
    //create our table.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //sql command will be used.
        //writing sql command
        Log.d("database: ","Table Created ");
        String CREATE_TABLE="CREATE TABLE "+Util.TABLE_NAME+"("
                +Util.KEY_ID+" INTEGER PRIMARY KEY,"+Util.KEY_NAME+" TEXT,"
                +Util.KEY_HANDLE+" TEXT"+")";
        db.execSQL(CREATE_TABLE); //creating our table.

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("database: ","Table Dropped ");
        String DROP_TABLE=String.valueOf(R.string.drop_table);
        db.execSQL(DROP_TABLE, new String[]{Util.TABLE_NAME});
        onCreate(db);
    }

    /*
    Database operations:
        CRUD= Create, Read, Update, Delete
     */

    //Adding user info
    public void addUserInfo(UserInfo userInfo){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME,userInfo.getName());
        values.put(Util.KEY_HANDLE,userInfo.getHandle());


        //inserting into table

        db.insert(Util.TABLE_NAME,null,values);
       // Log.d("database: ","Info inserted ");
        db.close();
    }

    //Getting a user's info
    public UserInfo getUserInfo(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{"*"},Util.KEY_ID+"=?",new String[]{
                String.valueOf(id)
        },null,null,null);
        UserInfo userInfo=new UserInfo();
        if(cursor.moveToFirst()){

            userInfo.setId(Integer.parseInt(cursor.getString(0)));
            userInfo.setName(cursor.getString(1));
            userInfo.setHandle(cursor.getString(2));

        }
        db.close();
        return userInfo;
    }


    //Getting all user's info
    public List<UserInfo>getAllUsers(){
        List<UserInfo> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String SELECT_ALL="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(SELECT_ALL,null);
        if(cursor.moveToFirst()){
            do{
                UserInfo userInfo=new UserInfo();
                userInfo.setId(Integer.parseInt(cursor.getString(0)));
                userInfo.setName(cursor.getString(1));
                userInfo.setHandle(cursor.getString(2));
                arrayList.add(userInfo);
            }while(cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    //Updating a row
    public int updateUserInfo(UserInfo userInfo){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,userInfo.getName());
        values.put(Util.KEY_HANDLE,userInfo.getHandle());
        int ind=db.update(Util.TABLE_NAME,values,Util.KEY_ID+"=?",
                new String[]{String.valueOf(userInfo.getId())});
        db.close();
        return ind;
    }


    //Deleting a row
    public void deleteUser(UserInfo userInfo){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",
                new String[]{String.valueOf(userInfo.getId())});
        db.close();
    }

    //Get User Id
    public int getUserId(UserInfo userInfo, String tableName){
        SQLiteDatabase db=this.getWritableDatabase();
        String ID_QUERY="SELECT "+Util.KEY_ID+","+Util.KEY_HANDLE+" FROM "+tableName
                +" where "+Util.KEY_NAME+"= \""+userInfo.getName()+"\" and "+Util.KEY_HANDLE+
                "= \""+userInfo.getHandle()+"\";";

        Cursor cursor=db.rawQuery(ID_QUERY,null);
        int id=-1;
        if(cursor.moveToFirst())  id= Integer.parseInt(cursor.getString(0));
        db.close();
        return id;
    }

    //How many records/rows are there in that table
    public int getCount(String tableName){
        String COUNT_QUERY="SELECT * FROM "+tableName;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(COUNT_QUERY,null);
        int ret=cursor.getCount();
        db.close();
        return ret;

    }

}