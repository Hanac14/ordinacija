<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="list-group">
    <div class="list-group-item">
        <b><a href="intervencija">Intervencija</a> / Popunjavanje</b>
    </div>
</div>

<form action="intervencija/${intervencija.intervencijaID}/izmena" method ="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>Pacijent</label>
            <div class='col-xs-8'>
                <select name='pacijentID' id="pacijentID" class='form-control'></select>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Datum i vreme</label>
            <div class='col-xs-8'>
                <fmt:formatDate value="${intervencija.datumVreme}" var="datumVreme" type="date" pattern="dd.MM.yyyy. HH:mm" />
                <input name='datumVreme' class='form-control' value='${datumVreme}' placeholder="dd.mm.yyyy. HH:mm"/>
            </div>
        </div>   

        <div class='form-group'>
            <label class='control-label col-xs-4'>Opis</label>
            <div class='col-xs-8'>
                <textarea class="form-control" style="resize: vertical" rows="10" name="opis"></textarea>
            </div>
        </div>

        <div class='form-group'>
            <label class='control-label col-xs-4'>Cena</label>
            <div class='col-xs-8'>
                <input name='cena' id='cena' readonly class='form-control' value='0'/>
            </div>
        </div>    

        <div class='text-right'>
            <button type="submit" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span></button>
            <a href="intervencija" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
        </div>

    </div>

    <div class="col-sm-6">

        <div class='form-inline'>
            <select name='uslugaID' id="uslugaID" class='form-control'></select>
            <button type='button' onclick="dodajStavku()" class="btn btn-primary"><span class="fa fa-fw fa-plus"></span></button>
        </div>

        <table class='table table-condensed table-align'>
            <thead>
                <tr>
                    <th>Usluga</th>
                    <th>Cena</th>
                    <th></th>
                </tr>
            </thead>
            <tbody id="tabela">
            </tbody>
        </table>

    </div>

</form>

<script>

    var rb = 0;

    function dodajStavku() {
        if ($("#uslugaID").val()) {
            $.get("intervencija/stavkaIntervencije", {rb: rb, uslugaID: $("#uslugaID").val()}, function (data) {
                $("#tabela").append(data);
            });
            $("#cena").val(parseFloat($("#cena").val()) + parseFloat($("#uslugaID :selected").attr("cena")));
            rb++;
        }
    }

    function obrisiStavku(rb) {
        $("#cena").val(parseFloat($("#cena").val()) - parseFloat($("#stavkaIntervencije_" + rb + "_cena").html()));
        $("#stavkaIntervencije_" + rb + "_status").val(0);
        $("#stavkaIntervencije_" + rb).hide();
    }
    $(document).ready(function () {
        $.get("pacijent/api", {}, function (data) {
            $("#pacijentID").append("<option value=''>--</option>");
            pacijentID = ${intervencija.pacijentID};
            for (i = 0; i < data.length; i++) {
                if (pacijentID == data[i].pacijentID) {
                    $("#pacijentID").append("<option selected value='" + data[i].pacijentID + "'>" + data[i].ime + " " + data[i].prezime + "</option>");
                } else {
                    $("#pacijentID").append("<option value='" + data[i].pacijentID + "'>" + data[i].ime + " " + data[i].prezime + "</option>");
                }
            }
        });
    });
    $(document).ready(function () {
        $.get("usluga/api", {}, function (data) {
            $("#uslugaID").append("<option value=''>--</option>");
            for (i = 0; i < data.length; i++) {
                $("#uslugaID").append("<option value='" + data[i].uslugaID + "' cena='" + data[i].cena + "'>" + data[i].naziv + "</option>");
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
                datumVreme: {
                    validators: {
                        notEmpty: {
                            message: 'Datum i vreme nisu uneti'
                        },
                        date: {
                            format: "DD.MM.YYYY. h:m",
                            separator: ".",
                            message: "Datum i vreme nisu validni"
                        }
                    }
                },
                opis: {
                    validators: {
                        notEmpty: {
                            message: 'Opis nije unet'
                        }
                    }
                }
            }
        })
    });

</script>