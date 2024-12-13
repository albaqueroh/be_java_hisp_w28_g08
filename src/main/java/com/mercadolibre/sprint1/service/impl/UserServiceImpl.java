package com.mercadolibre.sprint1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.sprint1.dto.UserDto;
import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;
import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.exception.NotFoundException;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
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
        User user = findUserById(userId);
        List<UserDto> followers = userFollowerRepositoryImpl.findAll()
                .stream()
                .filter(f -> f.getUserFollowed() == userId)
                .map(f -> findUserById(f.getUserFollower()))
                .map(u -> new UserDto(u.getId(), u.getName()))
                .toList();

        FollowersListByUserDto res = new FollowersListByUserDto();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setFollowers(followers);

        return res;
    }

    private User findUserById(int userId) {
        return userRepositoryImpl.findAll()
                .stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No existe el usuario " + userId));
    }

}
