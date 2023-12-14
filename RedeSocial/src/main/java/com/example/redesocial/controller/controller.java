package com.example.redesocial.controller;

import com.example.redesocial.Service.RedeSocialService;
import com.example.redesocial.controller.DTO.CadastrarPostDTO;
import com.example.redesocial.controller.DTO.UsuarioDTO;
import com.example.redesocial.controller.DTO.UsuarioFormDTO;
import com.example.redesocial.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;


@Controller
@AllArgsConstructor
public class controller {

    private final RedeSocialService redeSocialService;

    @GetMapping("/redeSocial")     //Acessar em localhost:8080/RedeSocial
    public String redeSocial() {

        return "redeSocial";
    }

    @GetMapping("/entrar")
    public String entrar(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        return "entrar";
    }

    @GetMapping("/cadastro")
    public String cadastro(@ModelAttribute("usuarioForm") UsuarioFormDTO usuarioDTO) {
        return "cadastro";
    }

    @GetMapping("/aplicacao")
    public String aplicacao(@RequestParam(name = "opcoes", required = false) String opcaoSelecionada,
                            @RequestParam(name = "orders", required = false) String ordemSelecionada,Model model) {
        List<Post> post = null;

        if(opcaoSelecionada != null && ordemSelecionada != null) {
            post = redeSocialService.listarOrder(opcaoSelecionada, ordemSelecionada);
        }else{
            post = redeSocialService.listar();
        }


        if(post == null){
            model.addAttribute("mensagem", "Erro ao listar postagems");
        }else if(post.isEmpty()){
            model.addAttribute("mensagem", "Não há postagens cadastradas");
        }
        List<String> opcoes = Arrays.asList("texto", "autor", "data");
        List<String> order = Arrays.asList("desc", "asc");
        model.addAttribute("post", post);
        model.addAttribute("orders",order);
        model.addAttribute("opcoes",opcoes);
        return "aplicacao";
    }

    @GetMapping("/postagem")
    public String postagem(@ModelAttribute("postagem") CadastrarPostDTO cadastrarPostDTO) {

        return "postagem";
    }


    @GetMapping("/inicio")
    public String inicio() {

        return "aplicacao";
    }



}