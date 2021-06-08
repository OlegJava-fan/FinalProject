<div class="pl-sm-2 pt-sm-2">
    <form action="controller" method="get">
        <div class="button">
            <input type="hidden" name="id" value="${sessionScope.account.id}"/>
            <input type="hidden" name="command" value="findAccountAct">
            <button class="btn btn-sm" type="submit">My result</button>
        </div>
    </form>
</div>