package com.samuexx.lojaodopc.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.samuexx.lojaodopc.model.Grupo;
import com.samuexx.lojaodopc.model.Usuario;
import com.samuexx.lojaodopc.repository.UsuarioRepository;
import com.samuexx.lojaodopc.util.cdi.CDIServiceLocator;

public class AppUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		UsuarioRepository usuarioRepository = CDIServiceLocator
				.getBean(UsuarioRepository.class);
		Usuario usuario = usuarioRepository.porEmail(email);

		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome()
					.toUpperCase()));
		}

		return authorities;
	}

}
