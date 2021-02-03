package com.example.appproyecto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localidad {

    @SerializedName("localidadId")
    @Expose
    private Long localidadId;


    public Localidad(Long localidadId) {
        this.localidadId = localidadId;
    }

    public Long getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(Long localidadId) {
        this.localidadId = localidadId;
    }

    @Override
    public String toString() {
        return "Localidad {" +
                "localidadId =" + localidadId +
                '}';
    }
}