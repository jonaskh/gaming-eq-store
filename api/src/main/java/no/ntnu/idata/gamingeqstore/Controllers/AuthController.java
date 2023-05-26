package no.ntnu.idata.gamingeqstore.Controllers;

import no.ntnu.idata.gamingeqstore.Entities.Role;
import no.ntnu.idata.gamingeqstore.Entities.User;
import no.ntnu.idata.gamingeqstore.Security.AuthenticationRequest;
import no.ntnu.idata.gamingeqstore.Services.RoleService;
import no.ntnu.idata.gamingeqstore.Services.UserService;
import no.ntnu.idata.gamingeqstore.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://group09.web-tek.ninja")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String jwt = jwtUtil.generateToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            logger.severe("Error occurred during authentication: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            Role defaultRole = roleService.findByName("USER");
            user.addRole(defaultRole);
            User newUser = userService.saveUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            logger.severe("Error occurred during user registration: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed. Please try again.");
        }
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        try {
            Role adminRole = roleService.findByName("ADMIN");
            user.addRole(adminRole);
            User newUser = userService.saveUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            logger.severe("Error occurred during user registration: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Admin registration failed. Please try again.");
        }
    }

    public static class JwtResponse {
        private final String jwt;

        public JwtResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }
}
