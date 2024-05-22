package com.one.literAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.one.literAlura.exceptions.ConversorJsonException;

import java.util.List;

public class ConversorJson implements IConversorJson{
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T converterJsonParaClasse(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        }
        catch (JsonProcessingException e){
            throw new ConversorJsonException(e.getMessage());
        }
    }

    @Override
    public <T> List<T> converterListaJsonParaLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try{
            return mapper.readValue(json, lista);
        }
        catch (JsonProcessingException e){
            throw new ConversorJsonException(e.getMessage());
        }
    }
}
