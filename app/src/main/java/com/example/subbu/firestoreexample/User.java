package com.example.subbu.firestoreexample;

import com.google.gson.Gson;

/**
 * Created by saravanan on 15-Oct-17.
 */

public class User {
    Object age;
    Object city;
    Object mobile;
    Object name;

    User(){
    }
    public User(Object age,Object city,Object mobile,Object name){
        this.age=age;
        this.city=city;
        this.mobile=mobile;
        this.name=name;
    }

    public String toJson(){
       return new Gson().toJson(this);
    }


}

