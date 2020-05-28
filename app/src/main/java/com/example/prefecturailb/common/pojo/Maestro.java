package com.example.prefecturailb.common.pojo;

import android.net.Uri;

import com.google.firebase.database.Exclude;

public class Maestro {

    private String nombre;
    private String correo;
    private Uri imagenUrl;

    public Maestro(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Exclude
    public String getCorreo() {
        return correo;
    }
    @Exclude
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Exclude
    public Uri getImagenUrl() {
        return imagenUrl;
    }
    @Exclude
    public void setImagenUrl(Uri imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
