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
    const sizeSRadioCheck = document.querySelector('input[id="sizeSmall"]');
    const sizeMRadioCheck = document.querySelector('input[id="sizeMiddle"]');
    const sizeBRadioCheck = document.querySelector('input[id="sizeBig"]');


    checkboxes = document.querySelectorAll('.checkBox');
    checkboxes2 = document.querySelectorAll('.checkBox2');
    /* sRadioSpan = document.querySelector('#sizeSmallSpan');
    sRadioDiv = document.querySelector('#sizeSmallDiv');

    mRadioSpan = document.querySelector('#sizeMiddleSpan');
    mRadioDiv = document.querySelector('#sizeMiddleDiv');

    bRadioSpan = document.querySelector('#sizeBigSpan');
    bRadioDiv = document.querySelector('#sizeBigDiv'); */



    radioCheck = [sizeSRadioCheck,sizeMRadioCheck, sizeBRadioCheck];
    
    radioCheck.forEach(element => {
        if(element.checked == true){
            console.log("클릭함")
            element.classList.replace('checkBox', 'radioSpanClick');
            element.classList.replace('checkBox2', 'radioBoxDivClick');
        }
    });
    
}


/* 파일 썸네일 */
/* 파일 */
const file = document.querySelector('input[type=file]');
/* 썸네일 담을 div */
const imgDiv = document.querySelector('.imageThumbnail');
/* x버튼 */
const closeButton = document.querySelector('span.closeImgButton');

file.addEventListener("change", function(e){
    closeButton.style.display = "inline-block";
    this.style.display = "none";
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
})

/* x버튼 누르면 기본이미지로 변경 */
closeButton.addEventListener('click', function (e) {
    this.style.display = "none";
	/* 삭제했을 때 이미지 뿐만 아니라 data 없애주기 */
	file.value = "";
    imgDiv.style.backgroundImage = "";
});