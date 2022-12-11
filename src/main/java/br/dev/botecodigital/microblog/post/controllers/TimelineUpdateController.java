package br.dev.botecodigital.microblog.post.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.post.dto.PostDTO;
import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.useCases.TimelineUpdateUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TimelineUpdateController {
	
	@Autowired
	private TimelineUpdateUseCase timelineUpdateUseCase;	

	@GetMapping(value="/api/posts/timeline/update")
	public ResponseEntity<List<PostDTO>> timeline(
			HttpServletRequest request, 
			@RequestParam(value = "after", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> optionalAfter
		){
		
		LocalDateTime after = optionalAfter.isPresent() ? optionalAfter.get() : LocalDateTime.now();
		
		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId"));
		
		List<Post> posts = this.timelineUpdateUseCase.execute(authUserId, after);
		
		List<PostDTO> postsDTO = posts.stream().map( (post) -> new PostDTO(post)).toList();  
		
		return ResponseEntity.status(HttpStatus.OK).body(postsDTO);
	}

}
