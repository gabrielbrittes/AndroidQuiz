package com.example.lucas.androidquiz.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.androidquiz.Config.ConfiguracaoFirebase;
import com.example.lucas.androidquiz.Entidades.Usuario;
import com.example.lucas.androidquiz.Helper.Base64Custom;
import com.example.lucas.androidquiz.Helper.Preferencias;
import com.example.lucas.androidquiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtCadEmail = findViewById(R.id.edtCadEmail);
        edtCadSenha = findViewById(R.id.edtCadSenha);
        final EditText edtCadConfirmSenha = findViewById(R.id.edtCadConfirmSenha);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtCadSenha.getText().toString().equals(edtCadConfirmSenha.getText().toString())){
                    usuario = new Usuario();
                    usuario.setNome(edtNome.getText().toString());
                    usuario.setEmail(edtCadEmail.getText().toString());
                    usuario.setSenha(edtCadSenha.getText().toString());


                }else{
                    Toast.makeText(getApplicationContext(), "As senhas não são correspondentes!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro de usuário realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    String identificadorUsuario = Base64Custom.coficadorBase64(usuario.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(identificadorUsuario);
                    usuario.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);

                }
            }
        });
    }
}
