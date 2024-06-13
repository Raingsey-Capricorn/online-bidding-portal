<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link rel="icon" href="data:,">
        <link rel="stylesheet" href="${'/css/common.css'}"/>
        <script type="application/javascript">
            function loginAction(e) {
                const formData = new FormData(document.getElementById("form-login"));
                const http = new XMLHttpRequest();
                http.open('POST', '${'/api/v1/auth/sign-in'}', true);
                http.send(formData);
                http.onreadystatechange = function () {
                    if (http.readyState === 4 && http.status === 200) {
                        self.location = '/view/v1/dashboard';
                    }
                    if (http.status === 400) {
                        alert("User is not existing.");
                    }
                }
            }
        </script>
    </head>
    <body>
        <div class="form-login">
            <form class="form-container" id="form-login">
                <h2>Login Form</h2>
                <label class="form-label">Email :
                    <input class="form-input" name="email" type="email" required aria-required="true"/>
                </label>
                <label class="form-label">Password :
                    <input class="form-input" name="password" type="password" required aria-required="true"/>
                </label>
                <nobr>
                    <input type="button" class="btn-submit" value="Login" onclick="loginAction(this);"/>
                    <span>|</span>
                    <input type="button" class="btn-submit" value="Register"/>
                </nobr>
            </form>
            <div class="third-party-auth">
                <div class="line-separator"></div>
                <label>Or<br/>Register with</label><br/>
                <a href="http://localhost:8080/oauth2/authorization/github" class="oauth-link">
                    <img src="${'/img/icons8-github-48.png'}" alt="Github Logo"/>
                    GitHub
                </a>|
                <a href="http://localhost:8080/oauth2/authorization/google" class="oauth-link">
                    <img src="${'/img/icons8-google-48.png'}" alt="Google Logo"/>
                    Google
                </a>
            </div>
            <br/>
            <#include "footer.ftl"/>
        </div>
    </body>
</html>