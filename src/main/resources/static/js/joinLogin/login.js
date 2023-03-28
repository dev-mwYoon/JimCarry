const $modal = $('.modal-container');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $loginButton = $('.loginButton');

if(window.location.search) {
    $modalText.text('아이디, 비밀번호를 확인해주세요.');
    $modal.css("visibility", "visible");
}

$loginButton.on('click', ()=>{
    const $userIdentification = $('.idPwInput').eq(0).val();
    const $userPassword = $('.idPwInput').eq(1).val();

    if(!$userIdentification || !$userPassword) {
        $modal.css("visibility", "visible");
        return;
    }
    document.loginForm.submit();
});
$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});
