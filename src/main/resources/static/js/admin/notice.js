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
              <td>${notices.noticeRegist}</td>
              <td>${notices.noticeUpdate}</td>
              <td>${notices.noticeWriter}</td>
              
              <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                  상세보기
                </button>
              </td>
        </tr>
        `
    return text;
}
$('#Glyph').on('click', function() {
    const conditiontest = $('.listbox-selecter').text();

    var condition;
    if (conditiontest == '제목') {
        condition = "noticeTitle";
    } else if (conditiontest == '작성자') {
        condition = "noticeWriter";
    } else if( conditiontest == '검색조건 선택'){
        alert("검색 조건을 선택해주세요.");
        return;
    }
    $("input[name='condition']").val(condition);

    document.searchForm.submit();
});
notices.forEach((notices, i)=>{
    noticeTableContainer.append(
        creatDOM(notices)
    );
});

$('#create-button').on('click', function(){
            $(".modal-stage").html(
                `
               <section class="modal" th:fragment="notice-modal">
                  <div class="modal__header">
                    <h3 class="modal__title">공지 상세보기</h3>
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
                  <form class="storage_form" name="noticeForm" method="post" action="/admin/notice/register">
                    <main class="modal__main">
                      <div class="modal__content">
                        <div class="content__title">
                          <h4>공지사항 제목</h4>
                          <div class="content__intput notice__title" style="margin-left: 11px;">
                            <input type="text" name="noticeTitle" value="공지사항 테스트용 제목">
                          </div>
                             <h4 style="margin-left: 37px;">작성자</h4>
                          <div class="content__intput notice__title" style="margin-right: 17px; margin-left: -33px;">
                            <input type="text" name="noticeWriter" value="작성자">
                          </div>
                        <div class="content__intput date" style="flex: 0">
                            <input type="date" name="noticeRegist">
                          </div></div>
                        <div class="notice__content">
                          <textarea id="summernote" name="noticeContent"></textarea>
                        </div>
                      </div>
                      <div id="#completeBtn" class="user__profile__button">
                        <button class="button__type_2 button__color__green">작성완료</button>
                      </div>
                    </main>
                  </form>
                </section>
                `
            );
    $('#summernote').summernote({
        placeholder: '공지사항 내용 작성',
        tabsize: 2,
        height: 300,
        width: '100%',
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link']],
        ],
    });

    $modalStage.show();
    $('.modal-close').on('click', function () {
        $modalStage.fadeOut(500);
    });
})



$(".content__detail__btn").on('click', function () {
    console.log("click");
    console.log($(".content__detail__btn"));
    console.log($detailButton);
    const i = $(".content__detail__btn").index($(this));

    /* 해당 컨텐츠 번호 */
    const noticeId = $(".content__detail__btn").eq(i).parent().siblings('.content__id').text();
    console.log(noticeId);
    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    $.ajax({
        url: "/admins/notice/detail",
        type: "post",
        data: { noticeId : noticeId },
        traditional : true,
        success : function(noticedetail){
            $(".modal-stage").html(
            `
               <section class="modal" th:fragment="notice-modal">
                  <div class="modal__header">
                    <h3 class="modal__title">공지 상세보기</h3>
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
                  <form class="storage_form" method="post" action="/admin/notice/update">
                    <main class="modal__main">
                      <div class="modal__content">
                        <div class="content__title">
                          <h4>공지사항 제목</h4>
                          <div class="content__intput notice__title">
                            <input type="text" name="noticeTitle" value="${noticedetail.noticeTitle}" />
                          </div>
                          <div class="content__intput date" style="flex: 0">
                            <input type="date" name="noticeRegist" value="${noticedetail.noticeRegist}" />
                          </div>
                        </div>
                        <div class="notice__content">
                          <textarea id="summernote" name="noticeContent" >${noticedetail.noticeContent}</textarea>
                        </div>
                      </div>
                      <div id="#completeBtn" class="user__profile__button">
                        <input type="hidden" name="noticeId" value="${noticedetail.noticeId}">
                        <input type="hidden" name="noticeWriter" value="${noticedetail.noticeWriter}">
                        <button class="button__type_2 button__color__green">작성완료</button>
                      </div>
                    </main>
                  </form> 
                </section>
                `

            );
            $('#summernote').summernote({
                placeholder: '공지사항 내용 작성',
                tabsize: 2,
                height: 300,
                width: '100%',
                toolbar: [
                    ['style', ['style']],
                    ['font', ['bold', 'underline', 'clear']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['table', ['table']],
                    ['insert', ['link']],
                ],
            });
            /* 모달 닫는 이벤트 */
            $('#modal-close').on('click', function () {
                $modalStage.fadeOut(500);
            });


        }
    });

    $modalStage.show();

});

/* 상세보기 모달 내용 submit 이벤트 */
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

/* 체크박스 */
const $checkAll = $('#checkAll');
const $check = $("input[name='check']");
let $checkArr = [];
$('input[name=check]').on('click', function() {
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
        $check.each((i,e)=> {
            $checkArr.push($check.eq(i).parent().parent().next().text());
        });
    }
    else {
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
    }
    else {
        $checkAll.prop('checked', true);
    }
});

confirmButton.on('click', function(){
    $.ajax({
        url: "/admin/notice/delete",
        type: "post",
        data: {noticeIds : $checkArr},
        traditional : true,
        success : function (result) {
            if(result){
                location.reload();
            }
        }
    });
});