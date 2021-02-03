package com.example.appproyecto.api;

public class APIutils {

    private APIutils() {}

    public static final String BASE_URL = "http://192.168.0.131:8081/MedioAmbiente/rest/";

    public static ObservacionAPI  getObservacionAPI() {

        return RetrofitClient.getClient(BASE_URL).create(ObservacionAPI.class);
    }
}