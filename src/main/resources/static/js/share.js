// move btn
function moveToTop() {
    document.body.scrollIntoView({behavior:"smooth"});
}
// search
$('#search-form').submit(function(event){
    event.preventDefault();

    let searchType = $('#search-type').val();
    let keyword = $('#search-input').val();

    window.location.href="/main/search?searchType=" + searchType + "&keyword=" + keyword;
});
// dropdown content menu
$('#nav-projects').on("click", function(){
    let dropdown = $('#nav-dropdown-content-box');
    if (dropdown.css('display') === 'block') {
        dropdown.css('display','none');
    } else {
        dropdown.css('display','block');
    }
});

//dropdown user menu
$('#nav-user-image').click(function(){
    let dropdown = $('#nav-dropdown-login-box');
    if (dropdown.css('display') === 'block') {
        dropdown.css('display','none');
    } else {
        dropdown.css('display','block');
    }
});

// login modal
function openModal() {
    $('.modal').css('display','block');
}
function closeModal() {
    $('.modal').css('display','none');
}