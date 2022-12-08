package br.dev.botecodigital.microblog.shared.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.dev.botecodigital.microblog.shared.FileStoreService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ImageController {

	@Autowired
	private FileStoreService fileStoreService;

	@GetMapping(value = "/images/**")
	public ResponseEntity<Object> image(HttpServletRequest request) {

		String path = request.getRequestURI().split(request.getContextPath() + "/images/")[1];

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG)
				.body(fileStoreService.getImageJpgFile(path));

	}
}
