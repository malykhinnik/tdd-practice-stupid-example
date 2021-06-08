package com.weigandt_consulting.rest;

import com.weigandt_consulting.model.Content;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contents")
public class ContentController {
	@GetMapping("{id}")
	public Content getEntities(@PathVariable Long id) {
		return new Content(id, "content");
	}
}
