function edit(){
    document.getElementById("editButton").style.visibility="hidden";
    document.getElementById("saveButton").style.visibility="visible";
    document.getElementById("cancelButton").style.visibility="visible";
    var elements = document.getElementsByClassName("edit-text");

    for (i=0; i<elements.length; i++){
        elements[i].readOnly = false;
        elements[i].style.border = "thin dotted red";
    }
}

function save(){
    document.getElementById("editButton").style.visibility="visible";
    document.getElementById("saveButton").style.visibility="hidden";
    document.getElementById("cancelButton").style.visibility="hidden";
    var elements = document.getElementsByClassName("edit-text");

    for(i=0; i<elements.length; i++){
        elements[i].readOnly = true;
        elements[i].style.border = "none";
    }
}

function cancel(){
    document.getElementById("editButton").style.visibility="visible";
    document.getElementById("saveButton").style.visibility="hidden";
    document.getElementById("cancelButton").style.visibility="hidden";
    var elements = document.getElementsByClassName("edit-text");

    for(i=0; i<elements.length; i++){
        elements[i].readOnly = true;
        elements[i].style.border = "none";
    }
}

var form = document.getElementById("edit-form");

document.getElementById("saveButton").addEventListener("click", function () {
    form.submit();
});