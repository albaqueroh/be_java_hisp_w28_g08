package com.mercadolibre.sprint1.service.impl;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
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
    public CreatePromoPostDto createPromoPost(CreatePromoPostDto postPromo) {
        if(postPromo != null){
            Post savedPost = repository.save(new Post(
                    (repository.findAll().get(-1).getId()+1),
                    CResourceUtils.formatToLocalDate(postPromo.getDate()),
                    postPromo.getUserId(),
                    CResourceUtils.MAPPER.convertValue(postPromo.getProduct(), Product.class),
                    postPromo.getCategory(),
                    postPromo.getPrice(),
                    postPromo.isHasPromo(),
                    postPromo.getDiscount()
            ));
            return CResourceUtils.MAPPER.convertValue(savedPost, CreatePromoPostDto.class);
        }else {
            throw new BadRequestException("El objeto enviado no es correcto");
        }

    }
}
