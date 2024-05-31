package com.mascotas.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mascotas.model.Mascota;

@Repository
public interface MascotaRepository extends PagingAndSortingRepository<Mascota, Long> {
	
	Optional<Mascota> findByNombre(String nombre);

}
