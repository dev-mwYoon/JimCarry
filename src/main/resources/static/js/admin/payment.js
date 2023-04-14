/*======== 결제 목록 조회 ========*/
const paymentTableContainer = $(".table");
const createDOM = function(payments){
    let text = `
        <tr class="table__content">
          <td>
            <label class="check-label">
              <input type="checkbox" name="check" />
            </label>
          </td>
          <td class="content__id">${payments.payId}</td>
          <td>${payments.storageId}</td>
          <td>${payments.userIdentification}</td>
          <td>${payments.userEmail}</td>
          <td>${payments.storageAddress} ${payments.storageAddressDetail}</td>
          <td>${payments.paymentDate.split(" ")[0]}</td>
          <td>
            <button class="content__detail__btn button__type_2 button__color__green">
              상세보기
            </button>
          </td>
        </tr>
`
    return text;
}
/*======== 검색 조건 조회  ========*/
$('#Glyph').on('click', function() {
    const conditiontest = $('.listbox-selecter').text();

    var condition;
    if (conditiontest == '창고위치') {
        condition = "storageAddress";
    } else if (conditiontest == '창고크기') {
        condition = "storageSize";
    } else if( conditiontest == '검색조건 선택'){
        return;
    }
    $("input[name='condition']").val(condition);

    document.searchForm.submit();
});
/*======== 결제 목록 출력 ========*/
payments.forEach((payments, i) => {
    paymentTableContainer.append(
        createDOM(payments)
    );

});

/*======== 결제 상세보기 모달 수정 ========*/
$(".content__detail__btn").on('click', function () {
    const i = $('.content__detail__btn').index($(this));
    console.log(i);

    /* 해당 컨텐츠 번호 */
    const payId = $('.content__detail__btn').eq(i).parent().siblings('.content__id').text();

                $.ajax({
                    url: "/admins/payment/detail",
                    type: "post",
                    data: { payId : payId },
                    traditional : true,
                    success : function(paydetail){
                        $(".modal-stage").html(
                                        `
                                 <section class="modal">
                                  <div class="modal__header">
                                    <h3 class="modal__title">결제 상세보기</h3>
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
                                  <form class="storage_form" action="">
                                    <main class="modal__main">
                                      <div class="modal__profile__top">
                                        <h4>결제 정보</h4>
                                        <div class="user__profile">
                                          <h5>예약번호</h5>
                                          <div class="user__profile__input">
                                            <input type="text" name="" value="${(paydetail.paymentDate.split("-").join("")).split(" ")[0]}${paydetail.payId}" readonly="true" />
                                          </div>
                                        </div>
                                        <div class="user__profile">
                                          <h5>결제일자</h5>
                                          <div class="user__profile__input">
                                            <input type="text" name="" value="${paydetail.paymentDate}" readonly="true" />
                                          </div>
                                        </div>
                                        <div class="user__profile">
                                          <h5>결제방법</h5>
                                          <div class="user__profile__input">
                                            <input type="text" name="" value="카카오페이(카카오머니)" readonly="true" />
                                          </div>
                                        </div>
                                        <div class="user__profile">
                                          <h5>결제금액</h5>
                                          <div class="user__profile__input">
                                            <input type="text" name="" value="${paydetail.paymentAmount}원" readonly="true" />
                                          </div>
                                        </div>
                                        <div class="user__profile">
                                          <h5>창고이용기간</h5>
                                          <div class="user__profile__input">
                                            <input type="text" name="" value="${paydetail.storageUseDate} ~ ${paydetail.storageEndDate}" readonly="true" />
                                          </div>
                                        </div>
                                        <div class="user__profile__button">
                                          <button type="button" id="completeBtn" class="button__type_2 button__color__green close_detailModal">닫기</button>
                                        </div>
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

});

console.log("detailButton: " + $detailButton);

$(".content__detail__btn").on('click', function () {
    console.log("click");
    const i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    const contentId = $detailButton.eq(i).parent().siblings('.content__id').text();
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});

/*=====================  목록 삭제 이벤트 =====================*/
/*======== 체크박스 이벤트 ========*/
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

/*======== 체크박스 이벤트 ========*/
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

/*======== 목록 삭제 ========*/
confirmButton.on('click', function(){
    $.ajax({
        url: "/admins/payment/delete",
        type: "post",
        data: {payIds : $checkArr},
        traditional : true,
        success : function (result) {
            if(result){
                location.reload();
            }
        }
    });
});