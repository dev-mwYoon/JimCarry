const inquiryTableContainer = $(".table");



const createDOM = function(inquiries){
    let text =
        `<tr class="table__content">
          <td>
              <label class="check-label">
                <input type="checkbox" name="check" />
              </label>
          </td>
          <td class="content__id">${inquiries.inquiryId}</td>
          <td>${inquiries.userIdentification}</td>
          <td>${inquiries.userEmail}</td>
          <td>${inquiries.inquiryRegist}</td>
          <td class="${inquiries.inquiryAnswer == 1 ? 'enquiryOk' : 'enquiryNo'}">${inquiries.inquiryAnswer == 1 ? '답변완료' : '답변대기'}</td>
          <td>
            <button class="content__detail__btn button__type_2 button__color__green">
              상세보기
            </button>
          </td>
         </tr>`

    return text;

}

inquiries.forEach((inquiries, i)=>{
    inquiryTableContainer.append(
        createDOM(inquiries)
    );
});
$(".modal-stage").append(
    `
    <section class="modal" th:fragment="enquiry-modal">
      <div class="modal__header">
        <h3 class="modal__title">문의 상세보기</h3>
        <a class="modal-close">
          <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
            <path
              d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z" />
          </svg>
        </a>
      </div>
      <form class="storage_form" action="">
        <main class="modal__main">
          <div class="modal__profile__top">
            <h4>회원정보</h4>
            <div class="user__profile">
              <h5>아이디</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="example1234" readonly="true"/>
              </div>
            </div>
            <div class="user__profile">
              <h5>이메일</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="example@gmail.com" readonly="true"/>
              </div>
            </div>
            <div class="user__profile">
              <h5>전화번호</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="01012341234" readonly="true"/>
              </div>
            </div>
          </div>
          <div class="modal__profile__bottom">
            <h4>테스트 문의 제목</h4>
            <textarea cols="30" rows="10"></textarea>
          </div>
        </main>
      </form>
    </section>
    `
);


/* 체크박스 이벤트 ======================================= */
// $('#checkAll').click(function () {
//     console.log($('input[type=checkbox]').is(':checked'));
//     if ($('#checkAll').is(':checked')) $('input[type=checkbox]').prop('checked', true);
//     else $('input[type=checkbox]').prop('checked', false);
// });
//
// $('input[type=checkbox]').click(function () {
//     console.log($('input[type=checkbox]').is(':checked'));
//     var total = $('input[type=checkbox]').length;
//     var checked = $('input[name=check]:checked').length;
//     console.log("들왓나?")
//     if (total != checked) $('#checkAll').prop('checked', false);
//     else $('#checkAll').prop('checked', true);
// });

// $(".content__detail__btn").on('click', function () {
//     const i = $detailButton.index($(this));
//     /* 해당 컨텐츠 번호 */
//     const contentId = $detailButton.eq(i).parent().siblings('.content__id').text();
//
//     /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
//
//     /* 추후 타임리프로 대체할 예정 */
//     $modalStage.show();
//
//     /* 모달 닫는 이벤트 */
//     /* 추후 외부로 빼야함 */
//     $('.modal-close').on('click', function (e) {
//         $modalStage.fadeOut(500);
//     });
// });
//
// /* 상세보기 모달 내용 submit 이벤트 */
// $('#completeBtn').on('click', function (e) {
//     e.preventDefault();
//     return new Promise(
//         function () {
//             console.log('으으아');
//             $modalStage.fadeOut(500);
//         },
//         () => {
//             $('.storage_form').submit();
//         }
//     );
// });



let $checkArr = [];
const $checkbox = $('input[name=check]');
console.log($checkbox);

$('input[type=checkbox]').on('click', function() {
    if ($(this).is(':checked')) {
        $checkArr.push($(this).parent().parent().next().text());
    } else {
        var toRemove = $(this).parent().parent().next().text();
        $checkArr = $checkArr.filter(function (item) {
            return item !== toRemove;
        });
    }
});
// /* 체크박스 */
// const $checkAll = $('#checkAll');
// const $check = $("input[name='check']");
// /* 체크박스 이벤트 ======================================= */
// $checkAll.click(function () {
//     if ($checkAll.is(':checked')) $check.prop('checked', true);
//     else $check.prop('checked', false);
// });
//
// $check.click(function () {
//     var total = $check.length;
//     var checked = $('input[name=check]:checked').length;
//     console.log("들왓나?")
//     if (total != checked) $checkAll.prop('checked', false);
//     else $checkAll.prop('checked', true);
// });

confirmButton.on('click', function () {

    $.ajax({
        url: "/admin/enquiry/delete",
        type: "post",
        data: { inquiryIds : $checkArr },
        traditional : true,
        success : function(result){
            if(result) {
                location.reload();
            }
        }
    });
});

