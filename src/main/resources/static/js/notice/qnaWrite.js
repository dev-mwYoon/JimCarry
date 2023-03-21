/* 파일 썸네일 */
/* 파일인풋 */
const file = document.querySelector('input[type=file]');
const imgButton = document.querySelector(".imgButton");
console.log(imgButton);

function handleFiles(files) {
    /* 썸네일 담을 div의 부모 */
    const thumbnailList = document.getElementById("thumbnail-list");

    for (let i = 0; i < files.length; i++) {

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


$('#inquiry-contents').keyup(function (e) {
	let content = $(this).val();
    
    // 글자수 세기
    if (content.length == 0 || content == '') {
    	$('.content-length-count').text('0자');
    } else {
    	$('.content-length-count').text(content.length + '자');
    }
    
    // 글자수 제한
    if (content.length > 5000) {
    	// 5000자 부터는 타이핑 되지 않도록
        $(this).val($(this).val().substring(0, 5000));
        
    };
});


/* 필수 input 확인 */
const $subject = $("#inquiry-subject")
const $contents = $("#inquiry-contents")
let infoBlurMessages = [
    '제목을 입력하세요.',
    '내용을 입력하세요.',
];
let infoCheck;
let infoCheckAll = [false, false];

const $errorDiv = $('div.errorDiv');
const $erroMessage = $('div.errorDiv p.errorMessage');

/* 제목 input */
$subject.on('blur', function () {
    let i = 0;
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

    infoCheck = value.length > 1;

    infoCheckAll[i] = infoCheck;

    $erroMessage.eq(i).text('');
    requireCheck();
});

/* 내용 input */
$contents.on('blur', function () {
    let i = 1;
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

    infoCheck = value.length > 1;

    infoCheckAll[i] = infoCheck;

    $erroMessage.eq(i).text('');
    requireCheck();
});

/* 필수 확인 후 버튼 활성화 */
function requireCheck() {
    const arr = [true, true];
    const $submitBtn = $(".submit-button");
    console.log($submitBtn);
    
    $(".submit-button").attr("disabled", true);

    if(JSON.stringify(infoCheckAll) === JSON.stringify(arr)) {
        $(".submit-button").attr("disabled", false);

        $(".submit-button").css("background-color", "#5f0080");

        /* $(".submit-button").removeClass('submit-button-disabled');
        $(".submit-button").addClass('submit-button'); */
    }
    else {
        $(".submit-button").attr("disabled", true);

        $(".submit-button").css("background-color", "#ddd");
        /* $(".submit-button").removeClass('submit-button');
        $(".submit-button").addClass('submit-button-disabled'); */
    }
}