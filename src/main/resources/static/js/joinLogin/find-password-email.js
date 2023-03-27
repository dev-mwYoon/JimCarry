let blurMsg = ["가입 시 등록한 이름을 입력하세요", "가입 시 등록한 이메일을 입력해 주세요."];
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
const idRegex = /^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,}$/;
const $findIdInputBox = $('.findIdInputBox');
const $findEmailInputBox = $('.findEmailInputBox');
const $errorMsg1 = $('.errorMsg1');
const $errorMsg2 = $('.errorMsg2');

$findIdInputBox.on('blur', function(){
    let value = $(this).val();
    let errorCheck = idRegex.test(value);
    console.log(value);

    if(!value){
        $errorMsg1.css('display', 'block');
        $errorMsg1.text("가입 시 등록한 아이디를 입력하세요.");
    }else if(!errorCheck){
        $errorMsg1.text("아이디를 확인해주세요.")
        $errorMsg1.css('display', 'block');
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

$findEmailInputBox.on('blur', function(){
    let value = $(this).val();
    let errorCheck = emailRegex.test(value);

    if(!value){
        $errorMsg2.css('display', 'block');
        $errorMsg2.text("가입 시 등록한 이메일을 입력해 주세요.");
    }else if(!errorCheck){
        $errorMsg2.text("이메일 형식을 확인해주세요.")
        $errorMsg2.css('display', 'block');
    }else{
        $errorMsg2.css('display', 'none');
        $errorMsg2.text("");
        if($errorMsg1.css('display') == 'none') {
            $('.codeButton').attr('disabled', false);
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

// $codeButton.on('click', ()=>{
//     $modal.css("visibility", "visible");
// });
// $checkButton.on("click", ()=>{
//     $modal.css("visibility", "hidden");
// });

if(window.location.search) {
    $modalText.text('존재하지 않는 회원입니다.');
    $modal.css("visibility", "visible");
}

$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});