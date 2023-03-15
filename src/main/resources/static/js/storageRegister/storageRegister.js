// 주소 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.querySelector('#address').value = data.address; // 주소 넣기
            document.querySelector('#address').focus();
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
/* 파일인풋 */
const file = document.querySelector('input[type=file]');
const imgButton = document.querySelector(".imgButton");
console.log(imgButton);
/* x버튼 */
/* const closeButton = document.querySelector('span.closeImgButton'); */
/* const size = imgDiv.length(); */


let count = 0;
function handleFiles(files) {

    /* 썸네일 담을 div */
    const thumbnailList = document.getElementById("thumbnail-list");
    for (let i = 0; i < files.length; i++) {
        if(files.length > 8) {
            alert("파일첨부는 최대 8개 까지만 가능합니다.");
            break;
        }
      const file = files[i];
      const reader = new FileReader();
      reader.onload = function(event) {
        const thumbnail = document.createElement("img");
        let result = event.target.result;
        thumbnail.classList.add("imageThumbnail");
        thumbnail.src = event.target.result;
        thumbnailList.prepend(thumbnail);
        };
      reader.readAsDataURL(file);
    }
    count += files.length
    console.log("count: "+ count);

    if(files.length <= 8){
        if(count > 7) {
            imgButton.style.display = "none";
            const thumbnail = document.removeElement("img");
        }
    }
}
  const fileInput = document.getElementById("photo-picker");
  fileInput.addEventListener("change", function(event) {
    handleFiles(event.target.files);
});


/* x버튼 누르면 기본이미지로 변경 */
/* closeButton.addEventListener('click', function (e) {
    this.style.display = 'none'; */

    /* div도 같이 삭제 */
    /* $(".imageThumbnail").remove(); */

    /* 삭제했을 때 이미지 뿐만 아니라 data 없애주기 */
    /* file.value = '';
    imgDiv.style.backgroundImage = '';
}); */

/* 정규식 */
const $infoInputs = $('.wrapper input[type=text]');
const nameRegex = /^[가-힣|a-z|A-Z|]+$/;
const specialCharacterRegex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
const phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
let infoBlurMessages = [
    '이름을 입력하세요.',
    '휴대폰 번호를 입력하세요.',
    '주소를 입력하세요.',
    '상세주소를 입력하세요.',
];
let infoRegexMessages = ['영문 혹은 한글로 2자~20자로 입력해주세요.', '휴대폰 번호를 확인하세요.'];
let infoCheck;
let infoCheckAll = [false, false, false, false];

const $errorDiv = $('div.errorDiv');
const $erroMessage = $('div.errorDiv p.errorMessage');

console.log($infoInputs);

$infoInputs.on('blur', function () {
    let i = $infoInputs.index($(this));
    let value = $(this).val();

    if (!value) {
        $errorDiv.eq(i).css('display', 'block');
        $erroMessage.eq(i).text(infoBlurMessages[i]);
        infoCheck = false;
        infoCheckAll[i] = infoCheck;
        return;
    } else {
        $errorDiv.eq(i).css('display', 'none');
    }

    switch (i) {
        case 0:
            infoCheck = value.length > 1 && value.length < 21 && nameRegex.test(value);
            break;
        case 1:
            infoCheck = phoneRegex.test(value);
            if (infoCheck) {
                $(this).val(value.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
            }
            break;
        case 2:
            infoCheck = value.length > 1;
            break;
        case 3:
            infoCheck = value.length > 1;
            break;
    }

    infoCheckAll[i] = infoCheck;

    if (!infoCheck) {
        $errorDiv.eq(i).css('display', 'block');
        $erroMessage.eq(i).text(infoRegexMessages[i]);
        return;
    } else {
        $errorDiv.eq(i).css('display', 'none');
    }

    $erroMessage.eq(i).text('');
});
