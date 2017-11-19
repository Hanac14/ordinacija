<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="list-group">
    <div class="list-group-item">
        <b><a href="intervencija">Intervencija</a> / Detalji</b>
    </div>
</div>


<div class='form-horizontal'>

    <div class="col-sm-6">

<!--        <div class='form-group'>
            <label class='control-label col-xs-4'>ID</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${intervencija.intervencijaID}
                </p>
            </div>
        </div>-->

        <div class='form-group'>
            <label class='control-label col-xs-4'>Datum i vreme</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    <fmt:formatDate value="${intervencija.datumVreme}" var="datumVreme" type="date" pattern="dd.MM.yyyy. HH:mm" />
                    ${datumVreme}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Pacijent</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${intervencija.pacijent.ime} ${intervencija.pacijent.prezime}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Stomatolog</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${intervencija.stomatolog.ime} ${intervencija.stomatolog.prezime}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Opis</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${intervencija.opis}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Cena</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${intervencija.cena} RSD
                </p>
            </div>
        </div>

    </div>

    <div class="col-sm-12">   

        <table class='table table-condensed table-align'>
            <thead>
                <tr>

                    <th>Rb</th>
                    <th>Usluga</th>
                     <th>Cena pojedinacne usluge</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${intervencija.stavkaIntervencijeList}" var="stavkaIntervencije" >
                       
                    <tr>
<!--                        <a href="usluga/${stavkaIntervencije.uslugaID}">${stavkaIntervencije.usluga.naziv}</a>-->
                        <td>
                            <p class='form-control-static'>
                                ${stavkaIntervencije.redniBrojStavke}
                            </p>
                        </td>   
                        <td>
                            <p class='form-control-static'>
                                <a href="usluga/${stavkaIntervencije.uslugaID}">${stavkaIntervencije.usluga.naziv}</a>
                            </p>
                        </td>
                         <td>
                            <p class='form-control-static'>
                              ${stavkaIntervencije.usluga.cena}
                            </p>
                        </td>   
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class='text-right'>
            <a href="intervencija" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
        </div>

    </div>

</div>