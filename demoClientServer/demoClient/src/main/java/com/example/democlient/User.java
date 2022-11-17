package com.example.democlient;

public class User {
    private  String nameProcess;
    private  String pid;

    public User(String nameProcess,String pid){
        this.nameProcess=nameProcess;
        this.pid=pid;
    }

    public String getNameProcess(){
        return nameProcess;
    }

    public String getPid() {
        return pid;
    }

}
