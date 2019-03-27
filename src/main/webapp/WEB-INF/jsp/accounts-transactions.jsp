<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<body>

<jsp:include page="app-header.jsp"/>

<div class="container">

    <h1 class="display-4">${accountCode}'s transactions</h1>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--6dp" style="margin: auto">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric" scope="col">CODE</th>
            <th class="mdl-data-table__cell--non-numeric" scope="col"></th>
            <th class="mdl-data-table__cell--non-numeric" scope="col"></th>
            <th class="mdl-data-table__cell--non-numeric" scope="col">VALUE</th>
            <th class="mdl-data-table__cell--non-numeric" scope="col">STATUS</th>
            <th class="mdl-data-table__cell--non-numeric" scope="col">CREATED</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${transactions}" var="tx">
            <tr id="home.tx.${tx.transactionCode}">
                <td class="mdl-data-table__cell--non-numeric" scope="row"><c:out value = "${tx.transactionCode}"/></td>

                <%-- OUTCOMMING TRANSACTION --%>
                <c:if test="${accountCode eq tx.fromAccountCode}">
                    <td id="home.accounts.${tx.transactionCode}.transactions"
                        style="text-align: center;">
                        <img style="width: 25px; height: 25px; margin: auto; vertical-align: center; transform: rotate(180deg)"
                             src="https://cdn0.iconfinder.com/data/icons/flat-round-arrow-arrow-head/512/Red_Arrow_Down-512.png"/>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric" scope="row"><c:out value = "${tx.toAccountCode}"/></td>
                    <td class="mdl-data-table__cell--non-numeric" id="home.tx.${tx.value}.balance" style="color: darkred">
                        <c:out value = "-${tx.value} $"/>
                    </td>

                </c:if>
                <%-- INCOMMING TRANSACTION --%>
                <c:if test="${accountCode eq tx.toAccountCode}">
                    <td id="home.accounts.${tx.transactionCode}.transactions"
                        style="text-align: center;">
                        <img style="width: 25px; height: 25px; margin: auto; vertical-align: center; transform: rotate(180deg)"
                             src="https://cdn0.iconfinder.com/data/icons/flat-round-arrow-arrow-head/512/Green_Arrow_Top-512.png"/>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric" scope="row"><c:out value = "${tx.fromAccountCode}"/></td>
                    <td class="mdl-data-table__cell--non-numeric" id="home.tx.${tx.value}.balance" style="color: darkgreen">
                        <c:out value = "+${tx.value} $"/>
                    </td>
                </c:if>


                <td  class="mdl-data-table__cell--non-numeric" id="home.tx.${tx.value}.activeTransactions"

                        <c:if test="${tx.transactionStatus eq 'COMPLETED'}">
                            style="color: green"
                        </c:if>
                        <c:if test="${tx.transactionStatus eq 'REJECTED'}">
                            style="color: red"
                        </c:if>
                >${tx.transactionStatus}</td>
                <td  class="mdl-data-table__cell--non-numeric" id="home.tx.${tx.value}.activeTransactions">${tx.createdDate}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</div>

</body>