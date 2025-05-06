<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Information</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 40px;
                background-color: #f4f4f4;
            }
            .result-container {
                max-width: 400px;
                margin: auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h2 {
                color: #333;
            }
            p {
                font-size: 16px;
                margin: 10px 0;
            }
            a {
                display: inline-block;
                margin-top: 15px;
                text-decoration: none;
                color: #4CAF50;
                font-weight: bold;
            }
            a:hover {
                color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="result-container">
            <h2>User Information</h2>
            <p><strong>Name:</strong> ${name}</p>
            <p><strong>Age:</strong> ${age}</p>
        </div>
    </body>
</html>