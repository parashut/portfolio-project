package com.example.projektportfolio.controllers;

import com.example.projektportfolio.PageInfoRepository;
import com.example.projektportfolio.ProjektportfolioRepository;
import com.example.projektportfolio.models.PageInfo;
import com.example.projektportfolio.models.Projektportfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecureController {

    @Autowired
    ProjektportfolioRepository projektportfolioRepository;
    @Autowired
    PageInfoRepository pageInfoRepository;

    @RequestMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("informations", pageInfoRepository.findAll());
        model.addAttribute("pageInfo", new PageInfo());
        model.addAttribute("projects", projektportfolioRepository.findAll());
        model.addAttribute("projektportfolio", new Projektportfolio());

        return "adminview";
    }

    @PostMapping("/admin")
    public String editInfo(@ModelAttribute PageInfo pageInfo){
        pageInfo.setName(pageInfoRepository.findById(pageInfo.getId()).getName());
        pageInfoRepository.save(pageInfo);

        return "redirect:/admin";
    }

    @PostMapping("/admin/editproject")
    public String editProject(@ModelAttribute Projektportfolio projektportfolio){
        projektportfolioRepository.save(projektportfolio);

        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteproject")
    public String deleteProject(@ModelAttribute Projektportfolio projektportfolio){
        projektportfolioRepository.delete(projektportfolio);

        return "redirect:/admin";
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
