package ma.emsi.UtilisateurService.service;

import ma.emsi.UtilisateurService.model.User;
import ma.emsi.UtilisateurService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean isValidUser(String username, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }

    public void createUser(String username, String password) {
        User newUser = User.builder()
                .username(username)
                .password(password)
                .build();
        userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
