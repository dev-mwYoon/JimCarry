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

    // $.ajax({
    //     url: "/user/login",
    //     type: "post",
    //     data: { userIdentification : $userIdentification, userPassword : $userPassword },
    //     success: function(result) {
    //         if(result) {
    //             $modalText.text("사용 가능 합니다.");
    //             $($('.duplicateBox')[1]).attr('disabled', true);
    //             $('.emailInput').attr('readonly', true);
    //         } else {
    //             $modalText.text("사용 불가능 합니다.");
    //             $('.emailInput').val('');
    //         }
    //     }
    // });
    loginForm.submit();
});
$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});
