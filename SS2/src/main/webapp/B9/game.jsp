<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashSet" %>
<%@ page import="static org.example.ss2.WordGameServlet.MAX_WRONG_GUESSES" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Game Đoán Chữ</title>
        <style>
            .game-container {
                max-width: 600px;
                margin: 20px auto;
                text-align: center;
            }
            .word-display {
                font-size: 24px;
                letter-spacing: 5px;
                margin: 20px 0;
            }
            .error {
                color: red;
            }
            .success {
                color: green;
            }
        </style>
    </head>
    <body>
        <div class="game-container">
            <h2>Game Đoán Chữ</h2>

            <%
                String secretWord = (String) session.getAttribute("secretWord");
                Integer wrongGuesses = (Integer) session.getAttribute("wrongGuesses");
                Boolean gameOver = (Boolean) session.getAttribute("gameOver");
                Boolean win = (Boolean) session.getAttribute("win");
                @SuppressWarnings("unchecked")
                HashSet<Character> guessedLetters = (HashSet<Character>) session.getAttribute("guessedLetters");

                if (secretWord == null || wrongGuesses == null || gameOver == null || win == null || guessedLetters == null) {
                    response.sendRedirect("WordGameServlet");
                    return;
                }

                // Display the word with underscores for unguessed letters
                StringBuilder displayWord = new StringBuilder();
                boolean allGuessed = true;
                for (char c : secretWord.toCharArray()) {
                    if (guessedLetters.contains(c)) {
                        displayWord.append(c).append(" ");
                    } else {
                        displayWord.append("_ ");
                        allGuessed = false;
                    }
                }
                if (allGuessed && !gameOver) {
                    session.setAttribute("win", true);
                    session.setAttribute("gameOver", true);
                    gameOver = true;
                    win = true;
                }
            %>

            <p>Từ bạn đang phải đoán gồm <%= secretWord.length() %> chữ cái: <%= displayWord.toString() %></p>
            <p>Bạn còn <%= MAX_WRONG_GUESSES - wrongGuesses %> lần đoán sai!</p>

            <!-- Display game status -->
            <%
                if (gameOver) {
                    if (win) {
            %>
            <p class="success">Bạn đã đoán đúng! Từ bí mật là: <%= secretWord %></p>
            <%
            } else {
            %>
            <p class="error">Bạn đã thua! Từ bí mật là: <%= secretWord %></p>
            <%
                }
            %>
            <form action="WordGameServlet" method="get">
                <input type="hidden" name="restart" value="true">
                <button type="submit">Chơi lại</button>
            </form>
            <%
            } else {
            %>
            <!-- Guess Form -->
            <form action="WordGameServlet" method="post">
                <label for="guess">Nhập từ đoán:</label>
                <input type="text" id="guess" name="guess" required>
                <button type="submit">Đoán</button>
            </form>
            <%
                }
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <p class="error"><%= error %></p>
            <%
                }
            %>
        </div>
    </body>
</html>