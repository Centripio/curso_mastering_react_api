package centripio.masteringreact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import centripio.masteringreact.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{

}
