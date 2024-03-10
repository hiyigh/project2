const baseUrl = window.location.pathname;
let id = baseUrl.replace("/item/edit/", "");

document.ready(function(){
    load();
});
function load(){
    $.ajax({
        type:'get',
        url:'shop/api/detail/' + id;
        success:function(response) {
            loadData(response);
        },
        error:function(error){
            alert(error.responseText);
            window.location.replace("item/list");
        }
    });
}

function loadData(data) {
    let item = data.item;
    let itemFile = data.itemFile;
    document.querySelector("#title").value = item.title;
    document.querySelectorAll("#category option").forEach(function(option){
        if(option.innerHTML == item.category){
            option.selected =true;
        }
    });
    document.querySelector("#explain").value = item.explain;

}

function editFormSubmit() {

}