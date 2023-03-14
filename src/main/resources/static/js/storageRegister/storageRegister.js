// 주소 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.querySelector('#address').value = data.address; // 주소 넣기
            document.querySelector('input[name=addressDetail]').focus(); //상세입력 포커싱
        },
    }).open();
}

/* 사이즈 라디오 버튼 클릭했을때 */
function clickRadio() {

    /* size의 모든값 조회 */
    const $sizes = $('input[name=size]');

    const $count = $sizes.length;

    const $checkboxes = $('span.checkBox');
    const $checkboxes2 = $('div.checkBox2');

    for (let i = 0; i < $count; i++) {
        if ($sizes[i].checked) {
            console.log('들어옴');
            console.log($sizes[i]);
            $checkboxes[i].classList.add('radioSpanClick');
            $checkboxes2[i].classList.add('radioBoxDivClick');
        } else {
            $checkboxes[i].classList.remove('radioSpanClick');
            $checkboxes2[i].classList.remove('radioBoxDivClick');
        }
    }
}

/* 파일 썸네일 */
/* 파일 */
const file = document.querySelector('input[type=file]');
/* 썸네일 담을 div */
const imgDiv = document.querySelector('.imageThumbnail');
/* x버튼 */
const closeButton = document.querySelector('span.closeImgButton');

file.addEventListener('change', function (e) {
    closeButton.style.display = 'inline-block';
    this.style.display = 'none';
    console.log(e.target.files[0]);

    /* 파일절대경로얻기 */
    let reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);

    /* 이미지인지 확인 */
    /* 이미지면 체크된 인덱스의 div.image에 넣기 아니면 no_image 띄어주기 */
    reader.onload = function (e) {
        let result = e.target.result;
        if (result.includes('image')) {
            imgDiv.style.backgroundImage = `url('${result}')`;
        } else {
            imgDiv.style.backgroundImage = `url('/src/main/resources/static/image/no_image.png')`;
        }
    };
});

/* x버튼 누르면 기본이미지로 변경 */
closeButton.addEventListener('click', function (e) {
    this.style.display = 'none';
    /* 삭제했을 때 이미지 뿐만 아니라 data 없애주기 */
    file.value = '';
    imgDiv.style.backgroundImage = '';
});


/* 정규식 */
const $infoInputs = $('.wrapper input[type=text]');
const nameRegex = /^[가-힣|a-z|A-Z|]+$/;
const specialCharacterRegex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
let infoBlurMessages = ["이름을 입력하세요.", "휴대폰 번호를 입력하세요."];
let infoRegexMessages = ["영문 혹은 한글로 2자~20자로 입력해주세요.", "휴대폰 번호를 확인하세요."];
let infoCheck;
let infoCheckAll = [false, false];

const $errorDiv = $('div.errorDiv');
const $erroMessage = $('div.errorDiv p.errorMessage');

$infoInputs.on("blur", function(){
    let i = $infoInputs.index($(this));
    let value = $(this).val();
    
    if(!value) {
        $errorDiv.eq(i).css("display", "block");
        $erroMessage.eq(i).text(infoBlurMessages[i]);
        infoCheck = false;
        infoCheckAll[i] = infoCheck;
        return;
    }
    else {
        $errorDiv.eq(i).css("display", "none");
    }

    switch(i){
        case 0:
            infoCheck = value.length > 1 && value.length < 21 && nameRegex.test(value);
            break;
        case 1:
            infoCheck = phoneRegex.test(value);
            if(infoCheck){
                $(this).val(value.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
            }
            break;
    }

    infoCheckAll[i] = infoCheck;

    if(!infoCheck){
        $errorDiv.eq(i).css("display", "block");
        $erroMessage.eq(i).text(infoRegexMessages[i]);
        return;
    }
    else {
        $errorDiv.eq(i).css("display", "none");
    }

    $erroMessage.eq(i).text("");
    
})

