package centripio.masteringreact.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie implements Serializable{
	
	public Movie() {
	}
	
	public Movie(Long id) {
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false, length=200)
	private String title;
	
	@Column(name="resumen", nullable=false, length=500)
	private String resumen;
	
	@Column(name="year", nullable=false, length=4)
	private String year;
	
	@Column(name="gender", nullable=false, length=100)
	private String gender;
	
	@Column(name="director", nullable=false, length=100)
	private String director;
	
	@Column(name="languaje", nullable=false, length=30)
	private String languaje;
	
	@Column(name="country", nullable=false, length=15)
	private String country;
	
	@Column(name="price", nullable=false)
	private Double price;
	
	@Column(name="banner", nullable=false, length=1000)
	private String banner;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getLanguaje() {
		return languaje;
	}
	public void setLanguaje(String languaje) {
		this.languaje = languaje;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
