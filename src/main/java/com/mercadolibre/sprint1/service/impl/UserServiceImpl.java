package com.mercadolibre.sprint1.service.impl;

import java.util.List;

import com.mercadolibre.sprint1.dto.response.FollowedListByUserDto;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.exception.BadRequestException;

import com.mercadolibre.sprint1.dto.UserDto;

import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;
import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.exception.NotFoundException;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import com.mercadolibre.sprint1.service.IUserService;
import com.mercadolibre.sprint1.utils.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private UserFollowerRepositoryImpl userFollowerRepositoryImpl;

    @Override
    public FollowersListByUserDto findAllFollowersByUser(int userId, String order) {
        User user = findUserById(userId);
        List<UserDto> followers = userFollowerRepositoryImpl.findAll()
                .stream()
                .filter(f -> f.getUserFollowed() == userId)
                .map(f -> findUserById(f.getUserFollower()))
                .map(u -> new UserDto(u.getId(), u.getName()))
                .toList();
        if(order != null){
            followers = orderUserDtoList(followers,order);
        }
        FollowersListByUserDto res = new FollowersListByUserDto();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setFollowers(followers);
        return res;
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
        throw new BadRequestException("El usuario no es un vendedor. " + userIdToFollow);
    }

    @Override
    public FollowedListByUserDto findUsersFollowedByUser(int id) {
        User user = findUserById(id);
        List<UserDto> followed = userFollowerRepositoryImpl.findAll()
                .stream()
                .filter(f -> f.getUserFollower() == id)
                .map(f -> findUserById(f.getUserFollowed()))
                .map(u -> new UserDto(u.getId(), u.getName()))
                .toList();

        FollowedListByUserDto res = new FollowedListByUserDto();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setFollowed(followed);

        return res;
    }

    private User findUserById(int userId) {
        return userRepositoryImpl.findAll()
                .stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No existe el usuario " + userId));
    }
    private List<UserDto> orderUserDtoList(List<UserDto> userDtoList, String order){
            return userDtoList.stream().sorted((f1, f2) -> {
                if (order.equalsIgnoreCase(String.valueOf(Order.NAME_ASC))) {
                    return f1.getName().compareTo(f2.getName());
                } else if (order.equalsIgnoreCase(String.valueOf(Order.NAME_DESC))) {
                    return f2.getName().compareTo(f1.getName());
                } else {
                    throw new BadRequestException("El orden requerido no es valido.");
                }
            }).toList();
    }

}
