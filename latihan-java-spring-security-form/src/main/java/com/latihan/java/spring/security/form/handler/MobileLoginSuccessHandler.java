package com.latihan.java.spring.security.form.handler;

import com.latihan.java.spring.security.form.model.UserPrincipal;
import com.latihan.java.spring.security.form.session.PdfSessionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MobileLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + this.getUrl(authentication, request));
    }

    private String getUrl(Authentication authentication, HttpServletRequest request) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String username = principal.getUser().getUsername();
        switch (username) {
            case "test":
                String pdfId = (String) request.getSession().getAttribute(PdfSessionEnum.PDF_SESSION.toString());
                return "/mobile/pdf/" + pdfId;
            default:
                return "/mobile";
        }

    }
}
