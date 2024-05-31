package com.mascotas.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mascotas.data.MascotaRepository;
import com.mascotas.model.Mascota;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MascotaController {
	
	@Autowired
	private MascotaRepository mascotaRepo;
	
	@GetMapping("/mascotas")
	public Iterable<Mascota> showAllMascotas(){
		return mascotaRepo.findAll();
	}
	
	@GetMapping("/mascotasId/{id}")
	public ResponseEntity<Mascota> showMascotaById(@PathVariable ("id") Long id) {
		
		Optional<Mascota> optMsc = mascotaRepo.findById(id);
		
		if(optMsc.isPresent()) {
			return new ResponseEntity<>(optMsc.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/mascotasName/{nombre}")
	public ResponseEntity <Mascota> showMascotaByName(@PathVariable ("nombre") String nombre) {
		
		Optional<Mascota> optMsc = mascotaRepo.findByNombre(nombre);
		
		if(optMsc.isPresent()) {
			return new ResponseEntity<>(optMsc.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/mascotas", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mascota addMascota(@RequestBody Mascota mascota) {
		return mascotaRepo.save(mascota);
	}
	
	@PutMapping("/updateMascota/{id}")
	public Mascota updateMascota(@RequestBody Mascota mascota) {
		return mascotaRepo.save(mascota);
	}
	
	@DeleteMapping("/mascotas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMascota (@PathVariable ("id") Long id) {
		mascotaRepo.deleteById(id);
	}
	
	@GetMapping("/mascotas/youngest")
	public Iterable<Mascota> youngestMascota(){
		PageRequest page = PageRequest.of(0, 20, Sort.by("fechaNac").descending());
		return mascotaRepo.findAll(page);
	}
	
	@GetMapping("/mascotasPag/{pag}")
	public Iterable<Mascota> pageableMascota(@PathVariable ("pag") int showingPage){
		PageRequest page = PageRequest.of(showingPage, 5, Sort.by("id").ascending());
		return mascotaRepo.findAll(page);
	}
	
	


}
