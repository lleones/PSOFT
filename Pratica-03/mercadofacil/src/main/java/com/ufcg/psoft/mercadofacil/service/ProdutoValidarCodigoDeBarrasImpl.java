package com.ufcg.psoft.mercadofacil.service;

public class ProdutoValidarCodigoDeBarrasImpl implements ProdutoValidarCodigoDeBarras {

    @Override
    public boolean vadidate(String codigo) {
        boolean valido = false;
        int soma = 0;

        String[] split = codigo.split("");

        int[] numeros = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            numeros[i] = Integer.parseInt(split[i]);
        }

        //Passo 1
        for (int i = numeros.length - 2; i > 0; i = i-2) {
            soma += numeros[i];
        }
        
        //Passo 2
        soma = soma * 3;
        
        //Passo 3 e 4
        for (int i = numeros.length - 3; i >= 0 ; i = i-2) {
            soma += numeros[i];
        }

        //Passo 5
        return ((soma + numeros[numeros.length - 1]) % 10) == 0 ;
    }
}
