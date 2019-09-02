package centripio.masteringreact.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import centripio.masteringreact.dao.MovieDAO;
import centripio.masteringreact.entity.Movie;
import centripio.masteringreact.exception.ValidateServiceException;
import centripio.masteringreact.rest.util.WrapperResponse;

@RestController
@RequestMapping("movies")
public class MovieREST {
	
	@Autowired
	private MovieDAO movieDAO;
	
	@GetMapping
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public WrapperResponse createOrder(@PathVariable("id") Long id) {
		try {
			Optional<Movie> movieOptional = movieDAO.findById(id);
			if(movieOptional.isPresent()) {
				WrapperResponse<Object> response = new WrapperResponse(true, "success", movieOptional.get());
				return response;
			}else {
				WrapperResponse<Object> response = new WrapperResponse(true, "success", null);
				return response;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
	
	@GetMapping
	public WrapperResponse getAllMovies() {
		try {
			List<Movie> movies = movieDAO.findAll();
			WrapperResponse<Object> response = new WrapperResponse(true, "success", movies);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
}