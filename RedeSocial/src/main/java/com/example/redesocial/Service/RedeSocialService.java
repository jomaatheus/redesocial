package com.example.redesocial.Service;

import com.example.redesocial.Repository.PostRepository;
import com.example.redesocial.Repository.UserRepository;
import com.example.redesocial.controller.DTO.CadastrarPostDTO;
import com.example.redesocial.controller.DTO.UsuarioFormDTO;
import com.example.redesocial.model.Post;
import com.example.redesocial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class RedeSocialService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public RedeSocialService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public User listarPeloId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User criarUser(UsuarioFormDTO usuarioDTO){
        User userValidar = userRepository.findByEmail(usuarioDTO.getEmail()).orElse( null);
        if (userValidar == null){
            User user = new User(usuarioDTO);
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    public User fazerLogin(String email, String password) {

        User user = userRepository.findByEmail(email).orElse( null);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }else {
            return null;
        }
    }

    public Post postar(CadastrarPostDTO cadastrarPostDTO){
    Post post = new Post();
    post.setAutor(cadastrarPostDTO.getAutor());
    post.setTexto(cadastrarPostDTO.getTexto());
    post.setData(new Date());
    return postRepository.save(post);
    }

    public List<Post> listar(){
        return postRepository.findAll();
    }

    public List<Post> listarOrder(String opcaoSelecionada, String ordemSelecionada){
        Sort sort = null;
        if(ordemSelecionada.equals("asc")){
            sort = Sort.by(Sort.Direction.ASC, opcaoSelecionada);
        }else{
            sort = Sort.by(Sort.Direction.DESC, opcaoSelecionada);
        }
        return postRepository.findAll(sort);
    }





}
