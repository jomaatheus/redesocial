package com.example.redesocial.controller;


import com.example.redesocial.Service.RedeSocialService;
import com.example.redesocial.controller.DTO.CadastrarPostDTO;
import com.example.redesocial.controller.DTO.UsuarioDTO;
import com.example.redesocial.controller.DTO.UsuarioFormDTO;
import com.example.redesocial.model.Post;
import com.example.redesocial.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class RedeSocialController {

    private final RedeSocialService redeSocialService;

    @PostMapping("/criar-conta")
    public String criarConta(@ModelAttribute("usuarioForm") UsuarioFormDTO usuarioDTO, Model model) {
        User user = redeSocialService.criarUser(usuarioDTO);
        if(user == null) {
            model.addAttribute("mensagem", "Erro ao cadastrar conta. Tente novamente.");
            return "cadastro";
        }else{
            model.addAttribute("mensagem", "Conta criada com sucesso!");
            return "redeSocial";
        }
    }

    @PostMapping("/fazer-login")
    public String fazerLogin(@ModelAttribute("usuario") UsuarioDTO usuarioDTO, Model model) {
        User usuarioLogado = redeSocialService.fazerLogin(usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if (usuarioLogado != null) { // Verifica se o usuário foi autenticado
            model.addAttribute("mensagem", "Login bem-sucedido!");
            model.addAttribute("usuarioLogado", usuarioLogado);
            return "redirect:/aplicacao";
        } else { // Caso o usuário não seja autenticado, redireciona de volta para a página de login
            model.addAttribute("mensagem", "Usuário ou senha incorretos. Tente novamente.");
            return "entrar";
        }

    }

    @PostMapping("/fazer-postagem")
    public String fazerPostagem(@ModelAttribute("postagem") CadastrarPostDTO cadastrarPostDTO, Model model) {
        Post post = redeSocialService.postar(cadastrarPostDTO);

        if(post == null){
            model.addAttribute("mensagem", "Erro ao cadastrar uma postagem");
            return "redirect:/fazer-postagem";
        }else{
            return "redirect:/aplicacao";
        }
    }
}
