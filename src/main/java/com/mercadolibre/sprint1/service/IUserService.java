package com.mercadolibre.sprint1.service;

import com.mercadolibre.sprint1.dto.response.FollowedListByUserDto;
import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;
import com.mercadolibre.sprint1.entity.User;

public interface IUserService {

    FollowersListByUserDto findAllFollowersByUser(int userId, String order);

    boolean followUser (int userId, int userIdToFollow);


    User findUserById (int userId);

    FollowedListByUserDto findUsersFollowedByUser(int id, String order);

}
