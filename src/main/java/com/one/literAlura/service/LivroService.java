package com.one.literAlura.service;

import com.one.literAlura.model.Autor;
import com.one.literAlura.model.Livro;
import com.one.literAlura.repository.AutorRepository;
import com.one.literAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    public void salvarLivro(Livro livro){

        for(int i = 0; i < livro.getAutores().size(); i++){
            Autor a = livro.getAutores().get(i);
            Optional<Autor> autor = autorRepository.findByNome(a.getNome());
                //se existir
                if(autor.isPresent()){
                    livro.getAutores().set(i, autor.get());
                }
                //se não existir autor
                else {
                    autorRepository.save(a);
                }
        }

        repository.save(livro);
    }

    public List<Livro> recuperarTodosLivros(){
        return repository.findAll();
    }

    public List<Livro> recuperarLivrosDeIdioma(String idioma) {
        return repository.recuperarLivrosPorIdioma(idioma);
    }
}
