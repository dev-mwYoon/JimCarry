let blurMsg = ["가입 시 등록한 이름을 입력하세요", "가입 시 등록한 이메일을 입력해 주세요."];
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

const $findnameInputBox = $('.findnameInputBox');
const $findEmailInputBox = $('.findEmailInputBox');
const $errorMsg1 = $('.errorMsg1');
const $errorMsg2 = $('.errorMsg2');


console.log($('.codeButton'));
$findnameInputBox.on('blur', function(){
    let value = $(this).val();
    console.log(value);

    if(!value){
        $errorMsg1.css('display', 'block');
        $errorMsg1.text("가입 시 등록한 이름을 입력하세요.");
    }else{
        $errorMsg1.css('display', 'none');
        $errorMsg1.text("");
    }
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
        
    }
});
$('.codeButton').css('background-color', '#5f0080');
// if($findEmailInputBox.val()){
//     $('.codeButton').css('background-color', '#5f0080');
// }


//모달
const $modal = $('.modal-container');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $codeButton = $('.codeButton');

$codeButton.on('click', ()=>{
    $modal.css("visibility", "visible");
});
$checkButton.on("click", ()=>{
    $modal.css("visibility", "hidden");
});