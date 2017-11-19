<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="list-group">
    <div class="list-group-item">
        <b><a href="intervencija/rezervacija">Rezervacija</a> / Brisanje</b>
    </div>
</div>


<form action="intervencija/${intervencija.intervencijaID}/brisanje" method="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>ID</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${intervencija.intervencijaID}
                </p>
            </div>
        </div>

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

        <div class='text-right'>
            <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
            <a href="intervencija/rezervacija" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
        </div>

    </div>

</form>