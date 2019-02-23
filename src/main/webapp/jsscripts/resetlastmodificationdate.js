$(document).ready(function () {
    $("#reset-lastmodificationdate").click(function(){
        $('#datepicker').val("").datepicker("update");
    })
});