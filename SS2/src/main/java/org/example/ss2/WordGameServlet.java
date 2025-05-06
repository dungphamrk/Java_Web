package org.example.ss2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "WordGameServlet", value = "/WordGameServlet")
public class WordGameServlet extends HttpServlet {
    private static final String[] WORDS = {"HOA", "HONG", "XINH", "DEP", "VUI"};
    public static final int MAX_WRONG_GUESSES = 5;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Start a new game if not already started
        if (session.getAttribute("secretWord") == null || "true".equals(request.getParameter("restart"))) {
            Random random = new Random();
            String secretWord = WORDS[random.nextInt(WORDS.length)];
            session.setAttribute("secretWord", secretWord);
            session.setAttribute("wrongGuesses", 0);
            session.setAttribute("gameOver", false);
            session.setAttribute("win", false);
            session.setAttribute("guessedLetters", new HashSet<Character>());
        }

        // Forward to game.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("B9/game.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String secretWord = (String) session.getAttribute("secretWord");
        Integer wrongGuesses = (Integer) session.getAttribute("wrongGuesses");
        Boolean gameOver = (Boolean) session.getAttribute("gameOver");
        Boolean win = (Boolean) session.getAttribute("win");
        @SuppressWarnings("unchecked")
        HashSet<Character> guessedLetters = (HashSet<Character>) session.getAttribute("guessedLetters");

        if (secretWord == null || gameOver == null || wrongGuesses == null || win == null || guessedLetters == null) {
            response.sendRedirect("WordGameServlet");
            return;
        }

        if (!gameOver) {
            String guess = request.getParameter("guess");
            if (guess != null) {
                guess = guess.trim().toUpperCase();
                if (guess.length() == secretWord.length()) {
                    if (guess.equals(secretWord)) {
                        session.setAttribute("win", true);
                        session.setAttribute("gameOver", true);
                    } else {
                        wrongGuesses++;
                        if (wrongGuesses >= MAX_WRONG_GUESSES) {
                            session.setAttribute("gameOver", true);
                        }
                        session.setAttribute("wrongGuesses", wrongGuesses);
                    }
                } else {
                    request.setAttribute("error", "Từ đoán phải có " + secretWord.length() + " chữ cái!");
                }

                // Update guessed letters for display
                for (char c : guess.toCharArray()) {
                    guessedLetters.add(c);
                }
                session.setAttribute("guessedLetters", guessedLetters);
            }
        }

        // Forward to game.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("B9/game.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}