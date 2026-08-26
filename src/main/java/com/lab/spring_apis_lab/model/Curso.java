package com.lab.spring_apis_lab.model;

public class Curso {

    private int id;
    private String nombre;
    private String instructor;
    private int duracionHoras;

    public Curso() {
    }

    public Curso(int id, String nombre, String instructor, int duracionHoras) {
        this.id = id;
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracionHoras = duracionHoras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }
}