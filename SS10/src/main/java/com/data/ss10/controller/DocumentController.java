package com.data.ss10.controller;

import com.data.ss10.model.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("B5")
public class DocumentController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @GetMapping("/document/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("document", new Document());
        return "B5/documentForm";
    }

    @PostMapping("/document/uploadDocument")
    public String uploadDocument(@ModelAttribute("document") Document document,
                                 BindingResult result,
                                 Model model) {
        MultipartFile file = document.getFile();

        // Kiểm tra lỗi đầu vào
        if (file == null || file.isEmpty()) {
            model.addAttribute("message", "Vui lòng chọn một tệp.");
            return "B5/documentForm";
        }

        try {
            // Tạo thư mục uploads nếu chưa tồn tại
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu tệp
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes());

            // Thêm thông tin vào model để hiển thị
            model.addAttribute("message", "Tải lên thành công: " + fileName);
            model.addAttribute("title", document.getTitle());
            model.addAttribute("description", document.getDescription());
            model.addAttribute("filePath", "/uploads/" + fileName);

        } catch (IOException e) {
            model.addAttribute("message", "Lỗi khi tải lên tệp: " + e.getMessage());
        }

        return "B5/documentForm";
    }
}