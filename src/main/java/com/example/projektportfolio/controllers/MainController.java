package com.example.projektportfolio.controllers;

import com.example.projektportfolio.ContactService;
import com.example.projektportfolio.ProjektportfolioRepository;
import com.example.projektportfolio.models.forms.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class MainController {
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    ContactService contactService;

    @Autowired
    ProjektportfolioRepository projektportfolioRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("emailClass", new ContactForm());
        model.addAttribute("success", false);

        model.addAttribute("projects", projektportfolioRepository.findAll());

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("emailClass") ContactForm contact, Model model) {
        Context context = new Context();
        context.setVariable("name", "Name: " + contact.getName());
        context.setVariable("phonenumber", "Phone number: " + contact.getPhonenumber());
        context.setVariable("message", contact.getMessage());

        String bodyHTML = templateEngine.process("emailTemplate", context);

        contactService.sendEmail(bodyHTML, contact.getEmail());

        model.addAttribute("success", true);
        model.addAttribute("emailClass", new ContactForm()); //wyczyści formularz z pamięci

        System.out.println("mail wyslany; adres zwrotny: " + contact.getEmail());
        return "index";
    }

    /*@RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@RequestBody ContactForm contact, Model model) {
        Context context = new Context();
        context.setVariable("name", "Name: " + contact.getName());
        context.setVariable("phonenumber", "Phone number: " + contact.getPhonenumber());
        context.setVariable("message", contact.getMessage());

        String bodyHTML = templateEngine.process("emailTemplate", context);

        contactService.sendEmail(bodyHTML, contact.getEmail());

        model.addAttribute("success", true);
        model.addAttribute("emailClass", new ContactForm());
        System.out.println("mail wyslany; adres zwrotny: " + contact.getEmail());
        return "index";
    }*/
}