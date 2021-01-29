package com.detalles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.entity.Detalles;
import com.commons.entity.Inventario;
import com.commons.entity.Usuario;
import com.detalles.client.InventarioClient;
import com.detalles.repository.DetallesRepository;

@Service("ServiceFeign")
public class DetallesService implements IDetallesService {
	
	@Autowired
	private DetallesRepository repo;
	
	@Autowired
	private InventarioClient clienteFeign;
	

	@Override
	public Detalles save(Detalles detalles) {
		Inventario invent = clienteFeign.findById(detalles.getInventario().getId()).get();
		if(invent.getExistencia()>= detalles.getCantidad()) {
			Detalles temp = repo.save(detalles);
			invent.setExistencia(invent.getExistencia()-detalles.getCantidad());
			Inventario inventNuevo = clienteFeign.updateDetalle(invent);
			System.out.println(inventNuevo.getExistencia());
			temp.getUsuario().setPassword("");
			return temp;
		}
		

		return null;
	}

	
	@Override
	public Detalles updateInventario(Detalles detalles) {
		Optional<Detalles> temp = repo.findById(detalles.getId());
		Integer oldCant = temp.get().getCantidad();
		Integer newCant = detalles.getCantidad();
		Inventario invent = clienteFeign.findById(temp.get().getInventario().getId()).get();
		Integer existencia = invent.getExistencia();
		
		if(existencia>=newCant) {
			if(newCant<oldCant) {
				existencia+= (oldCant-newCant);
			}else{
				existencia-=(newCant-oldCant);
			}
			invent.setExistencia(existencia);
			clienteFeign.updateDetalle(invent);
			
			temp.get().setCantidad(newCant);
			
			Detalles temp2 = repo.save(temp.get());
			temp2.getUsuario().setPassword("");
			return temp2;
			
		}
		
		

		return null;
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<Detalles> findById(long id){
		Optional<Detalles> temp = repo.findById(id);
		Usuario tempUser = temp.get().getUsuario();
		tempUser.setPassword("");
		temp.get().setUsuario(tempUser);
		return temp;
	}

}
