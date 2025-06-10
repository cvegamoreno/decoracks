/* package com.decoracks.app.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttribute {

    @ModelAttribute
    public void addAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI(); // Ejemplo: "/productos"
        model.addAttribute("activeUrl", uri);
    }
} */