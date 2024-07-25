package com.latihan.java.spring.security.form.filter;

import com.latihan.java.spring.security.form.session.PdfSessionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class MobileLoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String pdfId = request.getParameter("pdfId");
        HttpSession session = request.getSession();
        if (pdfId != null) {
            session.setAttribute(PdfSessionEnum.PDF_SESSION.toString(), pdfId);
        }
        filterChain.doFilter(request, response);
    }
}
