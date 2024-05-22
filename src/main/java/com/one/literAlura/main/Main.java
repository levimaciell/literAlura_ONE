package com.one.literAlura.main;

import com.one.literAlura.dto.LivroDto;
import com.one.literAlura.dto.ResultadosDto;
import com.one.literAlura.service.ConversorJson;
import com.one.literAlura.service.Requisitor;

import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final Scanner sc = new Scanner(System.in);
    private final Requisitor requisitor = new Requisitor();
    private final ConversorJson conversor = new ConversorJson();

    public void menu(){

        int option = 44;

        while (option != 0){
            exibirMenu();
            option = Integer.parseInt(pegarOpcao("Digite uma opção:"));
            limparConsole();

            String notImpl = "Opção não criada ainda. Por favor, tente mais tarde! :(";
            String urlBase = "https://gutendex.com/books?search=";

            switch (option){
                case 1:
                    String nomeLivro = pegarOpcao("Digite o título do livro desejado:")
                            .trim().replace(" ", "%20");
                    String url = urlBase + nomeLivro;
                    HttpResponse<String> response = requisitor.requisitar(url);

                    ResultadosDto resultados = conversor.converterJsonParaClasse(response.body(), ResultadosDto.class);
                    System.out.println(resultados);
                    break;
                case 2:
                    System.out.println(mensagemEmCaixa(notImpl));
                    break;
                case 3:
                    System.out.println(mensagemEmCaixa(notImpl));
                    break;
                case 4:
                    System.out.println(mensagemEmCaixa(notImpl));
                    break;
                case 5:
                    System.out.println(mensagemEmCaixa(notImpl));
                    break;

                case 0:
                    System.out.println(mensagemEmCaixa("Obrigado por usar a aplicação :)"));
                    break;

                default:
                    System.out.println(mensagemEmCaixa("Opção inválida! Digite uma opção válida!"));
                    break;
            }
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

    private String pegarOpcao(String mensagem){
        System.out.println(mensagemEmCaixa(mensagem));
        return sc.nextLine();
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
