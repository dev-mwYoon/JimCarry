/* 정규식 */
const specialCharacterRegex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
const nameRegex = /^[가-힣|a-z|A-Z|]+$/;
const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
const idRegex = /^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,}$/;
const passwordNumberRegex = /[0-9]/g;
const passwordEnglishRegex = /[a-z]/ig;
const passwordSpecialCharacterRegex = /[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi;
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let blurMessages = [
    "올바른 아이디 형식이 아닙니다.", "현재 비밀번호를 입력하세요.", "올바른 비밀번호 형식이 아닙니다.",
    "동일한 비밀번호를 입력해주세요.", "이메일을 입력하세요.", "휴대폰 번호를 입력하세요."
];
let regexMessages = [
    "현재 아이디를 입력해주세요.", "현재 비밀번호를 입력하세요.",
    "숫자, 영문, 특수문자를 조합하여 8~15자 이내로 입력해주세요.", "동일한 비밀번호를 입력해주세요",
    "이메일 주소를 확인해주세요.", "휴대폰 번호를 확인하세요."];
//아이디, 비밀번호, 새비밀번호, 비밀번호 확인, 이름, 이메일, 휴대폰

const $wrapperInputs = $('input[type=text], input[type=password]');
console.log($wrapperInputs);
const $errorMessage = $('div.errorDiv p.errorMessage');
let errorCheck;
let errorCheckAll = [true, false, false, false, true, true];
const $errorDiv = $('div.errorDiv');
$wrapperInputs.on("blur", function () {
    let i = $wrapperInputs.index($(this));
    let value = $(this).val();
    console.log(value);

    if (!value) {
        $errorDiv.eq(i).css("display", "block");
        $errorDiv.eq(i).css("color", "red");
        $errorMessage.eq(i).text(blurMessages[i]);
        errorCheck = false;
        errorCheckAll[i] = errorCheck;
        return;
    } else {
        $errorDiv.eq(i).css("display", "none");
    }
    switch (i) {
        case 0: // 아이디
            errorCheck = value.length > 3 && value.length < 21 && idRegex.test(value) && !specialCharacterRegex.test(value);
            /* Ajax 통신 */
            if (errorCheck) {
                doAjax(checkIdentificationAjaxConfig(value), (result) => {
                    if (result == true) {
                        $errorDiv.eq(i).css("display", "block");
                        $errorDiv.eq(i).css("color", "green");
                        $errorMessage.eq(i).text(ajaxSuccessMsgs.identification);
                    } else {
                        $errorDiv.eq(i).css("display", "block");
                        $errorDiv.eq(i).css("color", "red");
                        $errorMessage.eq(i).text(ajaxErrorMsgs.identification);
                    }
                });
            }
            break;
        case 1: // 현재 비밀번호

            // 로그인상태로 수정하는것이기 때문에 DB의 비밀번호랑 비교 해야 될것 같음
            let numberCheck = value.search(passwordNumberRegex);
            let englishCheck = value.search(passwordEnglishRegex);
            let specialCharacterCheck = value.search(passwordSpecialCharacterRegex);

            var condition1 = (numberCheck >= 0 && englishCheck >= 0) && (englishCheck >= 0 && specialCharacterCheck >= 0) && (specialCharacterCheck >= 0 && numberCheck >= 0)
            var condition2 = value.length > 9 && value.length < 21;
            var condition3 = value.search(/\s/) < 0;

            // errorCheck = condition1 && condition2 && condition3;
            errorCheck = true;

            if (errorCheck) {
                doAjax(checkPasswordAjaxConfig(value), (result) => {
                    if (result == true) {
                        $errorDiv.eq(i).css("display", "none");
                        $errorMessage.eq(i).text(ajaxSuccessMsgs.password);
                    } else {
                        $errorDiv.eq(i).css("display", "block");
                        $errorDiv.eq(i).css("color", "red");
                        $errorMessage.eq(i).text(ajaxErrorMsgs.password);
                    }
                });
            }

            break;
        case 2: //새 비밀번호
            let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,15}$/;
            errorCheck = reg.test(value);
            break;
        case 3: // 새 비밀번호 확인
            errorCheck = $wrapperInputs.eq(i - 1).val() == value;
            break;
        case 4: // 이메일
            errorCheck = emailRegex.test(value);
            if (errorCheck) {
                doAjax(checkEmailAjaxConfig(value), (result) => {
                    if (result == true) {
                        $errorDiv.eq(i).css("display", "block");
                        $errorDiv.eq(i).css("color", "green");
                        $errorMessage.eq(i).text(ajaxSuccessMsgs.email);
                    } else {
                        $errorDiv.eq(i).css("display", "block");
                        $errorDiv.eq(i).css("color", "red");
                        $errorMessage.eq(i).text(ajaxErrorMsgs.email);
                    }
                })
            }
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
        $errorDiv.eq(i).css("color", "red");
        $errorMessage.eq(i).text(regexMessages[i]);
        return;
    } else {
        $errorDiv.eq(i).css("display", "none");
    }
    $errorMessage.eq(i).text("");

});

/* 휴대폰 다른번호 인증 모달---------------- */
// const phonebtn = document.querySelector(".phoneBtn");
// const phonecontainer = document.querySelector(".phonemodal");
// const phoneclose = document.querySelector(".phoneclose");
//
// //모달창 열기
// phonebtn.addEventListener("click", function () {
//     phonecontainer.style.display = "block";
// });
//
// //모달창 닫기
// phoneclose.addEventListener("click", function () {
//     phonecontainer.style.display = "none";
// });


/* 회원정보 수정 모달----------------------- */
const $infobtn = $("button[type='button']");
const $infocontainer = $(".infomodal");
const $infoclose = $(".infocheck");

console.log($infobtn, $infocontainer, $infoclose);

//모달창 열기
$infobtn.on("click", function (e) {
    e.preventDefault();
    let check = 0;
    errorCheckAll.forEach(e => check += e ? 0 : 1);
    console.log(errorCheckAll);
    console.log(check);

    if (check == 0) {
        $(".pay-content").text("회원정보가 수정되었습니다.");
        $infocontainer.css("display", "block");
        setTimeout(() => $("form[name='userForm']").submit(), 1500);
    } else {
        $(".pay-content").text("입력하지 않은 사항이 있습니다.");
        $infocontainer.css("display", "block");
    }
});

//모달창 닫기
$infoclose.on("click", function () {
    $infocontainer.css("display", "none");
});


// 동의 버튼 효과
const $checkboxes = $('.termCheckBox');
const $path = $('.path1');
const $all = $('.allCheck');
const $checks = $('.checked');
$checkboxes.each((i, e) => {
    $(e).parent().on('click', function () {
        var $ischecked = $(e).is(':checked');
        if ($ischecked) {
            // $path.eq(i).attr('fill', '#5f0080');
            $path.eq(i).attr('fill', '#fff');
            $(e).prop('checked', false);
        } else {
            // $path.eq(i).attr('fill', '#fff');
            $path.eq(i).attr('fill', '#5f0080');
            $(e).prop('checked', true);

        }
    });
});
// 전체동의 버튼 효과
$all.on("click", function () {
    let $checked = $('.RequiredTermCondition').prop("checked");
    console.log($checked);
    if ($checked) {
        $path.attr('fill', '#fff');
        $('#TermsAgreeAll').prop('checked', false);
        $checks.children().prop('checked', false);
    } else {
        $path.attr('fill', '#5f0080');
        $('#TermsAgreeAll').prop('checked', true);
        $checks.children().prop('checked', true);

    }
});
// 동의 버튼 전체 확인 시 전체동의도 확인 효과, 필수사항 동의 시 submit 버튼 활성화
$checks.on('click', function () {
    var agreeCheck = [false, false];

    if ($checks.children().filter(":checked").length == 2) {

        $('.allPath').attr('fill', '#5f0080');
        $('#TermsAgreeAll').prop('checked', false);
    } else {

        $('.allPath').attr('fill', '#fff');
        $('#TermsAgreeAll').prop('checked', true);
    }
});


/* 성별 버튼 */
function setGenderValue(i) {
    switch (i) {
        case 0:
            $("input[name='userGender']").val("남");
            break;
        case 1:
            $("input[name='userGender']").val("여");
            break;
        default:
            $("input[name='userGender']").val("");
    }
}

$("div.sizeSelect").on("click", function () {
    let i = $("div.sizeSelect").index($(this));
    $("span.checkBox").eq(i).addClass('radioSpanClick');
    $("span.checkBox")
        .not($("span.checkBox").eq(i)).removeClass('radioSpanClick');
    $('div.checkBox2').eq(i).addClass('radioBoxDivClick');
    $('div.checkBox2')
        .not($('div.checkBox2').eq(i)).removeClass('radioBoxDivClick');
    setGenderValue(i);
});

$(".amend-birth-input-text").eq(2).on("blur", function () {
    let $date = $(".amend-birth-input-text");
    let format = `${$date.eq(0).val()}/${$date.eq(1).val()}/${$date.eq(2).val()}`
    console.log(format);
    $("input#userBirth").val(format);
})

