package com.one.literAlura.main;

import com.one.literAlura.dto.LivroDto;
import com.one.literAlura.dto.ResultadosDto;
import com.one.literAlura.model.Autor;
import com.one.literAlura.model.Livro;
import com.one.literAlura.service.AutorService;
import com.one.literAlura.service.ConversorJson;
import com.one.literAlura.service.LivroService;
import com.one.literAlura.service.Requisitor;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner sc = new Scanner(System.in);
    private final Requisitor requisitor = new Requisitor();
    private final ConversorJson conversor = new ConversorJson();
    private final UI ui = new UI(sc);
    private final LivroService service;
    private final AutorService autorService;

    private static final String URL_BASE = "https://gutendex.com/books?search=";

    public Main(LivroService service, AutorService autorService) {
        this.service = service;
        this.autorService = autorService;
    }

    public void menu(){

        int option = 44;

        while (option != 0){
            exibirMenu();
            option = ui.pegarOpcaoNumerica();
            ui.limparConsole();

            switch (option){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosDeDeterminadoIdioma();
                    break;

                case 0:
                    System.out.println(ui.mensagemEmCaixa("Obrigado por usar a aplicação :)", 6));
                    break;

                default:
                    System.out.println(ui.mensagemEmCaixa("Opção inválida! Digite uma opção válida!", 4));
                    break;
            }
        }

    }

    private void exibirMenu() {
        String menu = UI.mensagemEmCaixaComInput(Arrays.asList(
                "1 - Buscar um livro pelo título",
                "2 - Listar livros registrados",
                "3 - Listar autores registrados",
                "4 - Listar autores vivos em um determinado ano",
                "5 - Listar livros em um determinado idioma",
                "",
                "0 - sair"
        ), 3, "Digite uma opção:");
        System.out.print(menu);
    }

    private void exibirMenuIdiomas(){
        String menu = UI.mensagemEmCaixa(Arrays.asList(
                "1 - Inglês(en)",
                "2 - Espanhol(es)",
                "3 - Francês(fr)",
                "4 - Português(pt)"
        ),3);
        System.out.println(menu);
    }

    private void buscarLivroPorTitulo(){
        String nomeLivro = ui.pegarOpcao("Digite o título do livro desejado:", 6)
                .trim().replace(" ", "%20");
        String url = URL_BASE + nomeLivro;
        System.out.println("\nesperando...");

        HttpResponse<String> response = requisitor.requisitar(url);

        ui.limparConsole();

        ResultadosDto resultados = conversor.converterJsonParaClasse(response.body(), ResultadosDto.class);

        //Salvando somente o primeiro livro encontrado.
        if(resultados.resultados_encontrados() > 0){
            List<LivroDto> livrosList = resultados.livros();

            Livro livro = new Livro(livrosList.getFirst());
            service.salvarLivro(livro);

            System.out.println(livro);
        }
        else {
            System.out.println(ui.mensagemEmCaixa("Não foram encontrados resultados com o título informado", 6));
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livrosEncontrados = service.recuperarTodosLivros();

        if(livrosEncontrados.isEmpty())
            System.out.println(ui.mensagemEmCaixa("Não foram encontrados registros de livros!", 6));
        else {
            System.out.println(UI.listarVariosLivros(livrosEncontrados));
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autoresEncontrados = autorService.recuperarTodosAutores();
        if(autoresEncontrados.isEmpty())
            System.out.println(ui.mensagemEmCaixa("Não foram encontrados registros de autores!", 6));
        else {
            System.out.println(UI.listarVariosAutores(autoresEncontrados));
        }
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.print(ui.mensagemEmCaixa("Digite um ano:", 6));
        System.out.print("┌" + "───" + "> ");
        Integer ano = ui.pegarOpcaoNumerica();
        List<Autor> autoresVivos = autorService.recuperarTodosAutoresVivos(ano);

        System.out.println(UI.listarVariosAutores(autoresVivos));

    }

    private void listarLivrosDeDeterminadoIdioma() {
        exibirMenuIdiomas();
        String opcao = ui.pegarOpcao("Por favor, digite a opção desejada (Somente sigla do idioma): ", 3);

        List<Livro> livrosEncontrados = service.recuperarLivrosDeIdioma(opcao);
        System.out.println(UI.listarVariosLivros(livrosEncontrados));

    }
}
