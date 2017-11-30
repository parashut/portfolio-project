package com.example.projektportfolio.controllers;

import com.example.projektportfolio.PageInfoRepository;
import com.example.projektportfolio.ProjektportfolioRepository;
import com.example.projektportfolio.models.PageInfo;
import com.example.projektportfolio.models.Projektportfolio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SecureController {

    @Autowired
    ProjektportfolioRepository projektportfolioRepository;
    @Autowired
    PageInfoRepository pageInfoRepository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("informations", pageInfoRepository.findAll());
        model.addAttribute("pageInfo", new PageInfo());
        model.addAttribute("projects", projektportfolioRepository.findAll());
        model.addAttribute("projektportfolio", new Projektportfolio());
        return "adminview";
    }

    @PostMapping("/admin/editinfo")
    public String editInfo(@ModelAttribute PageInfo pageInfo){
        pageInfo.setName(pageInfoRepository.findById(pageInfo.getId()).getName());
        pageInfoRepository.save(pageInfo);

        log.info("edit info: " + pageInfo.getName());
        return "redirect:/admin";
    }

    @PostMapping("/admin/editproject")
    public String editProject(@ModelAttribute Projektportfolio projektportfolio){
        projektportfolioRepository.save(projektportfolio);

        log.info("edit project id: " + projektportfolio.getId());
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteproject")
    public String deleteProject(@ModelAttribute Projektportfolio projektportfolio){
        log.warn("delete project id: " + projektportfolio.getId());
        projektportfolioRepository.delete(projektportfolio);

        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            log.info("logout");
        }
        return "redirect:/";
    }

//    You have to register an InitBinder in your controller to let spring convert your date string to java.util.Date object and set it in command object.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
