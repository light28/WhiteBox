<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss"/>
    <link href="${jstlCss}" rel="stylesheet"/>

</head>

<style>
    .header-menu-option-icon {
        width: 20px;
        height: auto;
        margin-right: 10px;
    }
</style>

<!-- Image and text -->
<header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
    <div class="mdl-layout__header-row">
        <img src="/img/home-page-accounts.png" width="30" height="30" class="d-inline-block align-top" alt="">
        <span class="mdl-layout-title" style="margin-left: 10px">
            <c:choose>
            <c:when test="${sessionScope.user.userRole!= 'GUEST'}">
                 Hi, ${sessionScope.user.firstName}
            </c:when>
                <c:otherwise>
                    Accounts
                </c:otherwise>
            </c:choose>
        </span>
        <div class="mdl-layout-spacer"></div>

        <c:if test="${sessionScope.user.userRole!= 'GUEST'}">

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable" id="home.user.menu">
            <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
                <i class="material-icons">search</i>
            </label>
            <div class="mdl-textfield__expandable-holder">
                <input class="mdl-textfield__input" type="text" id="search">
                <label class="mdl-textfield__label" for="search">Enter your query...</label>
            </div>
        </div>
        <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
            <i class="material-icons">more_vert</i>
        </button>
        <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
            <li class="mdl-menu__item" onclick="window.location='/'">
                <img class="header-menu-option-icon" src="https://img.icons8.com/metro/1600/user-group-man-man.png">
                Accounts
            </li>
            <li class="mdl-menu__item" onclick="window.location='/accounts/edit/new'">
                <img class="header-menu-option-icon" src="https://img.icons8.com/metro/1600/add-user-male.png">
                Add account
            </li>
            <li class="mdl-menu__item" onclick="window.location='/transactions'">
                <img class="header-menu-option-icon" src="https://cdn1.iconfinder.com/data/icons/finance-solid-icons-vol-1/48/035-512.png">
                Manage transactions</li>
            <li class=" mdl-menu__item--full-bleed-divider"></li>
            <li class="mdl-menu__item" onclick="window.location='/auth/logout'">
                <img class="header-menu-option-icon" src="https://cdn.iconscout.com/icon/premium/png-256-thumb/logout-1-110657.png">
                Logout</li>
        </ul>
        </c:if>
    </div>
</header>
<%--<nav class="navbar navbar-light bg-light">--%>
    <%--<a class="navbar-brand" href="#">--%>
        <%--<img src="/img/home-page-accounts.png" width="30" height="30" class="d-inline-block align-top" alt="">--%>
        <%--<c:choose>--%>
            <%--<c:when test="${sessionScope.user.userRole!= 'GUEST'}">--%>
                <%--Hi, ${sessionScope.user.firstName}--%>

                <%--<div id="home.user.menu" class="dropdown justify-content-end">--%>
                    <%--<a class="btn btn-secondary dropdown-toggle " href="#" role="button" id="dropdownMenuLink"--%>
                       <%--data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                        <%--Actions--%>
                    <%--</a>--%>

                    <%--<div class="dropdown-menu dropdown-menu-right " aria-labelledby="dropdownMenuLink">--%>
                        <%--<a class="dropdown-item" href="/accounts/edit/new">Add account</a>--%>
                        <%--<a class="dropdown-item" href="/transactions">Manage transactions</a>--%>
                        <%--<div class="dropdown-divider"></div>--%>
                        <%--<a class="dropdown-item" href="/auth/logout">Logout</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
                <%--Accounts--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>

    <%--</a>--%>
<%--</nav>--%>

<script
        src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>

<%--<script type="text/javascript"--%>
        <%--src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"></script>--%>

<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>