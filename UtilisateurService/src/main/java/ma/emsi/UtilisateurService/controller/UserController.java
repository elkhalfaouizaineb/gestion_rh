package ma.emsi.UtilisateurService.controller;

import ma.emsi.UtilisateurService.model.User;
import ma.emsi.UtilisateurService.repository.UserRepository;
import ma.emsi.UtilisateurService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8888"}, allowedHeaders = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        boolean isValidUser = userService.isValidUser(username, password);
        if (isValidUser) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        userService.createUser(username, password);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
@GetMapping("/all")
    public List<User> getallusers(){
        return userRepository.findAll();
    }
}
