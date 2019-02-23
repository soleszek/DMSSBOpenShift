<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Data Management System</title>
    <link rel="stylesheet" href="style/style.css" type="text/css">
</head>
<body>
    <form action="/login" method="post">
        <div class="login-box">
            <h1>Login</h1>
            <div class="textbox">
                <i class="fas fa-users"></i>
                <input type="text" placeholder="Login" name="username" value="" required>
            </div>

            <div class="textbox">
                <i class="fas fa-lock"></i>
                <input type="password" placeholder="Password" name="password" value="" required>
            </div>

            <input class="btn" type="submit" name="" value="Sign in">

        </div>
    </form>
</body>

</html>