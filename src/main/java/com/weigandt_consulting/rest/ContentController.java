package com.weigandt_consulting.rest;

import com.weigandt_consulting.model.Content;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ContentController {
	@GetMapping("contents")
	public List<Content> getAllEntities() {
		return new ArrayList<Content>() {{
			add(new Content(1L, "content"));
		}};
	}

	@GetMapping("contents/{id}")
	public Content getEntity(@PathVariable Long id) {
		return new Content(id, "content");
	}

	@GetMapping("users/{userId}/contents")
	public List<Content> getEntities(@PathVariable Long userId) {
		return new ArrayList<Content>() {{
			add(new Content(1L, "content"));
		}};
	}

	@PostMapping("users/{userId}/contents")
	public Content createEntities(@PathVariable Long userId, @PathVariable Long id, @RequestBody ContentRequest request) {
		return new Content(1L, "content");
	}

	@PutMapping("users/{userId}/contents/{id}")
	public Content updateEntities(@PathVariable Long userId, @PathVariable Long id, @RequestBody ContentRequest request) {
		return new Content(1L, "content");
	}

	@DeleteMapping("users/{userId}/contents/{id}")
	public void deleteEntities(@PathVariable Long userId, @PathVariable Long id) {

	}
}
