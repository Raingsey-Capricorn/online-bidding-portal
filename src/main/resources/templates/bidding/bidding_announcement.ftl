<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="data:,">
        <title>Bidding Page</title>
        <link rel="stylesheet" href="${'/css/common.css'}"/>
        <script type="application/javascript">
            document.addEventListener("DOMContentLoaded", function (event) {
                document.getElementById('displays').value = ${size};
            });

            function selectAction(select) {
                self.location = 'announcement?page=' + ${page} +
                    '&size=' + select.value +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }

            function pagingAction(value) {
                self.location = 'announcement?page=' + value +
                    '&size=' + ${size} +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }
        </script>
    </head>
    <body>
        <div class="content">
            <a href="${'/view/v1/dashboard'}" class="navigation">Dashboard</a><br/><br/>
            <table class="item_list">
                <tr>
                    <th>Id</th>
                    <th>Item Name</th>
                    <th>Winner</th>
                    <th>Original Price</th>
                    <th>Min Bidding Price</th>
                    <th>Max Bidding Price</th>
                    <th>Description</th>
                    <th>Total Bidders</th>
                    <th>Bidding Date</th>
                </tr>
                <#list items as item>
                    <tr class="tableBody">
                        <td>${item.id()}</td>
                        <td>${item.item().name()}</td>
                        <td>${item.winner().userName()}</td>
                        <td>${item.originalPrice()}</td>
                        <td>${item.minBiddingPrice()}</td>
                        <td>${item.maxBiddingPrice()}</td>
                        <td>${item.description()!""}</td>
                        <td>${item.bidderAttend()}</td>
                        <td>${item.biddingDate()?string('dd.MM.yyyy HH:mm:ss')}</td>
                    </tr>
                </#list>
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
        <#include "bidding_page_bid.ftl"/>
    </body>
</html>