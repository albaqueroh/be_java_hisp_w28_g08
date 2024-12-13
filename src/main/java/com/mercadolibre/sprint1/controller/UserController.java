package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.service.IUserFollowerService;
import com.mercadolibre.sprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private IUserService userService;
    @Autowired
    private IUserFollowerService userFollowerService;

	@RequestMapping("/{userId}/followers/list")
	public ResponseEntity<?> findAllFollowersByUser(@PathVariable int userId) {
		return ResponseEntity.ok(userService.findAllFollowersByUser(userId));
	}
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollow(@PathVariable("userId") int userId, @PathVariable("userIdToUnfollow") int sellerId){
        return new ResponseEntity<>(userFollowerService.unfollow(userId,sellerId), HttpStatus.OK);
    }
}
