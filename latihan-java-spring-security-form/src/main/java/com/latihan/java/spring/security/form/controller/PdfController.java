package com.latihan.java.spring.security.form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class PdfController {

    @GetMapping("/mobile/pdf/{pdfId}")
    public String pdf(Model model, HttpServletRequest request, @PathVariable("pdfId") String pdfId) {
        model.addAttribute(pdfId);
        return "pdf/view";
    }

}
