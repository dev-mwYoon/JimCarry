/*리뷰 목록 불러오기*/
const reviewTableContainer = $(".table");

const createDOM = function (reviews) {
    let text = `
        <tr class="table__content">
          <td>
            <label class="check-label">
              <input type="checkbox" name="check" />
            </label>
          </td>
          <td class="content__id">${reviews.reviewId}</td>
          <td>${reviews.userIdentification}</td>
          <td>${reviews.userEmail}</td>
          <td>${reviews.reviewTitle}</td>
          <td>${reviews.reviewWriteDate}</td>
          <td>
            <button class="content__detail__btn button__type_2 button__color__green">
              상세보기
            </button>
          </td>
        </tr>
`
    return text;
}
$('#Glyph').on('click', function () {
    const conditiontest = $('.listbox-selecter').text();

    var condition;
    if (conditiontest == '제목') {
        condition = "reviewTitle";
    } else if (conditiontest == '내용') {
        condition = "reviewContext";
    } else if (conditiontest == '검색조건 선택') {
        alert("검색 조건을 선택해주세요.");
        return;
    }
    $("input[name='condition']").val(condition);

    document.searchForm.submit();
});
reviews.forEach((reviews, i) => {
    reviewTableContainer.append(
        createDOM(reviews)
    );

});


$(".content__detail__btn").on('click', function () {
    let $detailBt = $('.content__detail__btn');
    console.log("click");
    const i = $detailBt.index($(this));

    /* 해당 컨텐츠 번호 */
    const reviewId = $detailBt.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    $.ajax({
        url: "/admins/review/detail",
        type: "post",
        data: {reviewId: reviewId},
        traditional: true,
        success: function (reviewdetail) {

            $(".modal-stage").html(
                `
                   <section class="modal">
                          <div class="modal__header" >
                            <h3 class="modal__title">리뷰 상세보기</h3>
                            <a class="modal-close" id="modal-close">
                              <svg
                                xmlns="http://www.w3.org/2000/svg"
                                data-name="Capa 1"
                                id="Capa_1"
                                viewBox="0 0 20 19.84">
                                <path
                                  d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z" />
                              </svg>
                            </a>
                          </div>
                          <form class="storage_form" method="post" action="/admin/review/update">
                            <main class="modal__main">
                              <div class="modal__content">
                                <h4 class="review_detail_title" name="reviewTitle">${reviewdetail.reviewTitle}</h4>
                                <div class="content__intput input_box_shadow">
<!--                                  <input type="text" name="userAddress" readonly="true" />-->
                                </div>
                                <div class="content__main">
                                  <div class="content__img__wrap">
                                    <!-- 임시로 name='file' 해둠 -->
                                   ${
                                        reviewdetail.fileVOS
                                        .map((data, i) => {
                                            return `<label class="attach">
                                                            <div class="content__img">
                                                              <img src="/storages/search/files/display?fileName=${data.filePath}/${data.fileUuid}_${data.fileOrgName}">
                                                            </div>
                                                            <input type="" style="display: none;"/>
                                                          </label>`;
                                        })
                                        .join('')
                                     }
                                  </div>
                                  <h4 class="review_detail_title">리뷰 상세내용</h4>
                                  <div class="content__intput input_box_shadow">
                                    <textarea name="reviewContext">${reviewdetail.reviewContext}</textarea>
                                    <input type="hidden" name="reviewId" value="${reviewdetail.reviewId}">
                                  </div>
                                </div>
                              </div>
                              <div class="user__profile__button">
                                <button id="completeBtn" class="button__type_2 button__color__green">
                                  작성완료
                                </button>
                              </div>
                            </main>
                          </form>
                        </section>
                    `
            );

            /* 모달 닫는 이벤트 */
            /* 추후 외부로 빼야함 */
            $('#modal-close').on('click', function () {
                $modalStage.fadeOut(500);
            });
            $('.close_detailModal').on('click', function () {
                $modalStage.fadeOut(500);
            });

        }
    });


    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});


/* 상세보기 모달 내용 submit 이벤트 */


/* 체크박스 */
const $checkAll = $('#checkAll');
const $check = $("input[name='check']");
let $checkArr = [];
$('input[name=check]').on('click', function () {
    if ($(this).is(':checked')) {
        $checkArr.push($(this).parent().parent().next().text());
    } else {
        var toRemove = $(this).parent().parent().next().text();
        $checkArr = $checkArr.filter(function (item) {
            return item !== toRemove;
        });
    }
    console.log($checkArr);
});

/* 체크박스 이벤트 ======================================= */
$checkAll.click(function () {
    if ($checkAll.is(':checked')) {
        $check.prop('checked', true);
        $check.each((i, e) => {
            $checkArr.push($check.eq(i).parent().parent().next().text());
        });
    } else {
        $check.prop('checked', false);
        $check.each((i, e) => {
            var toRemove = $check.eq(i).parent().parent().next().text();
            $checkArr = $checkArr.filter(function (item) {
                return item !== toRemove;
            });
        });
    }
    console.log($checkArr);
});

$check.click(function () {
    var total = $check.length;
    var checked = $('input[name=check]:checked').length;
    if (total != checked) {
        $checkAll.prop('checked', false);
    } else {
        $checkAll.prop('checked', true);
    }
});
confirmButton.on('click', function () {
    $.ajax({
        url: "/admin/review/delete",
        type: "post",
        data: {reviewIds: $checkArr},
        traditional: true,
        success: function (result) {
            if (result) {
                location.reload();
            }
        }
    });
});