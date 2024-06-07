<div id="form-popup">
    <div id="popup-overlay" class="overlay-container">
        <div class="popup-box">
            <h2 style="color: green;">New Item Form</h2>
            <form class="form-container" id="form-data">
                <label class="form-label" for="name">Name:</label>
                <input id="name"
                       name="name"
                       type="text"
                       class="form-input"
                       placeholder="Item name ..."
                       required/>
                <label class="form-label" for="description">Description</label>
                <input type="text"
                       id="description"
                       class="form-input"
                       name="description"
                       placeholder="Description ..."
                       required/>
                <nobr>
                    <label for="country">Availability</label>
                    <div>
                        <input type="radio" id="true" name="availability" value=true>
                        <label for="true">Yes</label>
                        <input type="radio" id="true" name="availability" value=false>
                        <label for="false">No</label>
                    </div>
                </nobr>
                <label class="form-label" for="price">Price</label>
                <input id="price"
                       name="price"
                       type="number"
                       class="form-input"
                       placeholder="Price ..."
                       required/>
                <nobr>
                    <button class="btn-submit" type="button" onclick="addItemAction();">Submit</button>
                    <button class="btn-close" onclick="toggleAddAction();">Close</button>
                </nobr>
            </form>
        </div>
    </div>
</div>