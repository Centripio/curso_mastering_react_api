package centripio.masteringreact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import centripio.masteringreact.entity.CarItem;
import centripio.masteringreact.entity.Movie;

public interface CarDAO extends JpaRepository<CarItem, Long>{
	
	CarItem findByMovie(Movie movie);
	
	long deleteByMovie(Movie movie);
}
