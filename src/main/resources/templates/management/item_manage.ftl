<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="data:,">
        <title>Back-Office Page</title>
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

            function addItemAction() {
                const formData = new FormData(document.getElementById("form-data"));
                const http = new XMLHttpRequest();
                http.open('POST', '/api/v1/items');
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

            function tableAction(e) {
                const overlay = document.getElementById('popup-overlay-item-details');
                overlay.classList.toggle('show');
                const itemId = e.firstElementChild.firstChild.textContent;
                const http = new XMLHttpRequest();
                http.open('GET', '/api/v1/items/' + itemId, true);
                http.send();
                http.onreadystatechange = function () {
                    if (http.readyState === 4 && http.status === 200) {
                        const data = JSON.parse(http.response);
                        const bidders = data.bidders;
                        const item = data.item;
                        document.getElementById('item_id').innerText = item.id;
                        document.getElementById('item_bidding_date').innerText = data.biddingDate;
                        document.getElementById('max_bidding_price').innerText = data.maxBiddingPrice;
                        document.getElementById('last_bidding_price').innerText = data.lastBiddingPrice;
                        const table = document.getElementById("item_details_bidders");
                        for (let i = 0; i < bidders.length; i++) {
                            const row = table.insertRow(i + 1);
                            row.insertCell(0).innerText = bidders[i].userName;
                            row.insertCell(1).innerText = bidders[i].type;
                        }
                    }
                }
            }

            function toggleDetailsAction() {
                const overlay = document.getElementById('popup-overlay-item-details');
                overlay.classList.toggle('show');
                const table = document.getElementById("item_details_bidders");
                for (let i = 1; i < table.rows.length; i++) {
                    document.getElementById("item_details_bidders").deleteRow(i);
                }
            }

            function toggleAddAction() {
                const overlay = document.getElementById('popup-overlay');
                overlay.classList.toggle('show');
            }
        </script>
    </head>
    <body>
        <div class="content">
            <nobr>
                <a href="${'/view/v1/dashboard'}" class="navigation">Dashboard</a> |
                <button class="navigation" style="font-size: inherit;cursor: pointer" onclick="toggleAddAction();">Add Item</button>
            </nobr>
            <br/><br/>
            <table class="item_list">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>ClientId</th>
                    <th>Availability</th>
                    <th>Price</th>
                    <th>MinBiddingPrice</th>
                    <th>MaxBiddingPrice</th>
                    <th>EntryDate</th>
                    <th>ExpiryDate</th>
                </tr>
                <#foreach item in items>
                    <tr class="tableBody" onclick="tableAction(this);">
                        <td class="has-details">${item.id()}<span class="details">Click for details</span></td>
                        <td class="has-details">${item.name()}<span class="details">Click for details</span></td>
                        <td class="has-details">${item.description()}<span class="details">Click for details</span></td>
                        <td class="has-details">${item.clientId()}<span class="details">Click for details</span></td>
                        <td class="has-details">${item.availability()?c}<span class="details">Click for details</span></td>
                        <td class="has-details number">${item.price()}<span class="details">Click for details</span></td>
                        <td class="has-details number">${item.minBiddingPrice()}<span class="details">Click for details</span></td>
                        <td class="has-details number">${item.maxBiddingPrice()}<span class="details">Click for details</span></td>
                        <td class="has-details">${item.entryDate()?string('dd.MM.yyyy HH:mm:ss')}<span class="details">Click for details</span></td>
                        <td class="has-details">
                            <#if item.expiryDate()??>
                                ${item.expiryDate()?string('dd.MM.yyyy HH:mm:ss')}
                            <#else>N/A</#if>
                            <span class="details">Click for details</span>
                        </td>
                    </tr>
                </#foreach>
            </table>
            <#include "../pagination.ftl"/>
            <#include "../footer.ftl"/>
        </div>
        <#include "item_manage_add.ftl"/>
        <#include "item_manage_detail.ftl"/>
    </body>
</html>