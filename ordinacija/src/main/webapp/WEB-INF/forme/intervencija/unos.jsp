
<!doctype html>
<head>
<!--    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>-->
    <script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
          <script src="https://cdn.jsdelivr.net/momentjs/2.18.1/moment.min.js"></script>

        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
         
    </head>


<body>
<div class="list-group">
    <div class="list-group-item">
        <b><a href="intervencija">Intervencija</a> / Unos</b>
    </div>
</div>

<form action="intervencija/unos" method ="post" class='form-horizontal'>

    <div class="col-sm-6">

        <div class='form-group'>
            <label class='control-label col-xs-4'>Pacijent</label>
            <div class='col-xs-8'>
                <select name='pacijentID' id="pacijentID" class='form-control'></select>
            </div>
        </div>
<!--id="training-date"-->
        <div class='form-group'>
            <label class='control-label col-xs-4'>Datum i vreme</label>
            <div class='col-xs-8'>
                <div class='input-group date' id='rezervacija-datepicker-js'>                            
                    <input onkeydown="return false" name='datumVreme' class="input-me form-control name-bonus project-input date" 
                           placeholder="Izaberite datum i vreme "/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar" ></span>
                    </span>
                </div>
            </div>
                </div>    

       

        <div class='text-right'>
            <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
            <a href="intervencija" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
        </div>

    </div>

</form>

<script>

    $(document).ready(function () {
        $.get("pacijent/api", {}, function (data) {
            $("#pacijentID").append("<option value=''>--</option>");
            for (i = 0; i < data.length; i++) {
                $("#pacijentID").append("<option value='" + data[i].pacijentID + "'>" + data[i].ime + " " + data[i].prezime + "</option>");
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
                pacijentID: {
                    validators: {
                        notEmpty: {
                            message: 'Pacijent nije unet'
                        }
                    }
                },
//                datumVreme: {
//                    validators: {
//                        notEmpty: {
//                            message: 'Datum i vreme nisu uneti'
//                        },
////                        date: {
////                            format: "DD.MM.YYYY. h:m",
////                            separator: ".",
////                            message: "Datum i vreme nisu validni"
////                        }
//                    }
//                }
            }
        })
    });

</script>
    <script> $('#rezervacija-datepicker-js').datetimepicker({       
//        use24hours: true,
       format: 'DD.MM.YYYY. HH:mm',
       minDate:moment(),
        defaultDate: new Date(),
         
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom',
            ignoreReadonly: true
        }
    });</script>
</body>
</html>
