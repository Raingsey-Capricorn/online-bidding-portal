<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
        <script type="application/javascript">
            document.addEventListener("DOMContentLoaded", function (event) {
                document.getElementById('displays').value = ${size};
            });
            function selectAction(select) {
                self.location = 'items?page=' + ${page} +
                    '&size=' + select.value +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }
            function pagingAction(value) {
                self.location = 'items?page=' + value +
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
                <a href="${'/view/v1/bidding/items'}" class="navigation">Join Bidding</a>
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
            <div class="pagination">
                <label for="displays">Page:</label>
                <select id="displays" onchange="selectAction(this);">
                    <#foreach display in displays>
                        <option value=${display}>${display}</option>
                    </#foreach>
                </select>
                <#if hasPrev><button onclick="pagingAction(${prev})" id="previous"/></#if>
                <#if hasNext><button onclick="pagingAction(${next})" id="next"/></#if>
            </div>
            <#include "../footer.ftl"/>
        </div>
    </body>
</html>