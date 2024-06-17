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
            <div>
                <p>You are logged as ${role}</p>
                <a class="navigation" style="font-size: 13px;" href="${'/api/v1/auth/sign-out'}">Sign-Out</a>
            </div>
            <h1 class="h-one">Bidding Dashboard</h1>
            <#if !isAdmin>
                <a class="navigation" href="${'/view/v1/bidding/history'}">My Bidding</a> |
                <a class="navigation" href="${'/view/v1/bidding/board'}">Bidding List</a> |
            </#if>
            <#if isAdmin>
                <a class="navigation" href="${'/view/v1/items'}">Manage Items</a> |
            </#if>
            <a class="navigation" href="${'/view/v1/announcement'}">Announcement</a>
            <#include "footer.ftl"/>
        </div>
    </body>
</html>