package com.spb.supermercado.web_service.controller;

import com.spb.supermercado.web_service.model.User;
import com.spb.supermercado.web_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("user", new User());
        return "registro";
    }

    @PostMapping("/registro")
    public String processarRegistro(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registrarUsuario(user);

            redirectAttributes.addFlashAttribute("mensagemSucesso", "Registro criado com sucesso!");
            return "redirect:/";
        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/registro";
        }
    }

}