function hide(){
    document.getElementById("sign").style.display="none";
    document.getElementById("log").style.display="none";
}
function signup()
{
    var name,email,password,confirm,check,contact;
    name=document.getElementById("signup-name").value;
    email=document.getElementById("signup-email").value;
    password=document.getElementById("signup-password").value;
    confirm=document.getElementById("signup-confirm").value;
    check=document.getElementById("signup-check").checked;
    contact=document.getElementById("signup-contact").value;
    if(!name || !email || !password || !confirm){
        alert("Please fill all necessary fields");
    }
    else if(confirm!=password)
    {
    alert("Passwords are not same");
    }
    else if(password.length<8)
    {
        alert("Password must be minimum of 8 characters")
    }
    else if(check==false)
    {
        alert("Please accept terms and conditions");
    }
    else
    {
        if(!contact)
        {
            alert("Account created successfully");
        document.getElementById("signup-form").reset();
        }
        else
        {
            if(contact.length==10)
            {
                alert("Account created successfully");
                document.getElementById("signup-form").reset();
            }
            else
            {
                alert("Please enter 10 digit mobile number");
            }
        }
        
    }
}
function login(){
    var email,password;
    email=document.getElementById("login-email").value;
    password=document.getElementById("login-password").value;
    if(!email && !password){
        alert("Please enter email id and password")
    }
    else if(!email)
    {
        alert("Please enter email id")
    }
    else if(!password)
    {
        alert("Please enter password")
    }
    else{
        alert("login credentials will be checked at backend");
        document.getElementById("login-form").reset();
    }
}
function showlogin(){
    document.getElementById("log").style.display="block";
    document.getElementById("image1").style.display="none";
    document.getElementById("image2").style.display="none";
    document.getElementById("sign").style.display="none";
}
function showsignup(){
    document.getElementById("sign").style.display="block";
    document.getElementById("image1").style.display="none";
    document.getElementById("image2").style.display="none";
    document.getElementById("log").style.display="none";
}
