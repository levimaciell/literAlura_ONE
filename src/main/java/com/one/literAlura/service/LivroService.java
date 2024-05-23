package com.one.literAlura.service;

import com.one.literAlura.model.Livro;
import com.one.literAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public void salvarLivro(Livro livro){
        repository.save(livro);
    }
}
