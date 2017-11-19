<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b>Rezervacija</b>
    </div>
</div>

<table class='table table-condensed table-align'>
    <thead>
        <tr>
<!--             <th>ID</th> -->
            <th>Datum i vreme</th>
            <th>Pacijent</th>
            <th>Stomatolog</th>
            <th class='text-right'><a href="intervencija/unos" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
        </tr>
    </thead>
    <tbody>

        <c:forEach items="${intervencijaList}" var="intervencija" >
        <jsp:useBean id="today" class="java.util.Date" />
			
            
					
			 
				 <c:if test="${intervencija.datumVreme le today}">
  						<tr style='background-color:#ffc2b3 '  >
					</c:if>
					<c:if test="${intervencija.datumVreme ge today}">
  						<tr style='background-color: #eafaea'  >
					</c:if>
                <td>
                    <p class='form-control-static'>
                        <fmt:formatDate value="${intervencija.datumVreme}" var="datumVreme" type="date" pattern="dd.MM.yyyy. HH:mm"  />
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
                    <div class='text-right'>
                        <div class='btn-group'>
                            <a href="intervencija/${intervencija.intervencijaID}/izmena" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                            <a href="intervencija/${intervencija.intervencijaID}/brisanje" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                        </div>
                    </div>
                </td>               
            </tr>
        </c:forEach>
    </tbody>
</table>
<div id="pagination" class = "pagination">
    <ul class="pagination">
    <c:url value="/intervencija/rezervacija" var="prev">
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
                <c:url value="/intervencija/rezervacija" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
        </li>
    </c:forEach>
    <c:url value="/intervencija/rezervacija" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <li><c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
    </li>
    </ul>
</div>