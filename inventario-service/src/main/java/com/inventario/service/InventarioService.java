package com.inventario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.entity.Inventario;
import com.inventario.repository.InventarioRepository;
@Service("ServiceFeign")
public class InventarioService implements IInventarioService {
	
	@Autowired
	private InventarioRepository repo;
	
	@Override
	public Inventario save(Inventario Inventario) {
		return repo.save(Inventario);
	}

	@Override
	public Inventario updateInventario(Inventario Inventario) {
		Optional<Inventario> temp = repo.findById(Inventario.getId());
		temp.get().setExistencia(Inventario.getExistencia());
		return repo.save(temp.get());
	}

	
	@Override
	public void delete(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<Inventario> findById(long id) {
		return repo.findById(id);
	}

}
