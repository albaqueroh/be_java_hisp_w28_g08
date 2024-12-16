package com.mercadolibre.sprint1.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.dto.ProductDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.Product;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    PostRepositoryImpl repository;
    @Override
    public String createPromoPost(CreatePromoPostDto postPromo) {
        CResourceUtils.MAPPER.registerModule(new JavaTimeModule());
        CResourceUtils.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        if(postPromo != null){
            Post savedPost = repository.save(CResourceUtils.MAPPER.convertValue(postPromo, Post.class));

            return "Post guardado";
        }else {
            throw new BadRequestException("El objeto enviado no es correcto");
        }

    }
}
