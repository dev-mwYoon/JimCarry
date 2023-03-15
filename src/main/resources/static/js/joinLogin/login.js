const $modal = $('.modal-container');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $loginButton = $('.loginButton');

$loginButton.on('click', ()=>{
    $modal.css("visibility", "visible");
});
$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});