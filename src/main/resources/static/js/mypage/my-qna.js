const doAjax = function (config, callback) {
    let isContentType = true;
    let isProcesData = true;

    /* contentType과 processData의 false 값이 제대로 들어가게끔 조건문 설정 */
    if (!config.contentType) {
        isContentType = config.contentType === false ? true : false;
    }
    if (!config.processData) {
        isProcesData = config.processData === false ? true : false;
    }

    $.ajax({
        url: config.url,
        data: config.data,
        method: config.method,
        processData: isProcesData ? config.processData : true,
        contentType: isContentType ? config.contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log(config.data);
        }
    });
}

const qnaContainer = $(".qna-list-main-container");
const createDOM = function (qna) {
    return `
<li style="border-color: white;">
    <button type="button" class="qna-list-main-btn-container" name="flip">
        <div class="qna-list-main-title-container">
            <a href="#" class="qna-list-main-title-img-weapper">
                <img src="https://boxful-b2c-test-bucket.s3.ap-northeast-2.amazonaws.com/products/pickup/pallet/ko/one-pallet.png"
                     class="title-img">
            </a>
            <div>
                <p class="title-name">[소] 100cm(가로) x 75cm(세로) x 220cm(높이)</p>
                <div class="title-contact-wrapper">
                    <p class="title-contact">${qna.inquiryTitle}</p>
                </div>
            </div>
        </div>
        <div class="qna-list-main-date">${qna.inquiryRegist.split(" ")[0]}</div>
        <div class="qna-state-container">
            ${qna.inquiryAnswer == 0 ? '<span style="color: red">답변대기</span>' : '<span style="color: green">답변완료</span>'}
        </div>
    </button>
    <!-- 슬라이드 효과 (수정, 삭제) -->
    <div class="qna-slide-container" name="panel">
        <div class="qna-slide-wrapper">
            <div class="qna-slide-content">
                <div class="qna-slide-content-wrapper">
                    <div class="qna-slide-content-icon">
                            <span style="box-sizing: border-box; display: inline-block; overflow: hidden; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px; position: relative; max-width: 100%;">
                                <span style="box-sizing: border-box; display: block; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px; max-width: 100%;">
                                    <img alt="" aria-hidden="true"
                                         src="data:image/svg+xml,%3csvg%20xmlns=%27http://www.w3.org/2000/svg%27%20version=%271.1%27%20width=%2724%27%20height=%2724%27/%3e"
                                         style="display: block; max-width: 100%; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px;">
                                </span>
                                <img alt="질문"
                                     src="https://res.kurly.com/kurly/ico/2021/question_24_24_purple.svg"
                                     decoding="async" data-nimg="intrinsic" class="css-0"
                                     srcset="https://res.kurly.com/kurly/ico/2021/question_24_24_purple.svg 1x, https://res.kurly.com/kurly/ico/2021/question_24_24_purple.svg 2x"
                                     style="position: absolute; inset: 0px; box-sizing: border-box; padding: 0px; border: none; margin: auto; display: block; width: 0px; height: 0px; min-width: 100%; max-width: 100%; min-height: 100%; max-height: 100%;">
                            </span>
                    </div>
                    <p class="qna-slide-content-text">${qna.inquiryContent}</p>
                </div>
            </div>
            <div class="qna-slide-actions" style="display: ${qna.inquiryAnswer == 0 ? 'flex' : 'none'}">
                <button class="qna-slide-actions-btn modal-btn" id="update-btn">수정</button>
                <button class="qna-slide-actions-btn delete-btn" id="delete-btn">삭제</button>
            </div>
        </div>
    </div>
</li>
`
}

inquiries.forEach((qna, i) => qnaContainer.append(createDOM(qna)));
$(".qna-list-main-container").append(
    `
        <div class="change-modal" id="modal">
                <div class="change-modal-root"></div>
                <div class="change-modal-container">
                    <div class="change-modal-wrapper">
                        <div>
                            <div class="change-modal-title-row">
                                <h4 class="change-modal-title-text">1:1 문의내역</h4>
                                <!-- x버튼 -->
                                <button type="button" class="close-btn" style="cursor: pointer;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
                                         viewBox="0 0 32 32">
                                        <g fill="none" fill-rule="evenodd">
                                            <g>
                                                <g>
                                                    <g>
                                                        <path fill="#fff" fill-opacity="0"
                                                              d="M0 0H32V32H0z"
                                                              transform="translate(-884 -270) translate(500 240) translate(384 30)"></path>
                                                        <g stroke="#999" stroke-linecap="round">
                                                            <path d="M20 20L0 0M0 20L20 0"
                                                                  transform="translate(-884 -270) translate(500 240) translate(384 30) translate(6 6)"></path>
                                                        </g>
                                                        <g fill="#999">
                                                            <path d="M.784.089l.07.057L10.5 9.793 20.146.146c.196-.195.512-.195.708 0 .173.174.192.443.057.638l-.057.07-9.647 9.646 9.647 9.646c.195.196.195.512 0 .708-.174.173-.443.192-.638.057l-.07-.057-9.646-9.647-9.646 9.647c-.196.195-.512.195-.708 0-.173-.174-.192-.443-.057-.638l.057-.07L9.793 10.5.146.854C-.049.658-.049.342.146.146.32-.027.59-.046.784.09z"
                                                                  transform="translate(-884 -270) translate(500 240) translate(384 30) translate(5.5 5.5)"></path>
                                                        </g>
                                                    </g>
                                                </g>
                                            </g>
                                        </g>
                                    </svg>
                                </button>
                            </div>
                            <form action = "/users/mypage/qna/update" method="post" class="change-modal-form-wrapper">
                                <div class="termStart">
                                    <div class="termsLayout">
                                        <div class="termsTitle">
                                            <label class="termsLabel">
                                                문의사진
                                            </label>
                                        </div>
                                        <div class="imageContainer">
                                            <div class="imageWrapperPadding">
                                                <div class="imageWrapper">
                                                    <div id="thumbnail-list" class="imageDiv">
                                                        <div class="thumbnailWrap"></div>
                                                        <div class="imgButtonWrap">
                                                            <label for="photo-picker">
                                                                <div class="imgButton">
                                                                    <span class="cameraIconImg"></span>
                                                                </div>
                                                                <input class="imageFileInput" id="photo-picker" type="file" accept="image/jpg, image/jpeg, image/png, image/bmp" multiple="">
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="imgTextWrapper">
                                                        <div class="imgTextDiv">
                                                            <span></span>
                                                            30MB 이하의 이미지만 업로드 가능합니다.
                                                        </div>
                                                        <div class="imgTextDiv">
                                                            <span></span>
                                                            상품과 무관한 내용이거나 음란 및 불법적인 내용은 통보없이 삭제 될 수 있습니다.
                                                        </div>
                                                        <div class="imgTextDiv">
                                                            <span></span>
                                                            사진은 최대 8장까지 등록가능합니다.
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="change-modal-form-row">
                                    <span class="change-modal-form-label">제목</span>
                                    <div class="change-modal-form-field">
                                        <div class="change-modal-form-title">
                                            <input type="text" placeholder="제목을 입력해주세요."
                                                   class="change-modal-form-title-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="change-modal-form-row">
                                    <span class="change-modal-form-label">내용</span>
                                    <div class="change-modal-form-field">
                                        <div class="change-modal-form-field-container">
                                            <textarea inputmode="text"
                                                      class="change-modal-form-field-text"></textarea>
                                            <span class="change-modal-form-field-content">
                                                    <span style="color: rgb(51, 51, 51);">0자</span>
                                                    <span style="color: rgb(153, 153, 153);"></span>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                                <input type="text" name="inquiryTitle" style="display: none">
                                <input type="text" name="inquiryContent" style="display: none">
                                <input type="text" name="inquiryId" style="display: none">
                                <input type="text" name="inquiryAnswer" style="display: none">
                                <input type="text" name="page" style="display: none">
                            </form>
                            <div class="change-modal-actions-row">
                                <button type="button" class="change-modal-delete-btn">
                                    <span class="change-modal-btn">취소</span>
                                </button>
                                <button type="submit" class="change-modal-ok-btn">
                                    <span class="change-modal-btn">등록</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    `
);

$(".qna-list-main-container").append(
    `
    <!-- 삭제 모달 -->
    <div class="delete-modal-container" id="delete-modal" style="display: none;">
        <div class="delete-modal-wrapper">
            <div class="delete-modal-content-container">
                <div class="delete-modal-content-wrapper">
                    <div class="delete-modal-content-text">작성된 문의를 삭제하시겠습니까?</div>
                    <form action="/users/mypage/qna/delete" method="post" name="deleteForm" class="delete-modal-content-btn">
                        <button type="button" class="cancel-btn" id="close">취소</button>
                        <button type="submit" class="check-btn">확인</button>
                        <input type="text" name="page" style="display: none" value="${$page}">
                        <input type="text" name="inquiryId" style="display: none">
                    </form>
                </div>
            </div>
        </div>
    </div>
    `
);

/* 슬라이드 */
$(document).ready(function () {
    $("button[name='flip']").click(function () {
        let i = $("button[name='flip']").index($(this));
        $("div[name='panel']").eq(i).slideToggle("slow");
    });
});

/* 수정모달 */
const $btn = $(".modal-btn");
const $container = $(".change-modal");
const $close = $(".close-btn");
const $cancel = $(".change-modal-delete-btn");

/* file.js에서 사용 */
/* 현재 페이지에서 몇 번째를 클릭했는가 */
let inquiryIndex;

/* 썸네일을 담는 div */
const $thumbnailWrap = $(".thumbnailWrap");

/* 실제 저장할 파일VO들의 배열 */
let fileVOs = new Array();

const $textareaTitle = $(".change-modal-form-title-input");

// 글자수 세기, 제한
const $textarea = $('.change-modal-form-field-text');
const $counter = $('.change-modal-form-field-content span:first-child');

// 최대 글자수를 정합니다.
const maxLength = 5000;

const thumbnailAjaxConfig = (i) => {
    return {
        url: `/users/mypage/files/thumbnail/${inquiries[i].inquiryId}`,
        method: "GET",
        data: "",
        contentType: "application/json; charset=utf-8",
    }
}

//모달창 열기
$btn.on("click", function () {
    //  현재 클릭한 모달창을 기준으로 텍스트 설정
    let i = $btn.index($(this));
    inquiryIndex = i;

    /* 글자수 세팅 */
    let textLength = 0;
    $textareaTitle.val(inquiries[i].inquiryTitle);
    $textarea.val(inquiries[i].inquiryContent);

    textLength = $textarea.val().length;

    /* 처음 문의내용에 따라 몇자인지 세팅 */
    $counter.text(`${textLength}자 / ${maxLength}자`);

    //  열기
    $container.css("display", "block");

    // 텍스트 입력이 일어날 때마다 글자수를 세고, 글자수를 표시합니다.
    $textarea.on('input', () => {
        textLength = $textarea.val().length;

        // 최대 글자수를 초과하면 입력을 막습니다.
        if (textLength > maxLength) {
            let max = $textarea.val();
            $textarea.val(max.slice(0, maxLength));
            textLength = $textarea.val().length;
        }

        $counter.text(`${textLength}자 / ${maxLength}자`);
    });

    /* 썸네일 내용 및 파일 배열 비우기 */
    $thumbnailWrap.empty();
    fileVOs = new Array();

    doAjax(thumbnailAjaxConfig(i), (result) => {
        result.forEach((file) => {
            $thumbnailWrap.append(
                `
                <img class="imageThumbnail" 
                src="/users/mypage/files/display?fileName=${file.filePath + "/t_" + file.fileUuid + "_" + file.fileOrgName}">
                `
            );
        })
    });
});

//모달창 닫기
$close.on("click", function () {
    $container.css("display", "none");
});

//모달창 취소 닫기
$cancel.on("click", function () {
    $container.css("display", "none");
});

/* 삭제 모달 */
const $modal = $(".delete-btn");
const $openBtn = $("#delete-modal");
const $span = $("#close");

// 모달창 열기
$modal.on("click", function () {
    let i = $modal.index($(this));
    inquiryIndex = i;
    $openBtn.css("display", "block");
});

// 모달창 닫기
$span.on("click", function () {
    $openBtn.css("display", "none");
});

/* 문의삭제 */
$("form[name='deleteForm']").on("submit", function (e) {
    $("input[name='inquiryId']").val(inquiries[inquiryIndex].inquiryId);
});