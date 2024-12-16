package com.mercadolibre.sprint1.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.NewPostDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IRepository<Post> repository;

    @Override
    public String newPost(NewPostDto newPostDto) {
        CResourceUtils.MAPPER.registerModule(new JavaTimeModule());
        CResourceUtils.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try{
            repository.save(CResourceUtils.MAPPER.convertValue(newPostDto, Post.class));
        }catch (Exception e){
            throw new BadRequestException("El objeto enviado no es correcto");
        }
        return "todo OK";
    }
}
