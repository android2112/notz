package com.example.mark.notz;

public class Note {

    private String titolo;
    private String contenuto;
    private int id;
    private boolean isOnTop;

    public Note(String titolo, String contenuto) {
        this.titolo = titolo;
        this.contenuto = contenuto;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOnTop() {
        return isOnTop;
    }

    public void setOnTop(boolean onTop) {
        isOnTop = onTop;
    }




    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
}
