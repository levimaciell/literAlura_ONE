package com.one.literAlura.main;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final Scanner sc = new Scanner(System.in);

    public void menu(){

        int option = 44;

        while (option != 0){
            exibirMenu();
            option = pegarOpcao();
            limparConsole();
        }

    }

    private void exibirMenu() {

        String menu = mensagemEmCaixa(Arrays.asList(
                "1 - Buscar um livro pelo título",
                "2 - Listar livros registrados",
                "3 - Listar autores registrados",
                "4 - Listar autores vivos em um determinado ano",
                "5 - Listar livros em um determinado idioma",
                "",
                "0 - sair"
        ));
        System.out.print(menu);
    }

    private int pegarOpcao(){
        System.out.println(mensagemEmCaixa("Digite uma opção:"));
        return Integer.parseInt(sc.nextLine());
    }

    private String mensagemEmCaixa(String msg){
        int len = msg.length() + 2;
        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = "│ " + msg + " │\n";
        String bottom = "└" + "─".repeat(len) + "┘\n";
        return top + middle + bottom;
    }

    private String mensagemEmCaixa(List<String> mensagens){
        //pegando tamanho da maior mensagem
        int len = mensagens.stream()
                .max(Comparator.comparing(String::length)).get().length();

        //igualando todas mensagens
        int finalLen = len;
        mensagens = mensagens.stream().map(m -> m+=" ".repeat(finalLen - m.length()))
                .collect(Collectors.toList());

        len += 2;

        String top = "┌" + "─".repeat(len) + "┐\n";
        String middle = mensagens.stream().map(m -> "│ " + m + " │\n")
                .reduce("", String::concat);
        String bottom = "└" + "─".repeat(len) + "┘\n";
        return top + middle + bottom;
    }

    private void limparConsole(){
        for (int i = 0; i < 30; i++){
            System.out.println();
        }
    }
}
