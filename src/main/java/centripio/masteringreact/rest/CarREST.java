package centripio.masteringreact.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import centripio.masteringreact.dao.CarDAO;
import centripio.masteringreact.dao.MovieDAO;
import centripio.masteringreact.dao.OrderDAO;
import centripio.masteringreact.entity.CarItem;
import centripio.masteringreact.entity.Movie;
import centripio.masteringreact.entity.Order;
import centripio.masteringreact.entity.OrderItem;
import centripio.masteringreact.exception.ValidateServiceException;
import centripio.masteringreact.rest.util.WrapperResponse;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("car")
public class CarREST {
	
	@Autowired
	private CarDAO carDAO;
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private OrderDAO orderDao;
	
	@GetMapping
	public WrapperResponse getCar() {
		try {
			List<CarItem> currentCar = carDAO.findAll();
			WrapperResponse<List> response = new WrapperResponse(true, "success", currentCar);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error");
		}
	}
	
	@Transactional
	@PutMapping
	public WrapperResponse addItem(@RequestBody Movie movie) {
		try {
			Optional<Movie> movieOptional = movieDAO.findById(movie.getId());
			
			if(!movieOptional.isPresent()) {
				throw new ValidateServiceException("Movie not found");
			}
			
			Movie queryMovie = movieOptional.get();
			
			CarItem exist = carDAO.findByMovie(queryMovie);
			if(exist == null) {
				CarItem newItem = new CarItem();
				newItem.setMovie(queryMovie);
				newItem = carDAO.save(newItem);
			}
			List<CarItem> currentCar = carDAO.findAll();
			WrapperResponse<List> response = new WrapperResponse(true, "success", currentCar);
			return response;
		}catch (ValidateServiceException e) {
			e.printStackTrace();
			return new WrapperResponse(false, e.getMessage()); 
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
	
	@Transactional
	@RequestMapping(value="{movieId}", method=RequestMethod.DELETE)
	public WrapperResponse deleteItem(@PathVariable("movieId") Long movieId) {
		try {
			Optional<Movie> movieOptional = movieDAO.findById(movieId);
			
			if(movieOptional.isPresent()) {
				long deleteCount = carDAO.deleteByMovie(movieOptional.get());
			}
			
			List<CarItem> currentCar = carDAO.findAll();
			WrapperResponse<List> response = new WrapperResponse(true, "success", currentCar);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
	
	@Transactional
	@RequestMapping(value="checkout", method=RequestMethod.POST)
	public WrapperResponse checkout() {
		try {
			Order order = new Order();
			order.setCreateDate(Calendar.getInstance());
			order.setNumber(UUID.randomUUID().toString());
			order.setProducts(new ArrayList<>());
			
			List<CarItem> items = carDAO.findAll();
			for(CarItem item : items) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(order);
				orderItem.setPrice(item.getMovie().getPrice());
				orderItem.setProductId(item.getMovie().getId());
				orderItem.setProductName(item.getMovie().getTitle());
				order.getProducts().add(orderItem);
			}
			
			order= orderDao.save(order);
			carDAO.deleteAll();
			 
			WrapperResponse<List> response = new WrapperResponse(true, "success", order);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
}
