package com.mercadolibre.sprint1.service;

import com.mercadolibre.sprint1.dto.response.FollowedListByUserDto;
import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;

public interface IUserService {

    FollowersListByUserDto findAllFollowersByUser(int userId);

    boolean followUser (int userId, int userIdToFollow);

    FollowedListByUserDto findUsersFollowedByUser(int id);

}
