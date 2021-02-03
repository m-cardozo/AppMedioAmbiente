package com.example.appproyecto.adapters;

import java.util.Date;

public class Observaciones {
    private String mId;
    private String mDescripcion;
    private String mfecha;
    private String mnivelCritico;
    private String mfenomeno;
    private String mLocalidad;
    private String mLatitud;
    private String mLongitud;
    private String mAltitud;
    private String mUsuario;

    public Observaciones(String mId, String mDescripcion, String mfecha, String mnivelCritico, String mfenomeno, String mLocalidad, String mLatitud, String mLongitud, String mAltitud, String mUsuario) {
        this.mId = mId;
        this.mDescripcion = mDescripcion;
        this.mfecha = mfecha;
        this.mnivelCritico = mnivelCritico;
        this.mfenomeno = mfenomeno;
        this.mLocalidad = mLocalidad;
        this.mLatitud = mLatitud;
        this.mLongitud = mLongitud;
        this.mAltitud = mAltitud;
        this.mUsuario = mUsuario;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public void setmDescripcion(String mDescripcion) {
        this.mDescripcion = mDescripcion;
    }

    public String getMfecha() {
        return mfecha;
    }

    public void setMfecha(String mfecha) {
        this.mfecha = mfecha;
    }

    public String getMnivelCritico() {
        return mnivelCritico;
    }

    public void setMnivelCritico(String mnivelCritico) {
        this.mnivelCritico = mnivelCritico;
    }

    public String getMfenomeno() {
        return mfenomeno;
    }

    public void setMfenomeno(String mfenomeno) {
        this.mfenomeno = mfenomeno;
    }

    public String getmLocalidad() {
        return mLocalidad;
    }

    public void setmLocalidad(String mLocalidad) {
        this.mLocalidad = mLocalidad;
    }

    public String getmLatitud() {
        return mLatitud;
    }

    public void setmLatitud(String mLatitud) {
        this.mLatitud = mLatitud;
    }

    public String getmLongitud() {
        return mLongitud;
    }

    public void setmLongitud(String mLongitud) {
        this.mLongitud = mLongitud;
    }

    public String getmAltitud() {
        return mAltitud;
    }

    public void setmAltitud(String mAltitud) {
        this.mAltitud = mAltitud;
    }

    public String getmUsuario() {
        return mUsuario;
    }

    public void setmUsuario(String mUsuario) {
        this.mUsuario = mUsuario;
    }

    @Override
    public String toString() {
        return mId;
    }
}
