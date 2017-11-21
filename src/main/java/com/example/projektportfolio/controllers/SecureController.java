package com.example.projektportfolio.controllers;

import com.example.projektportfolio.PageInfoRepository;
import com.example.projektportfolio.ProjektportfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecureController {

//    @GetMapping("/")
//    public String mainPage(){
//        return "index";
//    }

    @Autowired
    ProjektportfolioRepository projektportfolioRepository;
    @Autowired
    PageInfoRepository pageInfoRepository;

    @RequestMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("projects", projektportfolioRepository.findAll());
        model.addAttribute("informations", pageInfoRepository.findAll());

        return "adminview";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}
