<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b>Usluge</b>
    </div>
</div>

<table class='table table-condensed table-align'>

    <thead>
        <tr>
<!--            <th>ID</th>-->
            <th>Naziv</th>
            <th>Cena</th>
            <th class='text-right'><a href="usluga/unos" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
        </tr>
    </thead>
    
    <tbody>
        <c:forEach items="${uslugaList}" var="usluga" >

            <tr>
<!--                <td>
                    <p class='form-control-static'>
                        ${usluga.uslugaID}
                    </p>
                </td> -->
                <td>
                    <p class='form-control-static'>
                        ${usluga.naziv}
                    </p>
                </td>    
                <td>
                    <p class='form-control-static'>
                        ${usluga.cena} RSD
                    </p>
                </td>      
                <td>
                    <div class='text-right'>
                        <div class='btn-group'>
                            <a href="usluga/${usluga.uslugaID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                            <a href="usluga/${usluga.uslugaID}/izmena" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                            <a href="usluga/${usluga.uslugaID}/brisanje" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                        </div>
                    </div>
                </td>               
            </tr>
        </c:forEach>
    </tbody>

</table>
<div id="pagination" class = "pagination">
    <ul class="pagination">
    <c:url value="/usluga" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
   
    <li>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>
    </li>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <li>
        <c:choose>
            <c:when test="${page == i.index}">
                <span class="page-link"  style="background-color:#ddd">${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/usluga" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
        </li>
    </c:forEach>
    <c:url value="usluga" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <li><c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
    </li>
    </ul>
</div>
