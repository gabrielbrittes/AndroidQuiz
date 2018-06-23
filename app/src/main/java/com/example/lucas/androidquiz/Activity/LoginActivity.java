package com.example.lucas.androidquiz.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.androidquiz.Config.ConfiguracaoFirebase;
import com.example.lucas.androidquiz.Entidades.Usuario;
import com.example.lucas.androidquiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{


    private EditText edtEmail;
    private EditText edtSenha;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        Button btnLogar = findViewById(R.id.btnLogar);
        TextView edtIrCadastro = findViewById(R.id.irCadastro);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().isEmpty() && !edtSenha.getText().toString().isEmpty()){

                    usuario = new Usuario();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());

                    validarLogin();
                }else{
                    Toast.makeText(getApplicationContext(), "Os campos E-mail e Senha são obrigatórios!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edtIrCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irTelaCadastro();
            }
        });

    }

    // ====================================== MÉTODOS ==============================================

    public void validarLogin(){

        // Pegando a referencia de autenticação do Firebase
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    irTelaInicial();
                    Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    edtEmail.setText("");
                    edtSenha.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "E-mail ou Senha não são válidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void irTelaInicial(){
        Intent intent = new Intent(LoginActivity.this, InicialActivity.class);
        startActivity(intent);
        finish();
    }

    public void irTelaCadastro(){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }
}
