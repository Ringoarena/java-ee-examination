package se.sysdev.javaeeexamination.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import se.sysdev.javaeeexamination.security.jwt.AuthenticationRequest;
import se.sysdev.javaeeexamination.security.jwt.AuthenticationResponse;
import se.sysdev.javaeeexamination.security.jwt.JwtUtil;
import se.sysdev.javaeeexamination.service.OrderService;
import se.sysdev.javaeeexamination.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderResource {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/private")
    public String getPrivateData() {
        return "Private";
    }

}
