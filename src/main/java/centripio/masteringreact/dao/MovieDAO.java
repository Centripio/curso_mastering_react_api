package centripio.masteringreact.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import centripio.masteringreact.entity.Movie;

public interface MovieDAO extends JpaRepository<Movie, Long> {

}
