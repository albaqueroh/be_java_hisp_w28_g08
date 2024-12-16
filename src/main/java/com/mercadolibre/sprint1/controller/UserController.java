package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.service.IUserFollowerService;
import com.mercadolibre.sprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private IUserService userService;
    @Autowired
    private IUserFollowerService userFollowerService;

	@GetMapping("/{userId}/followers/list")
	public ResponseEntity<?> findAllFollowersByUser(@PathVariable int userId, @RequestParam(required = false) String order) {
		return ResponseEntity.ok(userService.findAllFollowersByUser(userId, order));
	}

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollow(@PathVariable("userId") int userId, @PathVariable("userIdToUnfollow") int sellerId){
        return new ResponseEntity<>(userFollowerService.unfollow(userId,sellerId), HttpStatus.OK);
    }


	@PostMapping("/{userId}/follow/{userIdToFollow}")
	public ResponseEntity<?> followUser(@PathVariable int userId, @PathVariable int userIdToFollow){
		return ResponseEntity.ok(userService.followUser(userId,userIdToFollow));
	}

}
