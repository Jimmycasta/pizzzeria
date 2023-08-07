package com.jimmycasta.pizzeria.services;

import com.jimmycasta.pizzeria.entities.UserEntity;
import com.jimmycasta.pizzeria.entities.UserRoleEntity;
import com.jimmycasta.pizzeria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Se busca el usuario en la bd y se carga en userEntity si no está lanza una excepción.
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario " + username));

        //Trae los roles de la BD los pasa String y los convierte a un Array.
        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

        //Si el usuario esta, se construye el UserDetails (usuario) y se retorna.
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles)
                //.authorities(this.grantedAuthority(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }

    //****************configuración de authorities ********************

    //Método para asignar los authorities específicos.
    private String[] getAuthorities(String role) {

        // si el usuario que viene en role tiene el rol de "ADMIN" ó "CUSTOMER", le crear un authorities que se llama "random_order"
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[]{"random_order"};
        }
        //si no es ninguno se retorna un arreglo vacío;
        return new String[]{};
    }

    private List<GrantedAuthority> grantedAuthority(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            for (String authority : this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
    //**************** Fin configuración de authorities ********************
}
