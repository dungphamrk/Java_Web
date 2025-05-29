package com.data.ss14.controller;

import com.data.ss14.model.B9.Transaction;
import com.data.ss14.service.B9.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/finance")
public class B9Controller {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String showForm(Model model, HttpSession session, HttpServletRequest request, Locale locale) {
        model.addAttribute("transaction", new Transaction());

        List<Transaction> list = transactionService.getAllTransactions();
        model.addAttribute("transactionList", list);

        String username = (String) session.getAttribute("username");
        if (username == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("username".equals(c.getName())) {
                        username = c.getValue();
                        session.setAttribute("username", username);
                        break;
                    }
                }
            }
        }
        model.addAttribute("username", username);

        // Thông báo hướng dẫn theo ngôn ngữ
        String instruction = messageSource.getMessage("instruction.text", null, locale);
        model.addAttribute("instruction", instruction);

        return "/B9/financeForm";
    }

    @PostMapping("/add")
    public String addTransaction(@ModelAttribute Transaction transaction,
                                 HttpSession session,
                                 HttpServletResponse response,
                                 @RequestParam String username) {
        // Lưu username vào session và cookie
        session.setAttribute("username", username);

        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 1 tuần
        response.addCookie(cookie);

        // Lưu giao dịch
        transactionService.addTransaction(transaction);

        return "redirect:/finance";
    }

    // Xóa giao dịch theo index
    @GetMapping("/delete/{index}")
    public String deleteTransaction(@PathVariable int index) {
        transactionService.deleteTransaction(index);
        return "redirect:/finance";
    }
}

