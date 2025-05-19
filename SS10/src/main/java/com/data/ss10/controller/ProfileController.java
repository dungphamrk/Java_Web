package com.data.ss10.controller;


import com.data.ss10.model.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("B4")
public class ProfileController {

    // Directory to store uploaded avatars
    private static final String UPLOAD_DIR = "src/main/resources/";

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "B4/uploadForm";
    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (!file.isEmpty()) {
            String uploadPath = UPLOAD_DIR + file.getOriginalFilename();
            Files.write(Paths.get(uploadPath), file.getBytes());
            model.addAttribute("message", "Upload thành công: " + file.getOriginalFilename());
        } else {
            model.addAttribute("message", "Vui lòng chọn file.");
        }
        return "B4/uploadForm";
    }
}
