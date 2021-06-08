<div class="pl-sm-2 pt-sm-2">
    <form action="controller" method="get">
        <div class="button">
            <input type="hidden" name="account_id" value="${sessionScope.account.id}"/>
            <input type="hidden" name="command" value="findCurrentOrders">
            <button class="btn btn-sm" type="submit">My orders</button>
        </div>
    </form>
</div>