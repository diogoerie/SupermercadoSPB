package com.spb.supermercado.web_service.controller;

import com.spb.supermercado.web_service.model.User;
import com.spb.supermercado.web_service.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mostrarLogin(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam("nome") String nome,
                                 @RequestParam("senha") String senha,
                                 Model model,
                                 HttpSession session) {
        try {
            User usuarioAutenticado = userService.autenticarUsuario(nome, senha);

            session.setAttribute("usuarioLogado", usuarioAutenticado);

            return "redirect:/produtos";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new User());
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
