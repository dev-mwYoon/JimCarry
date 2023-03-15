const idRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$/;
const duplicateString = /([a-zA-Z])(?=(?:.*?\1){2})/;
const $errorMsg = $('.errorMsg');
const $inputs = $('.inputs');
console.log($inputs);

const $newPwInput = $('newPwInput');

$inputs.on('blur', function(){
    let value = $($inputs[0]).val();
    let errorCheck = idRegex.test(value);
   if(!errorCheck){
    $errorMsg[1].attr('.checkMsg');
   }else{
    $errorMsg[1].attr('.errorkMsg');
   }
});

/* 모달 */
const $modal = $('.modal-center');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $checkBt = $('.checkButton');

$checkButton.on('click', ()=>{
    $modal.css('visibility', 'hidden');
});
$checkBt.on('click', function(){
    
    $modal.css('visibility', 'visible');
    
});

