package com.deepelectrocodingbackend.ebackeend.services;

import com.deepelectrocodingbackend.ebackeend.entity.User;
import com.deepelectrocodingbackend.ebackeend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructeur
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Enregistrement de l'utilisateur
    public User registerUser(String nom, String prenom, String email, String password, MultipartFile profile) throws IOException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        String imagesDir = "images";
        File dir = new File(imagesDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filename = email.replace("@", "_") + "_" + profile.getOriginalFilename();
        String filePath = imagesDir + "/" + filename;
        File dest = new File(filePath);
        profile.transferTo(dest);

        User user = new User(null, nom, prenom, email, password, filePath);
        return userRepository.save(user);
    }

    // Connexion de l'utilisateur
    public User loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    // Récupérer le profil de l'utilisateur
    public User getUserProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
