/* 정규식 */
const specialCharacterRegex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
const nameRegex = /^[가-힣|a-z|A-Z|]+$/;
const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
const idRegex = /^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,}$/;
const passwordNumberRegex = /[0-9]/g;
const passwordEnglishRegex = /[a-z]/ig;
const passwordSpecialCharacterRegex = /[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi;
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let blurMessages = ["아이디를 입력하세요.", "현재 비밀번호를 입력하세요.", "새 비밀번호를 입력하세요.", "세 비밀번호 확인을 위해 한번 더 입력하세요.", "이름을 입력하세요.", "이메일을 입력하세요.", "휴대폰 번호를 입력하세요."];
let regexMessages = ["영문 혹은 영문과 숫자를 조합하여 4자~20자로 입력해주세요.", "공백 제외 영어 및 숫자, 특수문자 모두 포함하여 10~20자로 입력해주세요.", "위 비밀번호와 일치하지 않습니다. 다시 입력해주세요.", "영문 혹은 한글로 2자~20자로 입력해주세요.", "이메일 주소를 확인해주세요.", "휴대폰 번호를 확인하세요."];

const $wrapperInputs = $('.wrapper input[type=text], input[type=password]');
console.log($wrapperInputs);
const $errorMessage = $('div.errorDiv p.errorMessage');
let errorCheck;
let errorCheckAll = [false, false, false, false, false, false, false];
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
        case 2: //새 비밀번호
            
            break;
        case 3: // 새 비밀번호 확인
            errorCheck = $wrapperInputs.eq(i - 1).val() == value;
            break;
        case 4: // 이름
            errorCheck = value.length > 1 && value.length < 21 && nameRegex.test(value) && !specialCharacterRegex.test(value);
            break;
        case 5: // 이메일
            errorCheck = emailRegex.test(value);
            break;
        case 6: // 휴대폰
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