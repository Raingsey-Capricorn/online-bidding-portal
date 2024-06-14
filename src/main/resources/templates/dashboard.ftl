<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="data:,">
        <title>Title</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
    </head>
    <body>
        <div class="content">
            <a class="navigation" onclick="signOutAction(this);"
               style="font-size: 15px;" href="${'/api/v1/auth/sign-out'}">Sign-Out</a>
            <h1 class="h-one">Bidding Dashboard</h1>
            <#if !isAdmin>
                <a class="navigation" href="${'/view/v1/bidding/items'}">My Bidding</a> |
                <a class="navigation" href="${'/view/v1/bidding/items'}">Bidding List</a> |
            </#if>
            <#if isAdmin>
                <a class="navigation" href="${'/view/v1/items'}">Manage Items</a> |
            </#if>
            <a class="navigation" href="${'/view/v1/bidding/announcement'}">Announcement</a>
            <#include "footer.ftl"/>
        </div>
    </body>
</html>