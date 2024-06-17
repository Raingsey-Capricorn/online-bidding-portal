<div id="form-popup">
    <div id="popup-overlay-item-details" class="overlay-container">
        <div class="popup-box">
            <h2 style="color: green;">Item Details</h2>
            <h4>Item Id</h4><span id="item_id"></span>
            <h4>Bidding Date</h4><span id="item_bidding_date"></span>
            <h4>Highest Bidding Price</h4><span id="max_bidding_price"></span>
            <h4>Last Bidding Price</h4><span id="last_bidding_price"></span>
            <h4>Bidder List</h4>
            <table id="item_details_bidders">
                <tr>
                    <th>UserName</th>
                    <th>Bidder Type</th>
                </tr>
            </table>
            <button class="btn-close" onclick="toggleDetailsAction();">Close</button>
        </div>
    </div>
</div>