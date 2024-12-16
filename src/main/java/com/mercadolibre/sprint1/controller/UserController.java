package com.mercadolibre.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.sprint1.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
	private IUserService userService;

	@RequestMapping("/{userId}/followers/list")
	public ResponseEntity<?> findAllFollowersByUser(@PathVariable int userId) {
		return ResponseEntity.ok(userService.findAllFollowersByUser(userId));
	}

	@PostMapping("/{userId}/follow/{userIdToFollow}")
	public ResponseEntity<?> followUser(@PathVariable int userId, @PathVariable int userIdToFollow){
		return ResponseEntity.ok(userService.followUser(userId,userIdToFollow));
	}

}
