package com.gestao.restaurante.utils;

import com.gestao.restaurante.models.MesaModel;

import java.util.ArrayList;

public class Util {

    public static ArrayList<MesaModel> gerarMesas(int quantidade){
        ArrayList<MesaModel> mesaModels = new ArrayList<>();
        for (int i=0; i<quantidade;i++){
            MesaModel mesaModel = new MesaModel();
            //Criar metodo para gerar QRCODE
            mesaModels.add(mesaModel);
        }
        return mesaModels;
    }

    public static String generateQr(){
        return null;
    }
}
