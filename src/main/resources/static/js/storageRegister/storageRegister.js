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
    const $sizes = $('input[name=storageSize]');

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
/* 모달창 */
const showModal = document.querySelector(".modalContainer");
const clickModalBtn= document.querySelector(".modalCheckButton");

/* 파일 썸네일 */

/* 파일인풋 */
const file = document.querySelector('input[type=file]');
const imgButton = document.querySelector(".imgButton");
console.log(imgButton);

function handleFiles(files) {
    /* 썸네일 담을 div의 부모 */
    const thumbnailList = document.getElementById("thumbnail-list");

    for (let i = 0; i < files.length; i++) {

        /* 8개 이미지 추가되면 버튼 없애기 */
        if ($(".imageThumbnail").length > 7) {
            $(".imgButtonWrap").hide();
        }

        /* 파일절대경로얻기 */  
        const file = files[i];
        const reader = new FileReader();
        /* reader가 onload 할때 */
        reader.onload = function(event) {
            /* 썸네일 담을 div와 그 자식의 span 선언 */   
            const thumbnail = document.createElement("div");
            const thumbnailSpan = document.createElement("span");


            let result = event.target.result;

            /* 썸네일 담을 div와 그 자식의 span에 썸네일 css와 x버튼 css 추가*/
            thumbnail.classList.add("imageThumbnail");
            thumbnailSpan.classList.add("closeImgButton");

            /* 썸네일 담을 div에 절대경로 넣어주기 */
            thumbnail.style.backgroundImage = `url('${result}')`;

            /* 썸네일 담을 div와 그 자식의 span 추가해주기 */
            thumbnailList.prepend(thumbnail);
            thumbnail.appendChild(thumbnailSpan);

            /* x버튼 선언 */
            const closeButton = document.querySelector(".closeImgButton");

            /* x버튼 누를 시 x버튼과 backgroundImage 지워주기 */
            closeButton.addEventListener('click', function (e) {
                e.preventDefault();
                file.value = "";
                this.style.display = 'none';
                thumbnail.style.backgroundImage = `url('')`;
                thumbnail.remove(thumbnail);
                $(".imgButtonWrap").show();
            });

            /* 파일 개수가 8개 이상이면 버튼숨기기 */
           if($(".imageThumbnail").length > 7 ){
            $(".imgButtonWrap").hide();
            return;
           }
            
        };
        /* result 속성(attribute)에 담기 */
        reader.readAsDataURL(file);
    }

}

/* 버튼을 감싸고있는 label객체 들고오기 */
const fileInput = document.getElementById("photo-picker");

/* 버튼을 감싸고있는 label객체 클릭하면 위에 function handleFiles 실행 */
fileInput.addEventListener("change", function(event) {
    handleFiles(event.target.files);
});


/* 모달창 확인버튼 누르면 없애기 */
function hideModal() {
    showModal.classList.remove("showModal");
}

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

    console.log("i: "+i);
    if (!value) {
        $errorDiv.eq(i-1).css('display', 'block');
        $erroMessage.eq(i-1).text(infoBlurMessages[i-1]);
        infoCheck = false;
        infoCheckAll[i-1] = infoCheck;
        return;
    } else {
        $errorDiv.eq(i-1).css('display', 'none');
    }

    switch (i) {
        case 1:
            infoCheck = value.length > 1 && value.length < 21 && nameRegex.test(value);
            break;
        case 2:
            infoCheck = phoneRegex.test(value);
            if (infoCheck) {
                $(this).val(value.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
            }
            break;
        case 3:
            infoCheck = value.length > 1;
            break;
        case 4:
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

var region_list = ["", "서울", "경기", "강원", "충북", "충남",
    "경북", "경남", "전북", "전남", "제주특별자치도"];

const checkRegion = function() {
    console.log("sdadas");
    const $address = $('#address').val();
    var startIndex = $address.indexOf(" "); // 첫 번째 공백의 인덱스

    var result = $address.substring(0, startIndex); // 시작 인덱스부터 끝 인덱스 직전까지의 문자열 추출

    for (var i = 0; i < region_list.length; i++) {
        if(result == region_list[i]) {
            $('input[name=storageAddressNumber]').val(i);
        }
    }

//    인천, 대구, 광주, 부산, 울산, 대전
    if(result == "인천") {
        $('input[name=storageAddressNumber]').val(2);
    }

    if(result == "대구") {
        $('input[name=storageAddressNumber]').val(6);
    }

    if(result == "광주") {
        $('input[name=storageAddressNumber]').val(9);
    }

    if(result == "부산") {
        $('input[name=storageAddressNumber]').val(7);
    }

    if(result == "울산") {
        $('input[name=storageAddressNumber]').val(7);
    }

    if(result == "대전") {
        $('input[name=storageAddressNumber]').val(5);
    }
}

/*날짜 제한 설정하기*/

// 지금 날짜를 밀리초로
var now_utc = Date.now();
//getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeoff = new Date().getTimezoneOffset()*60000; // 분 단위를 밀리초로 반환

var today = new Date(now_utc-timeoff).toISOString().split("T")[0];
//오늘자 이후로 선택 안됨
document.getElementById("inputDateStart").setAttribute("min", today);

// 지금 날짜를 밀리초로
var now_utc = Date.now();
//getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeoff = new Date().getTimezoneOffset()*60000; // 분 단위를 밀리초로 반환

var today = new Date(now_utc-timeoff).toISOString().split("T")[0];
document.getElementById("inputDateEnd").setAttribute("min", today);