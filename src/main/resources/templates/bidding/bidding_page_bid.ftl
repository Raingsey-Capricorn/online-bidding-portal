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
                <label class="form-label" for="name">Name
                    <input type="text"
                           name="name"
                           id="bidding_item_name"
                           class="form-input"
                           readonly="readonly"
                           required/>
                </label>
                <label class="form-label" for="description">Description
                    <input type="text"
                           name="description"
                           id="bidding_item_desc"
                           class="form-input"
                           readonly="readonly"
                           required/>
                </label>
                <label class="form-label" for="bidding_item_price">Price
                    <input type="number"
                           name="price"
                           id="bidding_item_price"
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