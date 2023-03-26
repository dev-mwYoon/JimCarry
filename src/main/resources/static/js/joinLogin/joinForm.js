
/* 사이즈 라디오 버튼 클릭했을때 */
function clickRadio() {
    /* size의 모든값 조회 */
    const $sizes = $('.genderRadio');
    const $count = $sizes.length;
    const $checkboxes = $('span.checkBox');
    const $checkboxes2 = $('div.checkBox2');

    for (let i = 0; i < $count; i++) {
        if ($sizes[i].checked) {
            $checkboxes[i].classList.add('radioSpanClick');
            $checkboxes2[i].classList.add('radioBoxDivClick');
        } else {
            $checkboxes[i].classList.remove('radioSpanClick');
            $checkboxes2[i].classList.remove('radioBoxDivClick');
        }
    }
}


/* 정규식 */
//아이디, 비밀번호, 비밀번호 확인, 이름, 이메일, 휴대폰

const specialCharacterRegex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
const nameRegex = /^[가-힣|a-z|A-Z|]+$/;
const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
const idRegex = /^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,}$/;
const passwordNumberRegex = /[0-9]/g;
const passwordEnglishRegex = /[a-z]/ig;
const passwordSpecialCharacterRegex = /[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi;
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let blurMessages = ["아이디를 입력하세요.", "비밀번호를 입력하세요.", "비밀번호 확인을 위해 한번 더 입력하세요.", "이름을 입력하세요.", "이메일을 입력하세요.", "휴대폰 번호를 입력하세요."];
let regexMessages = ["영문 혹은 영문과 숫자를 조합하여 4자~20자로 입력해주세요.", "공백 제외 영어 및 숫자, 특수문자 모두 포함하여 10~20자로 입력해주세요.", "위 비밀번호와 일치하지 않습니다. 다시 입력해주세요.", "영문 혹은 한글로 2자~20자로 입력해주세요.", "이메일 주소를 확인해주세요.", "휴대폰 번호를 확인하세요."];

const $wrapperInputs = $('.wrapper input[type=text], input[type=password]');
const $errorMessage = $('div.errorDiv p.errorMessage');
let errorCheck;
let errorCheckAll = [false, false, false, false, false, false];
const $errorDiv = $('div.errorDiv');
$wrapperInputs.on("blur", function () {
    let i = $wrapperInputs.index($(this));
    let value = $(this).val();

    if (!value) {
        $errorDiv.eq(i).css("display", "block");
        $errorMessage.eq(i).text(blurMessages[i]);
        errorCheck = false;
        errorCheckAll[i] = errorCheck;
        return;
    }else{
        $errorDiv.eq(i).css("display", "none");
    }
    switch (i) {
        case 0: // 아이디
            errorCheck = value.length > 3 && value.length < 21 && idRegex.test(value) && !specialCharacterRegex.test(value);
            break;
        case 1: // 비밀번호
            let numberCheck = value.search(passwordNumberRegex);
            let englishCheck = value.search(passwordEnglishRegex);
            let specialCharacterCheck = value.search(passwordSpecialCharacterRegex);

            var condition1 = (numberCheck >= 0 && englishCheck >= 0) && (englishCheck >= 0 && specialCharacterCheck >= 0) && (specialCharacterCheck >= 0 && numberCheck >= 0)
            var condition2 = value.length > 9 && value.length < 21;
            var condition3 = value.search(/\s/) < 0;

            errorCheck = condition1 && condition2 && condition3;
            break;
        case 2: // 비밀번호 확인
            errorCheck = $wrapperInputs.eq(i - 1).val() == value;
            break;
        case 3: // 이름
            errorCheck = value.length > 1 && value.length < 21 && nameRegex.test(value) && !specialCharacterRegex.test(value);
            break;
        case 4: // 이메일
            errorCheck = emailRegex.test(value);
            break;
        case 5: // 휴대폰
            errorCheck = phoneRegex.test(value);
            if (errorCheck) {
                $(this).val(value.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
            }
            break;
    }
    errorCheckAll[i] = errorCheck;

    if (!errorCheck) {
        $errorDiv.eq(i).css("display", "block");
        $errorMessage.eq(i).text(regexMessages[i]);
        return;
    }else{
        $errorDiv.eq(i).css("display", "none");
    }
    $errorMessage.eq(i).text("");
    
});

//생년월일 정규식
// let yearRegex = /^(19[0-9][0-9]|20\d{2})$/;
// let monthRegex = /^(0[0-9]|1[0-2])$/;
// let dayRegex = /^(0[1-9]|[1-2][0-9]|3[0-1])$/;

// 주소 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.querySelector('#numberAddress').value = data.address; // 주소 넣기
            document.querySelector('#numberAddress').focus();
        },
    }).open();
}


const $checkboxes = $('.termCheckBox');
const $path = $('.path1');
const $all = $('.allCheck');
const $checks = $('.checked');
// 동의 버튼 효과
$checkboxes.each((i,e)=>{
    $(e).parent().on('click', function(){
        var $ischecked = $(e).is(':checked');
        if($ischecked){
            // $path.eq(i).attr('fill', '#5f0080');
            $path.eq(i).attr('fill', '#fff');
            $(e).prop('checked', false);
        }else{
            // $path.eq(i).attr('fill', '#fff');
            $path.eq(i).attr('fill', '#5f0080');
            $(e).prop('checked', true);
            
        }
    });
});
// 전체동의 버튼 효과
$all.on("click", function(){
    var $checked = $('#TermsAgreeAll').is(':checked');
    if($checked) {
        $path.attr('fill', '#fff');
        $('#TermsAgreeAll').prop('checked', false);
        $checks.children().prop('checked', false);
    } else {
        $path.attr('fill', '#5f0080');
        $checks.children().prop('checked', true);
        $('#TermsAgreeAll').prop('checked', true);
    }
});

// 동의 버튼 전체 확인 시 전체동의도 확인 효과, 필수사항 동의 시 submit 버튼 활성화
$checks.on('click', function(){
    var agreeCheck = [false, false, false, false];

    if($checks.children().filter(":checked").length == 4) {
        $('.allPath').attr('fill', '#5f0080');
        $('#TermsAgreeAll').prop('checked', false);
    } else {

        $('.allPath').attr('fill', '#fff');
        $('#TermsAgreeAll').prop('checked', true);
    }
});



//모달
const $modal = $('.modal-container');
const $modalText = $('.popup-content');
const $checkButton = $('.popup-check');

const $duplicateIdButton = $('.idDuplicate');
const $duplicateEmailButton = $('.emailDuplicate');

$duplicateEmailButton.on('click', function(){
    $modal.css('visibility', 'visible');
    let valueEmail = $('.emailInput').val();
    let errorCheck = emailRegex.test(valueEmail);
    if(!valueEmail){
        $modalText.text("이메일주소를 입력하세요.")
    }else if(!errorCheck){
        $modalText.text("이메일 형식을 확인해주세요.")
    }else{
        $.ajax({
            url: "/user/checkEmailDuplicate",
            type: "post",
            data: { userEmail : valueEmail },
            success: function(result) {
                if(result) {
                    $modalText.text("사용 가능 합니다.");
                    $($('.duplicateBox')[1]).attr('disabled', true);
                    $('.emailInput').attr('readonly', true);
                } else {
                    $modalText.text("사용 불가능 합니다.");
                    $('.emailInput').val('');
                }
            }
        });
    }
    
    $checkButton.on('click',()=>{
        $modal.css('visibility', 'hidden');
    });

});
$duplicateIdButton.on('click', function(){
    $modal.css('visibility', 'visible');
    let valueId = $('.idInput').val();
    let errorCheck = valueId.length > 3 && valueId.length < 21 && idRegex.test(valueId) && !specialCharacterRegex.test(valueId);
    if(!valueId){
        $modalText.text("아이디를 입력해주세요.");
    }else if(!errorCheck){
        $modalText.text("영문 혹은 영문과 숫자를 조합하여 4자~20자로 입력해주세요.");
    }else{
        $.ajax({
            url: "/user/checkIdentificationDuplicate",
            type: "post",
            data: { userIdentification : valueId },
            success: function(result) {
                if(result) {
                    $modalText.text("사용 가능 합니다.");
                    $($('.duplicateBox')[0]).attr('disabled', true);
                    $('.idInput').attr('readonly', true);
                } else {
                    $modalText.text("사용 불가능 합니다.");
                    $('.idInput').val('');
                }
            }
        });
    }
    
    $checkButton.on('click',()=>{
        $modal.css('visibility', 'hidden');
    });

});

$checkWrapper = $('#checkWrapper');
$checkNum = $('#checkNum');

$submitButton = $('.submitButton');
$submitButton.on('click', function(event) {
    event.preventDefault();

    for (let i = 0; i < $('.necessary').length; i++) {
        if(!$($('.necessary')[i]).val()) {
            $modal.css('visibility', 'visible');
            $modalText.text("필수입력사항을 모두 입력해주세요.");
            $checkButton.on('click',()=>{
                $modal.css('visibility', 'hidden');
            });
            return;
        }
    }

    if(!$($('.duplicateBox')[0]).attr('disabled')) {
        $modal.css('visibility', 'visible');
        $modalText.text("아이디 중복확인을 해주세요.");
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
        });
        return;
    }
    if(!$($('.duplicateBox')[1]).attr('disabled')) {
        $modal.css('visibility', 'visible');
        $modalText.text("이메일 중복확인을 해주세요.");
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
        });
        return;
    }
    if(!($('#duplicateBox').text() == '확인')) {
        $modal.css('visibility', 'visible');
        $modalText.text("인증번호를 확인해주세요.");
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
        });
        return;
    }

    if(!($($('.test')[0]).is(':checked')) || !($($('.test')[1]).is(':checked')) || !($($('.test')[3]).is(':checked'))) {
        $modal.css('visibility', 'visible');
        $modalText.text("필수 이용약관을 모두 선택 해주세요.");
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
        });
        return;
    }
    // console.log('서브밋');
    joinForm.submit();
});

var authNumber = null;

$checkNum.on('click', function(){
    if($('.errorDiv').eq(5).css('display') == 'none' && $('input[name=userPhone]').val()) {
        clearInterval(timer);

        $.ajax({
            url: "/user/sendSMS",
            type: "get",
            data: { userPhone : $('input[name=userPhone]').val() },
            success: function(result) {
                console.log(result);
                authNumber = result;
                $modal.css('visibility', 'visible');
                $modalText.text("인증번호가 전송되었습니다.");
                $checkWrapper.css("display", "flex");
                $('#duplicateBox').attr('disabled', false);
                $checkButton.on('click',()=>{
                    $modal.css('visibility', 'hidden');
                });
            }
        });

        var display = $(".checknum2");
        // 유효시간 설정
        var leftSec = 180;

        startTimer(leftSec, display);
    }
});
$duplicateBox = $('#duplicateBox');
$duplicateBox.on('click', function(){
    if($('input[name=authCode]').val() == authNumber) {
        $modal.css('visibility', 'visible');
        $modalText.text("인증확인이 완료되었습니다.");
        $('#duplicateBox').attr('disabled', true);
        $('#checkNum').attr('disabled', true);
        $('#duplicateBox').text('확인');
        clearInterval(timer);
        $checkButton.on('click',()=>{
            $modal.css('visibility', 'hidden');
        });
    } else {
        $modal.css('visibility', 'visible');
        $modalText.text("인증번호가 일치하지 않습니다.");
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
            $('#duplicateBox').attr('disabled', true);
        }
    }, 1000);
}
