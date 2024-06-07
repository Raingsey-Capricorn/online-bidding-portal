<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
    </head>
    <body>
        <div class="form-login">
            <form class="form-container">
                <h1>Login Form</h1>
                <label class="form-label">User Name :
                    <input class="form-input" type="text" required aria-required="true"/>
                </label>
                <label class="form-label">Password :
                    <input class="form-input" type="password" required aria-required="true"/>
                </label>
                <input type="button" class="btn-submit" value="Login"/><br/><br/>
            </form>
            <div class="third-party-auth">
                <a href="/oauth2/authorization/github" class="oauth-link">
                    <img src="${'/img/icons8-github-48.png'}" alt="My Profile"/>
                    GitHub
                </a>|
                <a href="/oauth2/authorization/google" class="oauth-link">
                    <img src="${'/img/icons8-google-48.png'}" alt="My Profile"/>
                    Google
                </a>
            </div>
            <#include "footer.ftl"/>
        </div>
    </body>
</html>