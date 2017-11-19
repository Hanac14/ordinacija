<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b>Intervencija</b>
    </div>
</div>

<table class='table table-condensed table-align'>
    <thead>
        <tr>
<!--            <th>ID</th>-->
            <th>Datum i vreme</th>
            <th>Pacijent</th>
            <th>Stomatolog</th>
            <th>Cena</th>
            <th class='text-right'><a href="intervencija/unos" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${intervencijaList}" var="intervencija" >

            <tr>  
<!--                <td>
                    <p class='form-control-static'>
                        ${intervencija.intervencijaID}
                    </p>
                </td>-->
                <td>
                    <p class='form-control-static'>
                        <fmt:formatDate value="${intervencija.datumVreme}" var="datumVreme" type="date" pattern="dd.MM.yyyy. HH:mm" />
                        ${datumVreme}
                    </p>
                </td>
                <td>
                    <p class='form-control-static'>
                        <a href="pacijent/${intervencija.pacijentID}">${intervencija.pacijent.ime} ${intervencija.pacijent.prezime}</a>
                    </p>
                </td>
                <td>
                    <p class='form-control-static'>
                        ${intervencija.stomatolog.ime} ${intervencija.stomatolog.prezime}
                    </p>
                </td>
                <td>
                    <p class='form-control-static'>
                        ${intervencija.cena} RSD
                    </p>
                </td>       
                <td>
                    <div class='text-right'>
                        <div class='btn-group'>
                            <a href="intervencija/${intervencija.intervencijaID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                        </div>
                    </div>
                </td>               
            </tr>
        </c:forEach>
    </tbody>
</table>
<div id="pagination">

    <c:url value="/user/list" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/intervencija/rezervacija" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/intervencija/rezervacija" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>
