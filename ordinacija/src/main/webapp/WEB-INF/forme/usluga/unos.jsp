<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b><a href="usluga">Usluga</a> / Unos</b>
    </div>
</div>

<form action="usluga/unos" method ="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>Naziv</label>
            <div class='col-xs-8'>
                <input name='naziv' class='form-control' value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Cena</label>
            <div class='col-xs-8'>
                <input name='cena' class='form-control' value=''/>
            </div>
        </div>

        <div class='text-right'>
            <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
            <a href="usluga" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
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
                naziv: {
                    validators: {
                        notEmpty: {
                            message: 'Naziv nije unet'
                        }
                    }
                },
                cena: {
                    validators: {
                        notEmpty: {
                            message: 'Cena nije uneta'
                        },
                        numeric: {
                            decimalSeparator : '.',
                            message: 'Cena nije validna'
                        }
                    }
                }
            }
        })
    });

</script>