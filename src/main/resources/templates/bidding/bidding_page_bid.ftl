<div id="form-bidding-popup">
    <div id="bidding-popup-overlay" class="overlay-container">
        <div class="popup-box">
            <h2 style="color: green;">Bidding Form</h2>
            <form class="form-container" id="form-bidding">
                <input type="hidden"
                       id="bidding_item_id"
                       hidden="hidden"
                       name="id"
                       required/>
                <label class="form-label" for="bidding_item_name">Name
                    <input type="text"
                           id="bidding_item_name"
                           class="form-input"
                           disabled="disabled"/>
                </label>
                <label class="form-label" for="bidding_item_desc"> Description
                    <input type="text"
                           id="bidding_item_desc"
                           class="form-input"
                           disabled="disabled"/>
                </label>
                <label class="form-label" for="bidding_item_price">Price
                    <input id="bidding_item_price"
                           name="price"
                           type="number"
                           step=".00"
                           class="form-input"
                           placeholder="Price ..."
                           required/>
                </label>
                <nobr>
                    <button class="btn-submit" type="button" onclick="postBidAction(this);">Submit</button>
                    <button class="btn-close" onclick="toggleBidPopupAction();">Close</button>
                </nobr>
            </form>
        </div>
    </div>
</div>