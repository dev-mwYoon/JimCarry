const $modal = $('.modal-container');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $loginButton = $('.loginButton');

$loginButton.on('click', ()=>{
    if(!$($('.idPwInput')[0]).val() || !$($('.idPwInput')[1]).val()) {
        $modal.css("visibility", "visible");
        return;
    }
    console.log('섭밋')
});
$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});