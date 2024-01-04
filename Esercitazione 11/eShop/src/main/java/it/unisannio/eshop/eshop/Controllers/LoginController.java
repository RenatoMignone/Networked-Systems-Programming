package it.unisannio.eshop.eshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
//@RequestMapping("eShop")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/?continue")
    public RedirectView redirectToAnotherUrl() {
        String redirectUrl = "http://localhost:8080/customer.html"; // Replace with your desired URL
        return new RedirectView(redirectUrl);
    }
    @PostMapping("/?continue")
    public RedirectView redirectToAnotherUrlP() {
        String redirectUrl = "http://localhost:8080/customer.html"; // Replace with your desired URL
        return new RedirectView(redirectUrl);
    }

//    @GetMapping("/login/{email}/{password}")
//    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return new ResponseEntity<>("User authenticated successfully", HttpStatus.OK);
//        } catch (AuthenticationException e) {
//            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//        }
//    }
}

