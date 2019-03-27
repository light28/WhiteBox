<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${pageContext.request.queryString!=null && pageContext.request.queryString.contains('error=true')}">
    <div class="alert alert-danger" role="alert">
            ${sessionScope.errors.message}
    </div>
</c:if>