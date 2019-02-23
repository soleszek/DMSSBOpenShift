function pobierz(val){
document.getElementById("wynik").value+=val;
}

function oblicz(){
var x = document.getElementById("wynik").value;
var y = eval(x);
document.getElementById("wynik").value = y;
}

function czysc(){
document.getElementById("wynik").value = "";
}