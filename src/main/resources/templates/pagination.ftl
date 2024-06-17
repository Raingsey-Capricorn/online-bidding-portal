<div class="pagination">
    <label for="displays">Total: ${total}</label>
    <label for="displays">Size:</label>
    <select id="displays" onchange="selectAction(this);">
        <#foreach display in displays>
            <option value=${display}>${display}</option>
        </#foreach>
    </select>
    <#if hasPrev><button onclick="pagingAction(${prev})" id="previous"/></#if>
    <#if hasNext><button onclick="pagingAction(${next})" id="next"/></#if>
</div>