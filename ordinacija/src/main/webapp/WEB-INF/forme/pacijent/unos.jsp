<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b><a href="pacijent">Pacijenti</a> / Unos</b>
    </div>
</div>

<form action="pacijent/unos" method ="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>JMBG</label>
            <div class='col-xs-8'>
                <input name='jmbg' class='form-control' value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Ime</label>
            <div class='col-xs-8'>
                <input name='ime' class='form-control' value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Prezime</label>
            <div class='col-xs-8'>
                <input name='prezime' class='form-control' value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Datum rodjenja</label>
            <div class='col-xs-8'>
                <input name='datumRodjenja' class='form-control' placeholder="dd.mm.yyyy." value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Grad</label>
            <div class='col-xs-8'>
                <select name='gradID' id="gradID" class='form-control'></select>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Adresa</label>
            <div class='col-xs-8'>
                <input name='adresa' class='form-control' value=''/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Mail</label>
            <div class='col-xs-8'>
                <input name='mail' class='form-control' value='' placeholder="username@domen"/>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Broj telefona</label>
            <div class='col-xs-8'>
                <input name='brojTelefona' class='form-control' value=''/>
            </div>
        </div>

        <div class='text-right'>
            <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
            <a href="pacijent" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
        </div>           

    </div>

</form>

<script>

    $(document).ready(function () {
        $.get("grad/api", {}, function (data) {
            $("#gradID").append("<option value=''>--</option>");
            for (i = 0; i < data.length; i++) {
                $("#gradID").append("<option value='" + data[i].gradID + "'>" + data[i].naziv + "</option>");
            }
        });
    });

    $(document).ready(function () {
        $('form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                jmbg: {
                    validators: {
                        remote: {
                            url: "/ordinacija/pacijent/jmbg",
                            message: "JMBG je zauzet"
                        },
                        notEmpty: {
                            message: 'JMBG nije unet'
                        }
                        
                    }
                },
                ime: {
                    validators: {
                        notEmpty: {
                            message: 'Ime nije uneto'
                        }
                    }
                },
                prezime: {
                    validators: {
                        notEmpty: {
                            message: 'Prezime nije uneto'
                        }
                    }
                },
                datumRodjenja: {
                    validators: {
                        notEmpty: {
                            message: 'Datum rodjenja nije unet'
                        },
                        date: {
                            format: "DD.MM.YYYY.",
                            separator: ".",
                            message: "Datum rodjenja nije validan"
                        }
                    }
                },
                gradID: {
                    validators: {
                        notEmpty: {
                            message: 'Grad nije unet'
                        }
                    }
                },
                adresa: {
                    validators: {
                        notEmpty: {
                            message: 'Adresa nije uneta'
                        }
                    }
                },
                mail: {
                    validators: {
                        emailAddress: {
                            message: "Mail nije validan"
                        },
                        notEmpty: {
                            message: 'Mail nije unet'
                        }
                    }
                },
                brojTelefona: {
                    validators: {
                        notEmpty: {
                            message: 'Broj telefona nije unet'
                        }
                    }
                }
            }
        })
    });

</script>