const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
const $findnameInputBox = $('.findnameInputBox');
const $findPhoneInputBox = $('.findPhoneInputBox');
const $errorMsg1 = $('.errorMsg1');
const $errorMsg2 = $('.errorMsg2');
const $verificationError = $('.verificationError');
const $verificationInput = $('.verification-input');
const $verifiCheck = $('.verifiCheck');

$findnameInputBox.on('blur', function(){
    let value = $(this).val();
    console.log(value);

    if(!value){
        $errorMsg1.css('display', 'block');
        $errorMsg1.text("가입 시 등록한 이름을 입력하세요.");
    }else{
        $errorMsg1.css('display', 'none');
        $errorMsg1.text("");
        if($errorMsg2.css('display') == 'none') {
            $('.codeButton').attr('disabled', false);
        }
        return;
    }
    $('.codeButton').attr('disabled', true);
});
$findPhoneInputBox.on('blur', function(){
    let value = $(this).val();
    let errorCheck = phoneRegex.test(value);

    if(!value){
        $errorMsg2.css('display', 'block');
        $errorMsg2.text("가입 시 등록한 휴대폰번호을 입력해 주세요.");
    }else if(!errorCheck){
        $errorMsg2.text("휴대폰 번호를 정확히 입력해 주세요.")
        $errorMsg2.css('display', 'block');
    }else{
        $errorMsg2.css('display', 'none');
        $errorMsg2.text("");
        if($errorMsg1.css('display') == 'none') {
            $('.codeButton').attr('disabled', false);
        }
        if (errorCheck) {
            $(this).val(value.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
        }
        return;
    }
    $('.codeButton').attr('disabled', true);
});
//모달
const $modal = $('.modal-container');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');
const $codeButton = $('.codeButton');
const $verification = $('.verification');
const $verificationBtn = $('.verification-button');

if(window.location.search) {
    $modalText.text('존재하지 않는 회원입니다.');
    $modal.css("visibility", "visible");
}

$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});

$codeButton.on('click', function(){
    $verification.css("display", "flex");
    $codeButton.css("display", 'none');
    $verifiCheck.css("display", "block");
    $modalText.text("인증번호가 발송되었습니다. 3분안에 인증번호를 입력하세요.");
    $modal.css('visibility', 'visible');

});
$checkButton.on("click", function(){
    $modal.css("visibility", "hidden");
});

$verificationBtn.on('click', function(){
    $modal.css('visibility', 'visible');
    $modalText.text("인증번호가 발송되었습니다. 3분안에 인증번호를 입력하세요.")
});

$verificationInput.on('blur', function(){
    let value = $(this).val();

    if(!value){
        $verificationError.css("display", "block")
        $verificationError.text("인증번호를 입력해주세요.");
    }else if(value.length < 6){
        $verificationError.css("display", "block")
        $verificationError.text("6자리를 입력해주세요.");
    }else{
        $verificationError.css("display", "none")
    }

});
// $verifiCheck.on('click', function(){
//     $modalText.text("인증번호가 일치 하지 않습니다.");
//     // $modalText.text("유효 시간이 만료되었습니다.\n재발송 후 다시 시도해주세요");
//     $modal.css('visibility', 'visible');
// });



var authNumber = null;

$('.send-sms').on('click', function(){
    $verifiCheckBtn.attr('disabled', false);
    clearInterval(timer);

    // $.ajax({
    //     url: "/user/send-sms",
    //     type: "get",
    //     data: { userPhone : $('input[name=userPhone]').val() },
    //     success: function(result) {
    //         console.log(result);
    //         authNumber = result;
    //     }
    // });
    joinService.sendSMS(function(result) {
        console.log(result);
        authNumber = result;
    });

    var display = $(".verification-timer");
    // 유효시간 설정
    var leftSec = 180;

    startTimer(leftSec, display);
});
$verifiCheckBtn = $('.verifiCheckBtn');
$verifiCheckBtn.on('click', function(event){
    event.preventDefault();
    if($('input[name=verificationNumber]').val() == authNumber) {
        $modal.css('visibility', 'visible');
        $modalText.text("인증확인이 완료되었습니다.");
        clearInterval(timer);
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
            findIdPhoneForm.submit();
        });
    } else {
        $modal.css('visibility', 'visible');
        $modalText.text("인증번호가 틀렸습니다.");
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
        });
    }
});

var timer = null;
function startTimer(count, display) {
    var minutes, seconds;
    timer = setInterval(function () {
        minutes = parseInt(count / 60, 10);
        seconds = parseInt(count % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.html(minutes + ":" + seconds);

        // 타이머 끝
        if (--count < 0) {
            clearInterval(timer);
            display.html("00:00");
            $modal.css('visibility', 'visible');
            $modalText.text("유효시간이 만료되었습니다.\n다시 시도해주세요.");
            $verifiCheckBtn.attr('disabled', true);
        }
    }, 1000);
}