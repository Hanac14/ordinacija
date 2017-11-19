<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="list-group">
    <div class="list-group-item">
        <b><a href="pacijent">Pacijent</a> / Brisanje</b>
    </div>
</div>


<form action="pacijent/${pacijent.pacijentID}/brisanje" method="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>ID</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${pacijent.pacijentID}
                </p>
            </div>
        </div>

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
                <th>ID</th>
                <th>Cena</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pacijent.intervencijaList}" var="intervencija" >
                <tr>
                    <td>
                        <p class='form-control-static'>
                            ${intervencija.intervencijaID}
                        </p>
                    </td>   
                    <td>
                        <p class='form-control-static'>
                            ${intervencija.cena}
                        </p>
                    </td>             
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class='text-right'>
        <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
        <a href="pacijent" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
    </div>

</form>