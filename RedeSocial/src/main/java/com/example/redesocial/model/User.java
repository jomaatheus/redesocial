package com.example.redesocial.model;

import com.example.redesocial.controller.DTO.UsuarioFormDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String nome;
    private String email;



    @ManyToMany
    @JoinTable(name = "amigos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "amigo_id"))
    private Set<User> amigos = new HashSet<>();

    public User(UsuarioFormDTO form){
        username = form.getUsername();
        password = form.getPassword();
        nome = form.getNome();
        email = form.getEmail();
    }

}
