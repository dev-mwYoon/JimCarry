const passwordNumberRegex = /[0-9]/g;
const passwordEnglishRegex = /[a-z]/ig;
const passwordSpecialCharacterRegex = /[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi;

const duplicateString = /([a-zA-Z])(?=(?:.*?\1){2})/;
const $errorMsg = $('.errorMsg');
const $inputs = $('.newPwInput');
console.log($inputs);

const $newPwInput = $(".newPwInput");
const $duplicatePw = $('.duplicatePw');
const $errorMsg1 = $('.errorMsg1');
const $errorMsg2 = $('.errorMsg2');

$newPwInput.on('blur', function(){
    let value = $(this).val();
    let numberCheck = value.search(passwordNumberRegex);
    let englishCheck = value.search(passwordEnglishRegex);
    let specialCharacterCheck = value.search(passwordSpecialCharacterRegex);
    var condition1 = (numberCheck >= 0 && englishCheck >= 0) && (englishCheck >= 0 && specialCharacterCheck >= 0) && (specialCharacterCheck >= 0 && numberCheck >= 0)
    var condition2 = value.length > 9 && value.length < 21;
    var condition3 = value.search(/\s/) < 0;

    let errorCheck = condition1 && condition2 && condition3;
    
    if(!value){
        $errorMsg1.css('display','block');
        $errorMsg1.text("변경하실 비밀번호를 입력해주세요.")
    }else if(!errorCheck){
        $errorMsg1.css('display','block');
        $errorMsg1.text("공백 제외 영어 및 숫자, 특수문자 모두 포함하여 10~20자로 입력해주세요.");
    }else {
        $errorMsg1.text("");
        $errorMsg1.css('display','none');
    }
    
});

var password = document.getElementById("password");
var passwordConfirm = document.getElementById("passwordConfirm");

function validatePassword(){
    if(password.value != passwordConfirm.value){
        $errorMsg2.css('display','block');
        $errorMsg2.text("비밀번호가 일치하지 않습니다.");
    }else{
        $errorMsg2.css('display','none');
        $errorMsg2.text("");
    }
}
password.onchange = validatePassword;
passwordConfirm.onkeyup = validatePassword;


/* 모달 */
const $modal = $('.modal-center');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $checkBt = $('.checkButton');

$checkButton.on('click', ()=>{
    $modal.css('visibility', 'hidden');
    passwordForm.submit();
});
$checkBt.on('click', function(event){
    event.preventDefault();
    $modal.css('visibility', 'visible');
});




