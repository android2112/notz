package com.example.mark.notz;

public class Note {

    private String titolo;
    private String contenuto;


    public Note(String titolo, String contenuto) {
        this.titolo = titolo;
        this.contenuto = contenuto;
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
