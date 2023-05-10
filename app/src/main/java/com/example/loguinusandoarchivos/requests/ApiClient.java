package com.example.loguinusandoarchivos.requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.loguinusandoarchivos.models.Persona;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ApiClient {
    public static void guardar(Context context, Persona usuario){
        File archivo=new File(context.getFilesDir(),"usuario.dat");
        try {
            FileOutputStream fos=new FileOutputStream(archivo);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            Log.d("guardar: ",usuario.toString());
            oos.writeObject(usuario);
            bos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Persona leer(Context context){
        File archivo=new File(context.getFilesDir(),"usuario.dat");
        try {
            FileInputStream fis=new FileInputStream(archivo);
            BufferedInputStream bis=new BufferedInputStream(fis);
            ObjectInputStream ois=new ObjectInputStream(bis);
            Persona usuario=(Persona)ois.readObject();
            ois.close();
            Log.d("lectura:  ",usuario.toString());
            return usuario;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Persona login(Context context, String mail, String password){
        Persona usuario = leer(context);
        if(mail.equals(usuario.getMail()) && password.equals(usuario.getPassword())){
            return usuario;
        }
        return null;
    }
}
