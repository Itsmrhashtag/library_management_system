package org.hashtag.library_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	@GetMapping("/")
	public String hi() {
		return "Welcome to application";
	}

}