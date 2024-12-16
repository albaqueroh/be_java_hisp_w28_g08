package com.mercadolibre.sprint1.service.impl;

import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;
import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.exception.NotFoundException;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import com.mercadolibre.sprint1.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private UserFollowerRepositoryImpl userFollowerRepositoryImpl;

    @Override
    public FollowersListByUserDto findAllFollowersByUser(int userId) {
        findUserById(userId);
        return null;
    }

    @Override
    public boolean followUser(int userId, int userIdToFollow) {
        User findSeller = findUserById(userIdToFollow);
        if(findSeller.isSeller()){
            UserFollower userFollower = new UserFollower();
            userFollower.setUserFollowed(userIdToFollow);
            userFollower.setUserFollower(userId);
            userFollowerRepositoryImpl.save(userFollower);
            return true;
        }
        throw new BadRequestException("El usuario " + userIdToFollow + " no es un vendedor.");
    }

    private User findUserById(int userId) {
        return userRepositoryImpl.findAll()
                .stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No existe el usuario " + userId));
    }

}
