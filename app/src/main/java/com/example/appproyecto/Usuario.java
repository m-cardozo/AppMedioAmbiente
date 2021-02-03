package com.example.appproyecto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("usuarioId")
    @Expose
    private Long usuarioId;


    public Usuario(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "usuarioId =" + usuarioId +
                '}';
    }
}