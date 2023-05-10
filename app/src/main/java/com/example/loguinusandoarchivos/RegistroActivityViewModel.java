package com.example.loguinusandoarchivos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loguinusandoarchivos.models.Persona;
import com.example.loguinusandoarchivos.requests.ApiClient;


public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Persona> mutableUsuario;
    private MutableLiveData<String> mutableError;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mutableUsuario = new MutableLiveData<>();
        mutableError = new MutableLiveData<>();
    }

    public MutableLiveData<String> getMutableError() { return mutableError; }
    public LiveData<Persona> getMutableUsuario(){
        return mutableUsuario;
    }
    public void verificarLlamada(boolean logueado){
        if(logueado){
            Persona userLogueado = ApiClient.leer(context);
            mutableUsuario.setValue(userLogueado);
        }
    }
    public void guardarUsuario(Persona user){
        //validacion de datos para campos vacios
        Log.d("validar: ",user.getMail() + ", " + user.getPassword() );
        if(validarCampos(user)){
            ApiClient.guardar(context,user);
            mutableUsuario.setValue(user);
            Toast.makeText(context, "SE GUARDARON CORRECTAMENTE LOS DATOS!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            mutableError.setValue("Debe ingresar al menos usuario y password");
        }
    }

    private boolean validarCampos(Persona user){

        return !(user.getPassword().equals("") || user.getMail().equals(""));
    }
}
