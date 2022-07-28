package com.tenpo.desafio.app.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tenpo.desafio.app.secutiry.entity.Rol;
import com.tenpo.desafio.app.secutiry.entity.Usuario;
import com.tenpo.desafio.app.secutiry.enums.RolNombre;
import com.tenpo.desafio.app.secutiry.service.RolService;
import com.tenpo.desafio.app.secutiry.service.UsuarioService;

/**
 * TOOL: unicamente se usa para crear ROLES
 *
 */

@Component
public class InitData implements CommandLineRunner {

	@Autowired
	RolService rolService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (rolService.lista().isEmpty()) {
			Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
			Rol rolUser = new Rol(RolNombre.ROLE_USER);
			rolService.save(rolAdmin);
			rolService.save(rolUser);
		}

		if (usuarioService.lista().isEmpty()) {

			Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

			Usuario user = new Usuario();
			user.setNombre("user");
			user.setNombreUsuario("user");
			user.setEmail("user@tenpo.com.pe");
			user.setPassword(passwordEncoder.encode("user"));
			user.setRoles(roles);

			usuarioService.save(user);

			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

			Usuario admin = new Usuario();
			admin.setNombre("admin");
			admin.setNombreUsuario("admin");
			admin.setEmail("admin@tenpo.com.pe");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRoles(roles);

			usuarioService.save(admin);
		}

	}
}
