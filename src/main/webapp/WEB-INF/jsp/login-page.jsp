<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
    .demo-card-wide.mdl-card {
        width: 50%;
        margin:auto;
        margin-top: 100px;
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
</style>
<body>


<jsp:include page="app-header.jsp"/>

<div class="container">

    <div class="demo-card-wide mdl-card mdl-shadow--6dp">
        <img class="login-icon" src="https://cdn0.iconfinder.com/data/icons/viking-2/500/viking_4-512.png">
     <jsp:include page="validation-error.jsp" />


    <form action="/auth/login" method="post">
        <div class="form-group">
            <label for="login.email">Email address</label>
            <input type="email" class="form-control" id="login.email" name="email" aria-describedby="emailHelp" placeholder="Enter email" required>
        </div>
        <div class="form-group">
            <label for="login.password">Password</label>
            <input type="password" class="form-control" id="login.password" name="password" placeholder="Password" required>
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
            <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div>
        <button type="submit"
                class="mdl-button mdl-js-button mdl-js-ripple-effect"
                id="login.submit"
                style="width: 100%">LOG IN</button>


        <a href="/register" style="margin-top: 20px;">Register</a>
    </form>
    </div>
</div>


</body>

</html>
