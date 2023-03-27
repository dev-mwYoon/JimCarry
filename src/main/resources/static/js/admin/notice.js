const noticeTableContainer = $(".table");
const creatDOM = function (notices) {
    let text=
        `
         <tr class="table__content">
              <td>
                <label class="check-label">
                  <input type="checkbox" name="check" />
                </label>
              </td>
              <td class="content__id">${notices.noticeId}</td>
              <td>${notices.noticeTitle}</td>
              <td class="notice-content">${notices.noticeContent}</td>
              <td>${notices.noticeRegist}</td>
              <td>${notices.noticeUpdate}</td>
              <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                  상세보기
                </button>
              </td>
        </tr>
        `
    return text;
}
notices.forEach((notices, i)=>{
    noticeTableContainer.append(
        creatDOM(notices)
    );
});



$(".content__detail__btn").on('click', function () {
    console.log("click");
    const i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    const contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});

/* 상세보기 모달 내용 submit 이벤트 */
$('#completeBtn').on('click', function (e) {
    e.preventDefault();
    return new Promise(
        function () {
            console.log('으으아');
            $modalStage.fadeOut(500);
        },
        () => {
            $('.storage_form').submit();
        }
    );
});
