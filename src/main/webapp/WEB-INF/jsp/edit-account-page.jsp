<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .demo-card-wide.mdl-card {
        width: 70%;
        margin:auto;
        padding: 30px;
    }
    .demo-card-wide > .mdl-card__menu {
        color: #fff;
    }
    .login-icon {
        width: auto;
        height: 100px;
        margin: auto;
    }
    .center {
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
</style>
<body>

<jsp:include page="app-header.jsp"/>


<div class="container">


    <h1 class="display-4">
        <c:choose>
            <c:when test="${acc == null}">
                New account set up
            </c:when>
            <c:otherwise>
                ${acc.name}
            </c:otherwise>
        </c:choose>
        </h1>


    <div class="demo-card-wide mdl-card mdl-shadow--6dp">

        <jsp:include page="validation-error.jsp"/>

        <form action="/accounts/edit" method="post">

            <div class="row">
                <div class="col-sm-8">


            <div class="form-group">
                <label for="account.edit.code">Account Code</label>
                <input type="text" class="form-control"
                       id="account.edit.code"
                       name="accountCode"
                       value="${acc.code}"
                       aria-describedby="accountCodeHelp"
                       placeholder="Enter unique account code without spaces, e.g. sport-wear-account" required
                <c:if test="${acc!=null}">
                       readonly="readonly"
                </c:if>
                >
            </div>

            <div class="form-group">
                <label for="account.edit.name">Account Name</label>
                <input type="text" class="form-control"
                       id="account.edit.name"
                       name="accountName"
                       value="${acc.name}"
                       aria-describedby="accountNameHelp"
                       placeholder="Enter account name" required>
            </div>

                    <div class="form-group">
                        <label for="account.edit.name">Account Balance</label>
                        <input type="text" class="form-control"
                               id="account.edit.balance"
                               name="accountBalance"
                               value="${acc.balance}"
                               aria-describedby="accountBalanceHelp"
                               placeholder="Enter account balance" required>
                    </div>

            <div class="form-group">
                <label for="imgUrl">Image url</label>
                <input type="text"
                       class="form-control"
                       id="imgUrl"
                       name="accountImage"
                       value="${acc.img}"
                       aria-describedby="imgHelp"
                       placeholder="Enter account image url" required>
            </div>
                </div>
                <div class="col-sm-4">
            <div class=" mdl-card mdl-shadow--3dp" style="max-width: 200px; max-height: 200px; padding: 10px;">
                <img id="accountImg" style="max-width: 180px; max-height: 180px;" class="center"
                <c:choose>
                    <c:when test="${acc == null}">
                         src="${acc.img == null ? 'http://www.hallspastry.com/gfx/freeTextImage/placeholder.png' : acc.img}"
                    </c:when>
                    <c:otherwise>
                         src="${acc.img}"
                    </c:otherwise>
                </c:choose>

                >
            </div>
                </div>
            </div>

            <button type="submit"
                    id="account.edit.submit"
                    class="mdl-button mdl-js-button mdl-js-ripple-effect"
                    style="width: 100%; margin-top: 50px">
                <c:choose>
                    <c:when test="${acc == null}">
                        CREATE
                    </c:when>
                    <c:otherwise>
                        UPDATE
                    </c:otherwise>
                </c:choose>
                ACCOUNT
            </button>

            <br>
            <a href="/">Back toAccount accounts</a>
        </form>


    </div>

</div>

<script>
    $("#imgUrl").change(function () {
        $("#accountImg").attr("src", $("#imgUrl").val());
    });
</script>