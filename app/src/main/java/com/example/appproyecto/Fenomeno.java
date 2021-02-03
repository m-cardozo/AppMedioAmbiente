package com.example.appproyecto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fenomeno {

    @SerializedName("fenomenoId")
    @Expose
    private Long fenomenoId;

    public Fenomeno(Long fenomenoId) {
        this.fenomenoId = fenomenoId;
    }

    public Long getFenomenoId() {
        return fenomenoId;
    }

    public void setFenomenoId(Long fenomenoId) {
        this.fenomenoId = fenomenoId;
    }

    @Override
    public String toString() {
        return "Fenomeno {" +
                "fenomenoId =" + fenomenoId +
                '}';
    }
}