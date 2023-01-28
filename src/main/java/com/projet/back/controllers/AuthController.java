package com.projet.back.controllers;


//import com.projet.back.security.config.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
public class AuthController {


    //se deconnecter
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }



}
