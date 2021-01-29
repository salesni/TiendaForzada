package com.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.entity.Usuario;
import com.usuarios.repository.UsuarioRepository;

@Service("ServiceFeign")
public class UserService implements IUserService {
	@Autowired
	private UsuarioRepository repo;

	@Override
	public Usuario save(Usuario usuario) {
		
		return repo.save(usuario);
	}

	@Override
	public Usuario updatePassword(Usuario usuario, String username) {
		Usuario newUser = repo.findByUsername(username);
		newUser.setPassword(usuario.getPassword());
		return repo.save(newUser);
	}

	@Override
	public Usuario updateRoles(Usuario usuario, String username) {
		Usuario newUser = repo.findByUsername(username);
		newUser.setRoles(usuario.getRoles());
		return repo.save(newUser);
	}
	
	@Override
	public Usuario updateInfo(Usuario usuario, String username) {
		Usuario newUser = repo.findByUsername(username);
		newUser.setNombre(usuario.getNombre());
		newUser.setApellido(usuario.getApellido());
		newUser.setEmail(usuario.getEmail());
		return repo.save(newUser);
	}
	
	
	@Override 
	public void deleteUser(String username) {
		Usuario deleteUser = repo.findByUsername(username);
		repo.delete(deleteUser);
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> users = (List<Usuario>) repo.findAll();
		for(Usuario user : users) {
			user.setPassword("");
		}
		return users;
	}

	@Override
	public Usuario update(Usuario usuario) {
		Usuario user = repo.save(usuario);
		user.setPassword("");
		return user;
	}
	
	
}