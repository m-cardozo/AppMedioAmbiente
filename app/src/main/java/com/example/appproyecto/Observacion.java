package com.example.appproyecto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Observacion {

    @SerializedName("observacionId")
    @Expose
    private Long observacionId;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("altitud")
    @Expose
    private String altitud;
    @SerializedName("nivelCritico")
    @Expose
    private String nivelCritico;
    @SerializedName("fenomeno")
    @Expose
    private Fenomeno fenomeno;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;
    @SerializedName("localidad")
    @Expose
    private Localidad localidad;


    public Observacion(Long observacionId, String descripcion, String fecha, String latitud, String longitud, String altitud, String nivelCritico, Fenomeno fenomeno, Usuario usuario, Localidad localidad) {
        this.observacionId = observacionId;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.nivelCritico = nivelCritico;
        this.fenomeno = fenomeno;
        this.usuario = usuario;
        this.localidad = localidad;
    }

    public Observacion(String descripcion, String fecha, String latitud, String longitud, String altitud, String nivelCritico, Fenomeno fenomeno, Usuario usuario, Localidad localidad) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.nivelCritico = nivelCritico;
        this.fenomeno = fenomeno;
        this.usuario = usuario;
        this.localidad = localidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getNivelCritico() {
        return nivelCritico;
    }

    public void setNivelCritico(String nivelCritico) {
        this.nivelCritico = nivelCritico;
    }

    public Fenomeno getFenomeno() {
        return fenomeno;
    }

    public void setFenomeno(Fenomeno fenomeno) {
        this.fenomeno = fenomeno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Long getObservacionId() {
        return observacionId;
    }

    public void setObservacionId(Long observacionId) {
        this.observacionId = observacionId;
    }

    @Override
    public String toString() {
        return "Observacion {" +
                "descripcion ='" + descripcion + '\'' +
                ", fecha =" + fecha +
                ", latitud ='" + latitud + '\'' +
                ", longitud ='" + longitud + '\'' +
                ", altitud ='" + altitud + '\'' +
                ", nivelCritico ='" + nivelCritico + '\'' +
                ", fenomeno =" + fenomeno +
                ", usuario =" + usuario +
                ", localidad =" + localidad +
                '}';
    }
}