package com.one.literAlura.main;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {
    private Scanner sc;

    public UI(Scanner sc){this.sc = sc;}

    public String pegarOpcao(String mensagem){
        System.out.println(mensagemEmCaixa(mensagem));
        return sc.nextLine();
    }
    public Integer pegarOpcaoNumerica(String mensagem){
        System.out.println(mensagemEmCaixa(mensagem));
        try{
            var string = sc.nextLine();
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    public String mensagemEmCaixa(String msg){
        int len = msg.length() + 2;
        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = "│ " + msg + " │\n";
        String bottom = "└" + "─".repeat(len) + "┘\n";
        return top + middle + bottom;
    }

    public static String mensagemEmCaixa(List<String> mensagens, Integer paddingHorizontal){
        //pegando tamanho da maior mensagem
        int len = maiorStringDeLista(mensagens);

        //igualando todas strings
        int finalLen = len;
        mensagens = mensagens.stream().map(m -> m+=" ".repeat(finalLen - m.length()))
                .collect(Collectors.toList());

        len += paddingHorizontal + paddingHorizontal;

        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = mensagens.stream().map(m -> "│" + " ".repeat(paddingHorizontal) + m + " ".repeat(paddingHorizontal) + "│\n")
                .reduce("", String::concat);
        String bottom = "└" + "─".repeat(len) + "┘\n";
        return top + middle + bottom;
    }

    public static Integer maiorStringDeLista(List<String> list){
        return list.stream()
                .max(Comparator.comparing(String::length)).get().length();
    }

    public void limparConsole(){
        for (int i = 0; i < 30; i++){
            System.out.println();
        }
    }

}
