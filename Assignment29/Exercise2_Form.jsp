<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exercise 2 - Student Registration Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f8f9fa; }
        .container { max-width: 550px; margin: auto; padding: 25px; border: 2px solid #333; border-radius: 8px; background-color: white; }
        h1 { color: #1a5276; text-align: center; }
        label { font-weight: bold; display: block; margin-top: 12px; }
        input[type="text"], input[type="email"], input[type="number"], select, textarea {
            width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;
        }
        input[type="radio"], input[type="checkbox"] { margin-right: 5px; }
        .radio-group, .checkbox-group { margin-top: 5px; }
        .radio-group label, .checkbox-group label { display: inline; font-weight: normal; margin-right: 15px; }
        input[type="submit"], input[type="reset"] {
            background-color: #2e86c1; color: white; padding: 10px 20px; border: none;
            border-radius: 4px; cursor: pointer; margin-top: 15px; margin-right: 10px;
        }
        input[type="reset"] { background-color: #7f8c8d; }
        input[type="submit"]:hover { background-color: #1a5276; }
        input[type="reset"]:hover { background-color: #566573; }
        .footer { text-align: center; margin-top: 20px; color: #7f8c8d; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Student Registration Form</h1>
        <p style="text-align:center; color:#555;">Fill in the details below to register.</p>
        <form action="Exercise2_Result.jsp" method="post">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter your name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" placeholder="Enter phone number">

            <label for="course">Course:</label>
            <select id="course" name="course">
                <option value="Computer Science">Computer Science</option>
                <option value="Mathematics">Mathematics</option>
                <option value="Physics">Physics</option>
                <option value="Electronics">Electronics</option>
                <option value="Mechanical Engineering">Mechanical Engineering</option>
            </select>

            <label>Gender:</label>
            <div class="radio-group">
                <input type="radio" id="male" name="gender" value="Male">
                <label for="male">Male</label>
                <input type="radio" id="female" name="gender" value="Female">
                <label for="female">Female</label>
                <input type="radio" id="other" name="gender" value="Other">
                <label for="other">Other</label>
            </div>

            <label>Hobbies:</label>
            <div class="checkbox-group">
                <input type="checkbox" id="reading" name="hobbies" value="Reading">
                <label for="reading">Reading</label>
                <input type="checkbox" id="sports" name="hobbies" value="Sports">
                <label for="sports">Sports</label>
                <input type="checkbox" id="music" name="hobbies" value="Music">
                <label for="music">Music</label>
                <input type="checkbox" id="coding" name="hobbies" value="Coding">
                <label for="coding">Coding</label>
            </div>

            <label for="address">Address:</label>
            <textarea id="address" name="address" rows="3" placeholder="Enter your address"></textarea>

            <div style="text-align:center;">
                <input type="submit" value="Register">
                <input type="reset" value="Clear">
            </div>
        </form>

        <div class="footer">
            &copy; 2025 JSP Registration Form - Exercise 2
        </div>
    </div>
</body>
</html>
