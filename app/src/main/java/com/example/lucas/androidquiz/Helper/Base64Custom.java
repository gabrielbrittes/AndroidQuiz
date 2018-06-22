package com.example.lucas.androidquiz.Helper;

import android.support.annotation.NonNull;
import android.util.Base64;

public class Base64Custom {

    public static String coficadorBase64(String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n/\\r)","");
    }

    public static String decoficadarBase64(String textoCodificado){
        return new String(textoCodificado.getBytes(), Base64.DEFAULT);
    }
}
