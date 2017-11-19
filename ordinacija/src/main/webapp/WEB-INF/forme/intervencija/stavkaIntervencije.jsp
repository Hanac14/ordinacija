<tr id="stavkaIntervencije_${rb}">
    <td>
        <p class="form-control-static">${usluga.naziv}</p>
    </td>
    <td>
        <p class="form-control-static" id='stavkaIntervencije_${rb}_cena'>${usluga.cena}</p>
    </td>
    <td class="text-right">
        <input hidden name='stavkaIntervencijeList[${rb}].uslugaID' value='${usluga.uslugaID}'>
        <input hidden name='stavkaIntervencijeList[${rb}].status' id='stavkaIntervencije_${rb}_status' value='1'>
        <button type="button" onclick="obrisiStavku(${rb})" class="btn btn-danger"><span class="fa fa-fw fa-remove"></span></button>
    </td>
</tr>