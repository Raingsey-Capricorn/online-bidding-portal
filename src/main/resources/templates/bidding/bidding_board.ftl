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
                self.location = 'board?page=' + ${page} +
                    '&size=' + select.value +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }

            function pagingAction(value) {
                self.location = 'board?page=' + value +
                    '&size=' + ${size} +
                    '&sortField=maxBiddingPrice&sortDirection=DESC'
                ;
            }

            function toggleBidPopupAction() {
                document.getElementById('bidding-popup-overlay').classList.toggle('show');
            }

            function tableAction(row) {
                const price = row.children[3].firstChild.textContent.replace(/,/g, '');
                document.getElementById('bidding_item_id').value = row.children[0].firstChild.textContent;
                document.getElementById('bidding_item_name').value = row.children[1].firstChild.textContent;
                document.getElementById('bidding_item_desc').value = row.children[3].firstChild.textContent;
                document.getElementById('bidding_item_price').value = Number(price);
                document.getElementById('bidding_item_price').setAttribute("min", price);
                document.getElementById('bidding-popup-overlay').classList.toggle('show');
            }

            function postBidAction(e) {
                const formData = new FormData(document.getElementById("form-bidding"));
                const http = new XMLHttpRequest();
                http.open('POST', '${'/api/v1/bidding/bid'}', true);
                http.send(formData);
                http.onreadystatechange = function () {
                    if (http.readyState === 4 && http.status === 200) {
                        alert('Successfully Saved.');
                        self.location = 'board?page=' + ${page} +
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
                    <th>Max Price</th>
                    <th>EntryDate</th>
                    <th>ExpiryDate</th>
                </tr>
                <#list items as item>
                    <tr class="tableBody" onclick="tableAction(this);">
                        <td class="has-details">${item.id()}<span class="details">Click to bid</span></td>
                        <td class="has-details">${item.name()}<span class="details">Click to bid</span> </td>
                        <td class="has-details"><#if item.description()??>${item.description()}<#else>N/A</#if><span class="details">Click to bid</span></td>
                        <td class="has-details number">${item.price()}<span class="details">Click to bid</span></td>
                        <td class="has-details number">${item.maxBiddingPrice()}<span class="details">Click to bid</span></td>
                        <td class="has-details">
                            <#if item.entryDate()??>
                                ${item.entryDate()?string('dd.MM.yyyy HH:mm:ss')}
                            <#else>N/A</#if>
                            <span class="details">Click for details</span>
                        </td>
                        <td class="has-details">
                            <#if item.expiryDate()??>
                                ${item.expiryDate()?string('dd.MM.yyyy HH:mm:ss')}
                            <#else>N/A</#if>
                            <span class="details">Click for details</span>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../pagination.ftl"/>
            <#include "../footer.ftl"/>
        </div>
        <#include "bidding_page_bid.ftl"/>
    </body>
</html>