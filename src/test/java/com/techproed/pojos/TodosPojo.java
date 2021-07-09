package com.techproed.pojos;

public class TodosPojo {
    /*
    {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
     */
    private int userId;
    private int id;
    private String title;
    private boolean completed; //isCompleted
    //json datadaki tum key ler private degisken olarak tanimladik

    //class icinde sag click -> generate-> getter and setter -> hepsini sec -> OK
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void isCompleted(boolean completed) {
        this.completed = completed;
    }

    //class icinde sag click -> generate-> constructor sec parametresiz cons oldugu icin en yukarda com.techproed.pojos.Todos.Pojo sec->OK
    public TodosPojo() {
    }

    //class icinde sag click -> generate-> constructor sec parametreli cons oldugu icin -> hepsini sec -> OK
    public TodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    //constructor bir degiskene ilk degeri atamak ve ayni degiskenlerde farkli degerler icin kullanilir
    //class icinde sag click -> generate-> toString() olusturulur-> hepsini sec -> OK
    @Override
    public String toString() {
        return "TodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

}
