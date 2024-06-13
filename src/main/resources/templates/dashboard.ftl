<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="data:,">
        <title>Title</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
        <#--<script type="application/javascript">
            function postBidAction(e) {
                const formData = new FormData(document.getElementById("form-bidding"));
                const http = new XMLHttpRequest();
                http.open('GET', '${'/view/v1/dashboard'}', true);
                http.send(formData);
                http.onreadystatechange = function () {
                    if (http.readyState === 4 && http.status === 200) {
                        alert('Successfully Saved.');
                        self.location = 'items?page=' + ${page} +
                            '&size=' + ${size} +
                            '&sortField=maxBiddingPrice&sortDirection=DESC'
                        ;
                    }
                }
            }
        </script>-->
    </head>
    <body>
        <div class="content">
            <h1 class="h-one">Bidding Dashboard</h1>
            <#--
                switch resource by login user:
                - user -> /view/v1/user/2/items : My Bidding
                - admin -> /view/v1/admin/items : Manage Items
            -->
            <a class="navigation" href="${'/view/v1/bidding/items'}">My Bidding</a> |
            <a class="navigation" href="${'/view/v1/items'}">Manage Items</a> |
            <a class="navigation" href="${'/view/v1/bidding/items'}">Bidding List</a> |
            <a class="navigation" href="${'/view/v1/bidding/announcement'}">Announcement</a>
            <#include "footer.ftl"/>
        </div>
    </body>
</html>