<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
    </head>
    <body>
        <div class="content">
            <h1 class="h-one">Bidding Dashboard</h1>
            <#--
                switch resource by login user:
                - user -> /view/v1/user/2/items : My Bidding
                - admin -> /view/v1/admin/items : Manage Items
            -->
            <a class="navigation" href="${'/view/v1/user/2/items'}">My Bidding</a> |
            <a class="navigation" href="${'/view/v1/admin/items'}">Manage Items</a> |
            <a class="navigation" href="${'/view/v1/bidding/items'}">Bidding List</a> |
            <a class="navigation" href="${'/view/v1/bidding/announcement'}">Announcement</a>
            <#include "footer.ftl"/>
        </div>
    </body>
</html>