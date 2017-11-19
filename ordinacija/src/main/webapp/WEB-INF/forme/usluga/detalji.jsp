<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b><a href="usluga">Usluga</a> / Detalji</b>
    </div>
</div>

<div class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>ID</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${usluga.uslugaID}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Naziv</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${usluga.naziv}
                </p>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Cena</label>
            <div class='col-xs-8'>
                <p class="form-control-static">
                    ${usluga.cena} RSD
                </p>
            </div>
        </div>

        <div class='text-right'>
            <a href="usluga/${usluga.uslugaID}/izmena" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
            <a href="usluga" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
        </div>

    </div>

</div>