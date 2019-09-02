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

import centripio.masteringreact.dao.OrderDAO;
import centripio.masteringreact.entity.Order;
import centripio.masteringreact.entity.OrderItem;
import centripio.masteringreact.exception.ValidateServiceException;
import centripio.masteringreact.rest.util.WrapperResponse;

@RestController
@RequestMapping("orders")
public class OrderREST {

	@Autowired
	private OrderDAO orderDao;
	
	@RequestMapping(value="{orderId}", method=RequestMethod.GET)
	public WrapperResponse<Order> getOrderById(@PathVariable("orderId") Long orderId){
		try {
			Optional<Order> optOrder = orderDao.findById(orderId);
			if(!optOrder.isPresent()) {
				throw new ValidateServiceException("Order not found");
			}
			
			Order order = optOrder.get();
			
			
			return new WrapperResponse<>(true, "success", order);
		} catch (ValidateServiceException e) {
			e.printStackTrace();
			return new WrapperResponse(false, e.getMessage()); 
		}catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
	
	@GetMapping
	public WrapperResponse<List<Order>> getAllOrders(){
		try {
			List<Order> orders = orderDao.findAll();
			
			
			
			return new WrapperResponse<>(true, "success", orders);
		} catch (Exception e) {
			e.printStackTrace();
			return new WrapperResponse(false, "Internal Server Error"); 
		}
	}
}
