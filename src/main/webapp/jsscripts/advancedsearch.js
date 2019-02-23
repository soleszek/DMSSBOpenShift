$(document).ready(function () {
    $('.form-div1, .form-div2, .form-div3, .form-div4').hide();

    $('#object').change(function () {
        var selected = $('#object option:selected').text();
        $('.form-div1').toggle(selected == "Documents");

        $('.form-div2').toggle(selected == "Routes");

        $('.form-div3').toggle(selected == "Tasks");

        $('.form-div4').toggle(selected == "Users");
    });
});