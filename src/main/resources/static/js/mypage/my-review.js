payments.forEach((payment, i) => {
    let check = reviews[i] === undefined;
    let text = `
            <div class="review-content-list">
                <div class="review-content">
                    <a style="text-decoration: none;">`

    if(!check && reviews[i].fileVOS.length > 0){
        let file = reviews[i].fileVOS[0];
        text += `<img src="/users/mypage/files/display?fileName=${file.filePath}/t_${file.fileUuid}_${file.fileOrgName}"
                             class="review-content-list-img">`
    } else {
        text += `<img src="https://boxful-b2c-test-bucket.s3.ap-northeast-2.amazonaws.com/products/pickup/pallet/ko/one-pallet.png"
                             class="review-content-list-img">`
    }
    text += `</a>
                    <p>
                        <a class="review-content-name">
                            ${check ? '리뷰 없음' : reviews[i].reviewTitle}
                        </a>
                        <span>${payment.paymentDate} 주문완료</span>
                    </p>
                </div>  
                <button class="review-content-btn modal-btn">
                    ${check ? '후기 작성' : '후기 수정'}
                </button>
            </div>
            <div class="review-content-line"></div>
        `;
    $(".review-content-list-container").append(text);
});

$(".review-content-wrpper").append(
    `
    <div class="change-modal" id="modal">
    <div class="change-modal-root"></div>
    <div class="change-modal-container">
        <div class="change-modal-wrapper">
            <div>
                <div class="change-modal-title-row">
                    <h4 class="change-modal-title-text">후기 작성 및 수정</h4>
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
                <div class="review-modal-container">
                    <div>
                        <div class="review-modal-title-container">
                            <div class="change-modal-image-row">
                                <div class="change-modal-image-wrapper">
                                        <span style="box-sizing: border-box; display: inline-block; overflow: hidden; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px; position: relative; max-width: 100%;">
                                            <span style="box-sizing: border-box; display: block; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px; max-width: 100%;">
                                                <img alt="" aria-hidden="true"
                                                     src="data:image/svg+xml,%3csvg%20xmlns=%27http://www.w3.org/2000/svg%27%20version=%271.1%27%20width=%2772%27%20height=%2772%27/%3e"
                                                     style="display: block; max-width: 100%; width: initial; height: initial; background: none; opacity: 1; border: 0px; margin: 0px; padding: 0px;">
                                            </span>
                                            <img src="https://boxful-b2c-test-bucket.s3.ap-northeast-2.amazonaws.com/products/pickup/pallet/ko/one-pallet.png"
                                                 class="change-modal-image">
                                        </span>
                                </div>
                                <p  class="change-modal-image-title-text"></p>
                            </div>
                        </div>
                        <div class="review-modal-main-container">
                            <h2 class="review-modal-main-text">후기는 이렇게 작성해 보세요</h2>
                            <p class="review-modal-text">
                                창고의 <span
                                    style="color: rgb(168, 100, 216);">크기, 사용감, 거리</span>등을
                                설명해주세요
                                <strong style="font-weight: 500;">좋았던 점, 아쉬웠던 점</strong>도
                                솔직하게 얘기해주세요
                            </p>
                        </div>
                        <form action="/users/mypage/review/update" method="post" class="change-modal-form-wrapper" datatype="">
                            <div class="review-modal-content-container">
                                <div class="review-modal-content-wrapper">
                                    <label class="review-modal-content-name">제목</label>
                                    <div class="review-modal-form-title">
                                        <input type="text" placeholder="제목을 입력해주세요." class="review-modal-form-title-input">
                                    </div>
                                </div>
                                <div class="review-modal-content-wrapper">
                                    <label class="review-modal-content-name">내용</label>
                                    <div style="width: 100%;">
                                        <div class="review-modal-content-inputBox-container">
                                            <textarea inputmode="text" maxlength="5000"
                                                      placeholder="창고 특성에 맞는 후기를 작성해주세요. 예) 창고 크기, 실제 창고 사진, 창고의 청결도 등 (최소 10자 이상)"
                                                      class="review-modal-content-inputBox"></textarea>
                                            <span class="review-modal-content-inputBox-number-container">
                                                    <span>
                                                        <span style="color: rgb(153, 153, 153);">0</span>
                                                        <span style="color: rgb(153, 153, 153);">/ 5000</span>
                                                    </span>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="review-modal-content-wrapper">
                                    <div class="termsLayout">
                                        <label class="review-modal-content-name">리뷰사진</label>
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
                                <div class="review-modal-content-wrapper">
                                    <label class="review-modal-content-name"></label>
                                    <ul class="review-modal-photo-text">
                                        <li class="review-modal-photo-text-list">창고와
                                            무관하거나 반복되는 동일 단어/문장을 사용하여 후기로 볼 수 없는 글, 판매자와
                                            고객의 후기 이용을 방해한다고 판단되는 경우, 창고를 구분할 수 없는 전체
                                            사진, 화면캡쳐, 음란 및 부적절하거나 불법적인 내용은 통보없이 삭제될 수
                                            있습니다.
                                        </li>
                                        <li class="review-modal-photo-text-list">전화번호,
                                            이메일, 주소, 계좌번호 등 개인정보가 노출되지 않도록 주의해주세요.
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="change-modal-actions-row">
                                <button type="button" class="change-modal-delete-btn">
                                    취소
                                </button>
                                <button type="button" class="change-modal-ok-btn"
                                        value="등록">
                                    등록
                                </button>
                            </div>
                            <input type="text" name="reviewTitle" style="display: none">
                            <input type="text" name="reviewContext" style="display: none">
                            <input type="text" name="page" style="display: none">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
`
);

/* file.js에서 사용 */
/* 현재 페이지에서 몇 번째를 클릭했는가 */
let contentIndex;

/* 썸네일 불러오기 */
const thumbnailAjaxConfig = (i) => {
    return {
        url: `/users/mypage/files/thumbnail/${reviews[i].reviewId}`,
        method: "GET",
        data: {table: "review"},
        contentType: "application/json; charset=utf-8",
    }
}

/* 썸네일을 담는 div */
const $thumbnailWrap = $(".thumbnailWrap");

/* 실제 저장할 파일VO들의 배열 */
let fileVOs = new Array();

const $textareaTitle = $(".review-modal-form-title-input");

// 글자수 세기, 제한
const $textarea = $('.review-modal-content-inputBox');
const $counter = $('.review-modal-content-inputBox-number-container span:first-child');

// 최대 글자수를 정합니다.
const maxLength = 5000;

/* 후기작성 모달 */
const $btn = $(".modal-btn");
const $container = $(".change-modal");
const $close = $(".close-btn");
const $cancel = $(".change-modal-delete-btn");
let $reviewTitle = $(".change-modal-image-title-text");

//모달창 열기
$btn.on("click", function () {
    $container.css("display", "block");
    //  현재 클릭한 모달창을 기준으로 텍스트 설정
    let i = $btn.index($(this));
    contentIndex = i;

    /* 글자수 세팅 */
    // 글자수세기, 제한  <작성가능 후기>
    let textLength = 0;
    $reviewTitle.text(reviews[i] === undefined ? "새로 작성하는 리뷰" : reviews[i].reviewTitle);
    $textareaTitle.val(reviews[i] === undefined ? "" : reviews[i].reviewTitle)
    $textarea.val(reviews[i] === undefined ? "" : reviews[i].reviewContext);

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

    if (reviews[i] === undefined) {
        return;
    }

    $doAjax(thumbnailAjaxConfig(i), (result) => {
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

reviewDTO = {
    reviewTitle: $("input[name='reviewTitle']").val($textareaTitle.val()),
    reviewContext: $("input[name='reviewContext']").val($textarea.val()),
}

$(".change-modal-ok-btn").on("click", function () {

    let reviewId;
    let config;
    const $files = $photoPicker[0].files;

    fileVOs = new Array();

    $files.forEach((file, i) => {
        let fileVO = new Object();
        fileVO.fileOrgName = file.name;
        fileVO.fileUuid = globalThis.uuids[i];
        fileVOs.push(fileVO);
    });
    /* 입력된 값을 가져와서 reviewDTO 객체 설정 */
    const reviewTitle = $textareaTitle.val();
    const reviewContext = $textarea.val();
    const reviewDTO = {
        reviewTitle: reviewTitle,
        reviewContext: reviewContext,
        files: fileVOs
    };
    /* 리뷰 Id 확인 */
    reviewId = reviews[contentIndex]?.reviewId;
    /* 새로 작성 */
    if (!reviewId) {
        reviewDTO.storageId = payments[contentIndex].storageId;
        config = {
            url: `/users/mypage/review/register?page=${$page}`,
            method: "POST",
            data: JSON.stringify(reviewDTO),
            contentType: "application/json; charset=utf-8",
        };
    } else {
        /* 업데이트 */
        reviewDTO.reviewId = reviewId;
        config = {
            url: `/users/mypage/review/update?page=${$page}`,
            method: "POST",
            data: JSON.stringify(reviewDTO),
            contentType: "application/json; charset=utf-8",
        };
    }
    $doAjax(config, (result) => {
        location.href = result;
    });
});