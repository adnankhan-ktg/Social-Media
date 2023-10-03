package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.entity.Post;
import com.app.service.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;

	@GetMapping("/update/{userId}")
	public ResponseEntity<List<Post>> getFeedUpdate(@PathVariable Long userId) {
		List<Post> feedUpdates = feedService.getFeedUpdate(userId);
		return new ResponseEntity<>(feedUpdates, HttpStatus.OK);
	}
}