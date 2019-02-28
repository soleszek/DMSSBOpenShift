function moveToTrash() {
    // get all checked checkboxes
    var checkboxes = document.querySelectorAll("input:checked");

    if (checkboxes.length === 0) { // if there are no checked checkboxes
        // display info
        alert('Please select any message');
        return false; // don't submit
    } else {
        if(confirm("Selected messages will be moved to trash")) {
            document.getElementById('myForm').submit();
            return true; // submit
        } else {
            return false;
        }
    }
}