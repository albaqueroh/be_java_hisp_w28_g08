package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.service.IUserFollowerService;
import com.mercadolibre.sprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

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

	@GetMapping("/{userId}/followers/count")
	public ResponseEntity<?> followersCountById(@PathVariable int userId){
		return ResponseEntity.ok(userFollowerService.followersCount(userId));
	}

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollow(@PathVariable("userId") int userId, @PathVariable("userIdToUnfollow") int sellerId){
        return new ResponseEntity<>(userFollowerService.unfollow(userId,sellerId), HttpStatus.OK);
    }

	@PostMapping("/{userId}/follow/{userIdToFollow}")
	public ResponseEntity<?> followUser(@PathVariable int userId, @PathVariable int userIdToFollow){
		userService.followUser(userId,userIdToFollow);
		return ResponseEntity.ok(null);
	}

	@GetMapping("/{userid}/followed/list")
	public ResponseEntity<?> findUsersFollowedByUser(@PathVariable int userid, @RequestParam(required = false) String order) {
		return ResponseEntity.ok(userService.findUsersFollowedByUser(userid,order));
	}

	@GetMapping("/average-promos")
	public ResponseEntity<?> findUserPromoAverage(){

		return ResponseEntity.ok(userService.findUserPromoAverage());
	}

	@GetMapping("/most-followed/posts")
	public ResponseEntity<?> userMostFollwed(){
		return ResponseEntity.ok(userService.findMostFllowedUser());
	}
}
