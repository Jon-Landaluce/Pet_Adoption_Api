package com.mascotas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Mascota {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(name = "fecha_nac", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNac;
	@Column(nullable = false)
	private String raza;
	@Column(nullable = false)
	private float peso;
	@Column(nullable = false)
	private boolean tiene_chip;
	@Column(nullable = false)
	private String url_foto;

}
