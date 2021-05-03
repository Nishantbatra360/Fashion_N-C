<!DOCTYPE html>
<html>
    <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>
            Login
        </title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="LoginSignup.css">
		<script src="LoginSignup.js"></script>
    </head>
    <body onload="hide()">
    <jsp:include page="Header.jsp"></jsp:include>
        <div class="card-deck" style="border:hidden;">
            <div class="card mb-2 form-padding" style="border:hidden;background:none;" id="image1">
                <center>
                <img id="i1" src="../IMAGES/login.png"><br>
                <button id="b1" style="width: 100px;" type="button" class="btn btn-primary" onclick="showlogin()">User Login</button>
                </center>
                </div>
            <div class="card mb-2 form-padding" style="border:hidden;background:none;" id="image2">
                <center>
                <img id="i2" src="../IMAGES/signup.png"><br>
                <button id="b2" style="width:100px;" type="button" class="btn btn-secondary" onclick="showsignup()">User Sign-up</button>
                </center>
                </div>
        </div>
        <div class="card-deck">
            <div class="card mb-2 form-padding" id="log">
                <h4 class="card-title">Login</h4>
                <form id="login-form" action="Login">
                    <div class="form-group">
                        <label for="login-email">Email</label>
                        <input type="email" class="form-control" id="login-email" 
                        placeholder="Enter Email address">
                    </div>
                    <div class="form-group">
                        <label for="login-password">Password</label>
                        <input type="password" class="form-control" id="login-password" 
                        placeholder="Enter password">
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="login-check">
                        <label for="login-check" class="form-check-label">Remember me</label>
                    </div><br>
                    <span id="noaccount" onclick="showsignup()">Don't have an account?</span>
                <center><button type="submit" class="btn btn-primary">Login</button></center>
                </form>
            </div>
            <div id="sign" class="card mb-2 form-padding">
                <h4 class="card-title">Create Account</h4>
                <form id="signup-form">
                    <div class="form-group">
                        <label for="signup-name">Name <span class="star">*</span></label>
                        <input type="text" class="form-control" id="signup-name" 
                        placeholder="Enter Name">
                    </div>
                    <div class="form-group">
                        <label for="signup-email">Email <span class="star">*</span></label>
                        <input type="email" class="form-control" id="signup-email" 
                        placeholder="Enter Email address">
                    </div>
                    <div class="form-group">
                        <label for="signup-contact">Contact number</label>
                        <input type="number" class="form-control" id="signup-contact" 
                        placeholder="Enter Contact number">
                    </div>
                    <div class="form-group">
                        <label for="signup-password">Password <span class="star">*</span></label>
                        <input type="password" class="form-control" id="signup-password" 
                        placeholder="Enter password">
                    </div>
                    <div class="form-group">
                        <label for="signup-confirm">Confirm <span class="star">*</span></label>
                        <input type="password" class="form-control" id="signup-confirm" 
                        placeholder="Confirm password">
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="signup-check">
                        <label for="signup-check" class="form-check-label">
                            I accept terms and conditions</label>
                    </div><br>
                    <button type="button" class="btn btn-primary" onclick="signup()">Signup</button>
                    <button type="button" class="btn btn-secondary" onclick="reset()">Reset</button>
                    <span id="haveaccount" onclick="showlogin()">&nbsp&nbsp&nbsp&nbsp&nbspHave an account?</span>
                </form>
                <div class="field">Fields with * are necessary</div>
                <div class="field">Note: Password should be minimum of 8 characters</div>
            </div>
        </div>
        <div>
        </div>
    </body>
</html>