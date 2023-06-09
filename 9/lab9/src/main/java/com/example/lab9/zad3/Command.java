package com.example.lab9.zad3;

public class Command {
    private int id;
    private int user_id;
    private int age;
    private String commandName;

    public Command(int user_id, int age) {
        this.user_id = user_id;
        this.age = age;
    }

    public Command() {
    }

    public Command( int user_id, int age, String commandName) {
                this.user_id = user_id;
        this.age = age;
        this.commandName = commandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
