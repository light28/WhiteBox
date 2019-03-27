<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .demo-card-wide.mdl-card {
        width: 50%;
        margin:auto;
        margin-top: 50px;
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
        <img class="login-icon" src="https://www.freeiconspng.com/uploads/panda-icon-25.png">
       <jsp:include page="validation-error.jsp"/>
        <form action="/auth/register" method="post">
            <div class="form-group">
                <label for="register.firstName">First Name</label>
                <input type="firstName" class="form-control" id="register.firstName" name="firstName"
                       aria-describedby="firstNameHelp" placeholder="Enter firstName" required>
            </div>
            <div class="form-group">
                <label for="register.lastName">Last Name</label>
                <input type="lastName" class="form-control" id="register.lastName" name="lastName"
                       aria-describedby="lastNameHelp" placeholder="Enter lastName" required>
            </div>
            <div class="form-group">
                <label for="register.email">Email address</label>
                <input type="email" class="form-control" id="register.email" name="email"
                       aria-describedby="emailHelp" placeholder="Enter email" required>
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.
                </small>
            </div>
            <div class="form-group">
                <label for="register.password">Password</label>
                <input type="password" class="form-control" id="register.password" name="password"
                       placeholder="Password" required>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="register.rememberme" name="rememberMe">
                <label class="form-check-label" for="register.rememberme">Check me out</label>
            </div>
            <button type="submit"
                    id="register.submit"
                    class="mdl-button mdl-js-button mdl-js-ripple-effect"
                    style="width: 100%">REGISTER</button>

            <a href="/login" style="margin-top: 10px;">Login</a>
        </form>
    </div>
</div>

</body>

</html>
