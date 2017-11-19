<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${error!=''}"><h2>${error}</h2></c:if>

<div class="list-group">
    <div class="list-group-item">
        <b>Prijava</b>
    </div>
</div>

<form action="prijava" method ="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>Korisnicko ime</label>
            <div class='col-xs-8'>
                <input name='korisnickoIme' class='form-control' value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Korisnicka sifra</label>
            <div class='col-xs-8'>
                <input name='korisnickaSifra' type="password" class='form-control' value=''/>
            </div>
        </div>

        <div class='text-right'>
            <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
        </div>

    </div>

</form>

<script>

    $(document).ready(function () {
        $('form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                korisnickoIme: {
                    validators: {
                        notEmpty: {}
                    }
                },
                korisnickaSifra: {
                    validators: {
                        notEmpty: {}
                    }
                }
            }
        })
    });

</script>