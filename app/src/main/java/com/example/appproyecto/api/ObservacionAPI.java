package com.example.appproyecto.api;

import com.example.appproyecto.Observacion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ObservacionAPI {

    @DELETE("observaciones/eliminar/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("observaciones/agregar")
    Call<Observacion> add(@Body Observacion observacion);

    @PUT ("observaciones/editar")
    Call<Observacion> edit(@Body Observacion observacion);

    @GET ("usuarios/login/{usuario}/{password}")
    Call<String> login(@Path("usuario") String usuario,
                     @Path("password") String password);
}
