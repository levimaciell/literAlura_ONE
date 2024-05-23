package com.one.literAlura.main;

import com.one.literAlura.dto.LivroDto;
import com.one.literAlura.dto.ResultadosDto;
import com.one.literAlura.model.Livro;
import com.one.literAlura.service.ConversorJson;
import com.one.literAlura.service.LivroService;
import com.one.literAlura.service.Requisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner sc = new Scanner(System.in);
    private final Requisitor requisitor = new Requisitor();
    private final ConversorJson conversor = new ConversorJson();
    private UI ui = new UI(sc);
    private LivroService service;

    private static final String URL_BASE = "https://gutendex.com/books?search=";

    public Main(LivroService service) {
        this.service = service;
    }


    public void menu(){

        int option = 44;

        while (option != 0){
            exibirMenu();
            option = ui.pegarOpcaoNumerica("Digite uma opção:");
            ui.limparConsole();

            String notImpl = "Opção não criada ainda. Por favor, tente mais tarde! :(";

            switch (option){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    System.out.println(ui.mensagemEmCaixa(notImpl));
                    break;
                case 3:
                    System.out.println(ui.mensagemEmCaixa(notImpl));
                    break;
                case 4:
                    System.out.println(ui.mensagemEmCaixa(notImpl));
                    break;
                case 5:
                    System.out.println(ui.mensagemEmCaixa(notImpl));
                    break;

                case 0:
                    System.out.println(ui.mensagemEmCaixa("Obrigado por usar a aplicação :)"));
                    break;

                default:
                    System.out.println(ui.mensagemEmCaixa("Opção inválida! Digite uma opção válida!"));
                    break;
            }
        }

    }

    private void exibirMenu() {

        String menu = UI.mensagemEmCaixa(Arrays.asList(
                "1 - Buscar um livro pelo título",
                "2 - Listar livros registrados",
                "3 - Listar autores registrados",
                "4 - Listar autores vivos em um determinado ano",
                "5 - Listar livros em um determinado idioma",
                "",
                "0 - sair"
        ), 3);
        System.out.print(menu);
    }

    private void buscarLivroPorTitulo(){
        String nomeLivro = ui.pegarOpcao("Digite o título do livro desejado:")
                .trim().replace(" ", "%20");
        String url = URL_BASE + nomeLivro;
        HttpResponse<String> response = requisitor.requisitar(url);

        ResultadosDto resultados = conversor.converterJsonParaClasse(response.body(), ResultadosDto.class);

        //Salvando somente o primeiro livro encontrado.
        if(resultados.resultados_encontrados() > 0){
            List<LivroDto> livrosList = resultados.livros();

            Livro livro = new Livro(livrosList.getFirst());
            service.salvarLivro(livro);

            System.out.println(livro);
        }
        else {
            System.out.println(ui.mensagemEmCaixa("Não foram encontrados resultados com o título informado"));
        }

    }
}
