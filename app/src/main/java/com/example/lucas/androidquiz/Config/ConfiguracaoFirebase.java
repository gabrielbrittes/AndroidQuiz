package com.example.lucas.androidquiz.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;

    private static FirebaseAuth autenticacao;

// Retorna a referencia do RealTimeDatabase do Firebase
    public static DatabaseReference getFirebase(){

        if(referenciaFirebase == null) referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        return referenciaFirebase;
    }


    // Retorna a referencia do Authentication do Firebase
    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null) autenticacao = FirebaseAuth.getInstance();
        return autenticacao;
    }

}
