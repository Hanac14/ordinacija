<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b><a href="grad">Grad</a> / Unos</b>
    </div>
</div>

<form action="grad/unos" method ="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>PTT</label>
            <div class='col-xs-8'>
                <input name='ptt' class='form-control' value='${grad.ptt}'/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Naziv</label>
            <div class='col-xs-8'>
                <input name='naziv' class='form-control' value='${grad.naziv}'/>
            </div>
        </div>

        <div class='text-right'>
            <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
            <a href="grad" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
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
                ptt: {
                    validators: {
                        notEmpty: {
                            message: "PTT nije unet"
                        }
                    }
                },
                naziv: {
                    validators: {
                        notEmpty: {
                            message: 'Naziv nije unet'
                        }
                    }
                }
            }
        })
    });

</script>