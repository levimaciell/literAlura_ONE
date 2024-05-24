package com.one.literAlura.service;

import com.one.literAlura.model.Autor;
import com.one.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public List<Autor> recuperarTodosAutores(){
        return repository.findAll();
    }

    public List<Autor> recuperarTodosAutoresVivos(Integer ano){
        return repository.buscarAutoresVivos(ano);
    }
}
