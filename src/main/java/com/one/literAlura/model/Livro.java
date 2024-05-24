package com.one.literAlura.model;

import com.one.literAlura.dto.LivroDto;
import com.one.literAlura.main.UI;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Autor> autores;
    private String idioma;
    private Integer downloads;

    public Livro(){}

    public Livro(LivroDto dto){
        this.titulo = dto.titulo();
        this.autores = dto.autores().stream()
                .map(Autor::new).collect(Collectors.toList());
        this.idioma = dto.idiomas().getFirst();
        this.downloads = dto.numeroDownloads();
    }

    public Livro(String titulo, List<Autor> autores, String idioma, Integer downloads) {
        this.titulo = titulo;
        this.autores = autores;
        this.idioma = idioma;
        this.downloads = downloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        List<String> st = autores.stream()
                .map(Autor::getNome)
                .collect(Collectors.toList());
        String primeiroAutor = st.removeFirst();

        st.addFirst("Escrito por: " + primeiroAutor + ",");
        st.addFirst(titulo);
        st.addFirst("");
        st.addFirst("LIVRO");

        st.addLast( "Idioma: " + idioma);
        st.addLast("Número de downloads: " + String.valueOf(downloads));

        return UI.mensagemEmCaixa(st, 3);
    }
}
