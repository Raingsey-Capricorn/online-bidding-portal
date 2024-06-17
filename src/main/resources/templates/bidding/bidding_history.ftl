<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="data:,">
        <title>${title}</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
        <script type="application/javascript">
            document.addEventListener("DOMContentLoaded", function (event) {
                document.getElementById('displays').value = ${size};
            });
            function selectAction(select) {
                self.location = 'history?page=' + ${page} +
                    '&size=' + select.value +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }
            function pagingAction(value) {
                self.location = 'history?page=' + value +
                    '&size=' + ${size} +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }
        </script>
    </head>
    <body>
        <#-- item listing -->
        <div class="content">
            <nobr>
                <a href="${'/view/v1/dashboard'}" class="navigation">Dashboard</a> |
                <a href="${'/view/v1/bidding/board'}" class="navigation">Join Bidding</a>
            </nobr>
            <br/><br/>
            <table class="item_list">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Win</th>
                    <th>Item's Name</th>
                    <th>Description</th>
                    <th>Still Availability?</th>
                    <th>Price</th>
                    <th>LastBiddingPrice</th>
                    <th>MaxBiddingPrice</th>
                    <th>BiddingDate</th>
                </tr>
                <#foreach biddingItem in items>
                    <tr class="tableBody">
                        <td>${biddingItem.item().id()}</td>
                        <td>${biddingItem.bidder().firstName() + biddingItem.bidder().lastName()}</td>
                        <td>${biddingItem.bidder().type().name()}</td>
                        <td>${biddingItem.isWon()?c}</td>
                        <td>${biddingItem.item().name()}</td>
                        <td>${biddingItem.item().description()}</td>
                        <td>${biddingItem.item().availability()?c}</td>
                        <td>${biddingItem.item().price()}</td>
                        <td>${biddingItem.lastBiddingPrice()}</td>
                        <td>${biddingItem.maxBiddingPrice()}</td>
                        <td>${biddingItem.biddingDate()?string('dd.MM.yyyy HH:mm:ss')}</td>
                    </tr>
                </#foreach>
            </table>
            <#include "../pagination.ftl"/>
            <#include "../footer.ftl"/>
        </div>
    </body>
</html>