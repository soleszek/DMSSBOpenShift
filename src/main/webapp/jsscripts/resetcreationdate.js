$(document).ready(function () {
    $("#reset-creationdate").click(function(){
        $('#datepicker').val("").datepicker("update");
    })
});