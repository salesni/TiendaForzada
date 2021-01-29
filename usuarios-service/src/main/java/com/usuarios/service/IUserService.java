package com.usuarios.service;

import java.util.List;

import com.commons.entity.Usuario;

public interface IUserService {
	public Usuario save(Usuario usuario);
	public Usuario updatePassword(Usuario usuario, String username);
	public Usuario updateRoles(Usuario usuario, String username);
	public Usuario updateInfo(Usuario usuario, String username);
	public List<Usuario> findAll();
	public Usuario update(Usuario usuario);
	public void deleteUser(String username);
}