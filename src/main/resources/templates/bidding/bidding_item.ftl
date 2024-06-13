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

            function toggleBidPopupAction() {
                const overlay = document.getElementById('bidding-popup-overlay');
                overlay.classList.toggle('show');
            }

            function tableAction(row) {
                const overlay = document.getElementById('bidding-popup-overlay');
                overlay.classList.toggle('show');
                document.getElementById('bidding_item_id').value = row.children[0].innerText;
                document.getElementById('bidding_item_name').value = row.children[1].innerText;
                document.getElementById('bidding_item_desc').value = row.children[2].innerText;
                document.getElementById('bidding_item_price').value = row.children[3].innerText;
            }

            function postBidAction(e) {
                const formData = new FormData(document.getElementById("form-bidding"));
                const http = new XMLHttpRequest();
                http.open('POST', '${'/api/v1/bidding/bid'}', true);
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
        </script>
    </head>
    <body>
        <div class="content">
            <a href="${'/view/v1/dashboard'}" class="navigation">Dashboard</a><br/><br/>
            <table class="item_list">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Current Price</th>
                    <th>EntryDate</th>
                    <th>ExpiryDate</th>
                </tr>
                <#list items as item>
                    <tr class="tableBody" onclick="tableAction(this);">
                        <td class="has-details">${item.id()}<span class="details">Click to bid</span></td>
                        <td class="has-details">${item.name()}<span class="details">Click to bid</span> </td>
                        <td class="has-details">${item.description()}<span class="details">Click to bid</span></td>
                        <td class="has-details">${item.maxBiddingPrice()}<span class="details">Click to bid</span></td>
                        <td class="has-details">${item.entryDate()?string('dd.MM.yyyy HH:mm:ss')}<span class="details">Click to bid</span></td>
                        <td class="has-details">${item.expiryDate()?string('dd.MM.yyyy HH:mm:ss')}<span class="details">Click to bid</span></td>
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