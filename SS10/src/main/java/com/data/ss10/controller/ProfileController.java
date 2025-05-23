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
import java.util.UUID;

@Controller
@RequestMapping("B4")
public class ProfileController {

    // Directory to store uploaded avatars
    private static final String UPLOAD_DIR = System.getProperty("java.io.tmpdir") + "/avatars/";

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "B4/uploadForm";
    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (!file.isEmpty()) {
            Path uploadDirPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDirPath)) {
                Files.createDirectories(uploadDirPath);
            }

            // Generate unique file name to avoid conflicts
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            Path uploadPath = Paths.get(UPLOAD_DIR + uniqueFileName);

            // Save the file
            Files.write(uploadPath, file.getBytes());
            model.addAttribute("message", "Upload thành công: " + uniqueFileName);
        } else {
            model.addAttribute("message", "Vui lòng chọn file.");
        }
        return "B4/uploadForm";
    }
}