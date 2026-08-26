package com.lab.spring_apis_lab.model;

public class Pelicula {

    private int id;
    private String titulo;
    private String director;
    private int anioEstreno;

    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String director, int anioEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anioEstreno = anioEstreno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }
}