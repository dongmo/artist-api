package org.sid.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.sid.domaine.Artist;
import org.sid.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@Validated
public class ArtistController {

	@Autowired
	private ArtistService artistService;
	
	  @GetMapping("/artists")
	    public ResponseEntity<?>getAllArtist(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
	        return ResponseEntity.ok().body(artistService.findAll(page, size));
	    }

	    @GetMapping("/artists/{username}")
	    public ResponseEntity<Artist> getArtistByUsername(@PathVariable String username) throws NotFoundException {
	        return ResponseEntity.ok().body(artistService.findByUsername(username));
	    }

	    @PostMapping("/artists")
	    public ResponseEntity<?> createArtist(@Valid @RequestBody Artist artist) {
	    	
	        return ResponseEntity.ok().body(artistService.create(artist));
	    }

	    @PutMapping("/artists/{username}")
	    public ResponseEntity<Artist> updateArtist(@PathVariable @NotEmpty @Valid String username, @RequestBody Artist artist) {
	        return ResponseEntity.ok().body(this.artistService.update(artist, username));
	    }

	    @DeleteMapping("/artists/{username}")
	    public HttpStatus deleteArtist(@PathVariable String username) throws NotFoundException {
	        this.artistService.delete(username);
	        return HttpStatus.OK;
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	     
	        ex.getBindingResult().getFieldErrors().forEach(error -> 
	            errors.put(error.getField(), error.getDefaultMessage()));
	         
	        return errors;
	    }
	    
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler(ConstraintViolationException.class)
	    public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
	        Map<String, String> errors = new HashMap<>();
	         
	        ex.getConstraintViolations().forEach(cv -> {
	            errors.put("message", cv.getMessage());
	            errors.put("path", (cv.getPropertyPath()).toString());
	        }); 
	     
	        return errors;
	    }
}
