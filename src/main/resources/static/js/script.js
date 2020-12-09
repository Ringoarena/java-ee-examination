$(document).ready(function () {
    $('.sidenav').sidenav();
    $('.select-category').formSelect();
})

function clearFilter() {
    window.location = '/products';
}