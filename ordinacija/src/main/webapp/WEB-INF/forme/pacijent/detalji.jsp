<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="list-group">
    <div class="list-group-item">
        <b><a href="pacijent">Pacijent</a> / Detalji</b>
    </div>
</div>


<div class='form-horizontal'>

    <div class="col-sm-6">

<!--        <div class='form-group'>
            <label class='control-label col-xs-4'>ID</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.pacijentID}
                </p>
            </div>
        </div>-->

        <div class='form-group'>
            <label class='control-label col-xs-4'>JMBG</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.jmbg}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Ime</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.ime}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Prezime</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.prezime}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Datum rodjenja</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    <fmt:formatDate value="${pacijent.datumRodjenja}" var="datumRodjenja" type="date" pattern="dd.MM.yyyy." />
                    ${datumRodjenja}
                </p>
            </div>
        </div>

    </div>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>Grad</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.grad.naziv}
                </p> 
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Adresa</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.adresa}
                </p>   
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Mail</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.mail}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Broj telefona</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.brojTelefona}
                </p>
            </div>
        </div>

    </div>

    <table class='table table-condensed table-align'>
        <thead>
            <tr>
                <th>Datum intervencije</th>
                <th>Cena</th>
              
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pacijent.intervencijaList}" var="intervencija" >
                <tr>
                    <td>
                     <div >
<!--    class='form-control'          class='form-group'                <label class='control-label col-xs-4'>Datum i vreme</label>-->
<!--                            <div class='col-xs-8'>-->
                                  <fmt:formatDate value="${intervencija.datumVreme}" var="datumVreme" type="date" pattern="dd.MM.yyyy. " />
<!--                                  <input name='datumVreme' value='${intervencija.datumVreme}' placeholder="dd.mm.yyyy. "/>-->
                                  <p class='form-control-static'>
                                      ${datumVreme}
                                  </p>
                            </div>
                   
                     </td>
                    <td>
                        <p class='form-control-static'>
                            ${intervencija.cena}
                        </p>
                    </td>     
                    <td>
                        <div class='text-right'>
                            <div class='btn-group'>
                                <a href="intervencija/${intervencija.intervencijaID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                <a href="intervencija/${intervencija.intervencijaID}/brisanje" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                            </div>
                        </div>
                    </td>               
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class='text-right'>
        <a href="pacijent/${pacijent.pacijentID}/izmena" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
        <a href="pacijent" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
    </div>

</div>