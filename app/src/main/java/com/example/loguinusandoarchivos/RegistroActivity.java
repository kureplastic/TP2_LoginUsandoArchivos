package com.example.loguinusandoarchivos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loguinusandoarchivos.databinding.ActivityRegistroBinding;
import com.example.loguinusandoarchivos.models.Persona;


public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        //vm.VerificarLlamada se llama al inicio y corrobora de donde viene la llamada de la activity
        vm.verificarLlamada(getIntent().getBooleanExtra("logueado",false));
        //observer para cambios en el mutable
        vm.getMutableUsuario().observe(this, new Observer<Persona>() {
            @Override
            public void onChanged(Persona persona) {
               binding.etNombre.setText(persona.getNombre());
               binding.etApellido.setText(persona.getApellido());
               binding.etDni.setText(persona.getDni());
               binding.etMail.setText(persona.getMail());
               binding.etPass.setText(persona.getPassword());
            }
        });
        //listener para el boton guardar
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona user = new Persona(
                        binding.etNombre.getText().toString(),
                        binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etPass.getText().toString(),
                        binding.etMail.getText().toString()
                );
                Log.d("datos: ",user.toString());
                vm.guardarUsuario(user);
            }
        });
        vm.getMutableError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                binding.etMail.setError(error);
                binding.etPass.setError(error);
            }
        });
        setContentView(binding.getRoot());
    }
}