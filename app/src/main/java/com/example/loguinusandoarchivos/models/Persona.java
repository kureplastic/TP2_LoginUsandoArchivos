package com.example.loguinusandoarchivos.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Persona implements Serializable {
    private String Nombre, Dni, Apellido, Password, Mail;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public Persona(String nombre, String dni, String apellido, String password, String mail) {
        Nombre = nombre;
        Dni = dni;
        Apellido = apellido;
        Password = password;
        Mail = mail;
    }

    @NonNull
    @Override
    public String toString() {
        return Nombre + Apellido;
    }
}
