<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<div class="control-label">
    <label class='control-label col-xs-4'>Pretraga</label>
    <div class='col-xs-8'>
        <input type="text"  name='pretraga' id="#target" class='form-control'  onKeyUp="pretraga()">
    </div>
</div>

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
                <c:url value="/user/list" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/user/list" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>
<table class='table table-condensed table-align' id ="tabela">
    <thead>
        <tr id ="tr">
           
            <th>JMBG</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th class='text-right'><a href="pacijent/unos" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
        </tr>
    </thead>
    <tbody id="bodi">
        <c:forEach items="${pacijentList}" var="pacijent" >

            <tr> 
                <td>
                    <p class='form-control-static'>
                        ${pacijent.jmbg}
                    </p>
                </td>
                <td>
                    <p class='form-control-static'>
                        ${pacijent.ime}
                    </p>
                </td>    
                <td>
                    <p class='form-control-static'>
                        ${pacijent.prezime}
                    </p>
                </td>          
                <td>
                    <div class='text-right'>
                        <div class='btn-group'>
                            <a href="pacijent/${pacijent.pacijentID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                            <a href="pacijent/${pacijent.pacijentID}/izmena" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                            <a href="pacijent/${pacijent.pacijentID}/brisanje" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                        </div>
                    </div>
                </td>               
            </tr>
        </c:forEach>
    </tbody>
</table>

<scripts>
    <script>
       function pretraga(data) {
         var pretraga =  document.getElementById("#target").value;
         $('#tabela tbody').remove();
         
          $.ajax({
           url: "http://localhost:8080/ordinacija/pacijent/pretraga?pretraga="+pretraga,
           type: "GET",
           success: function (data) {
          $('#tabela tbody').remove();
            var trHTML = '';
                  $.each(data, function (i, item) {
                      
                        trHTML += '<tr><td>  <p class='+'form-control-static'+'>' + data[i].jmbg + ' </p></td><td><p class='+'form-control-static'+'>' + data[i].ime + '</p></td><td><p class='+'form-control-static'+'>'+ data[i].prezime + '</p></td>';
                       trHTML += '<td>  <div class='+'text-right'+'> <div class='+'btn-group'+'>' +'  <a href=pacijent/'+data[i].pacijentID+' class='+'"btn btn-default"'+'><span class='+'"fa fa-fw fa-info"'+'></span></a>'
                         +'  <a href=pacijent/'+data[i].pacijentID+'/izmena  class='+'"btn btn-warning"'+'><span class='+'"fa fa-fw fa-file"'+'></span></a>'
                         +'  <a href="+"pacijent/'+data[i].pacijentID+'/brisanje class='+'"btn btn-danger"'+'><span class='+'"fa fa-fw fa-trash"'+'></span></a>'
                     +"   </div>"
                   +" </div>"
                +"</td> </tr>"   ;
           
            }); 
                  $('#tabela ').append(trHTML); 
        }
    });
 };
        </script>
</scripts>