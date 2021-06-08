<%@ taglib prefix="customtaglib" uri="http://com.my.finalProject/tags" %>
<div class="d-flex justify-content-sm-end pt-4">
    <div>
        <customtaglib:hiTag account="${sessionScope.account}"/>
        <form action="controller" method="get">
            <div class="button">
                <input type="hidden" name="command" value="logout"/>
                <button class="btn btn-sm" type="submit">Logout</button>
            </div>
        </form>
    </div>
</div>


