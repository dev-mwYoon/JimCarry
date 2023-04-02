// /*부트페이 결제 API*/
// $('#payBtn').on('click', function() {
//     if(!agree){
//         alert('결제를 동의해주세요!');
//         return false;
//     }
// BootPay.request({
//     price: `${paymentAmount}`, //실제 결제되는 가격
//
//     // 관리자로그인 -> 결제설치 -> 인증키 및 보안 -> WEB Application ID
//     application_id: "6420f19a755e27001e7e35bc",
//
//     name: '짐캐리', //결제창에서 보여질 이름
//     pg: '',
//     method: 'card', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
//     show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
//     items: [
//         {
//             item_name: `${storageTitle}`, //상품명
//             qty: `${paymentMonth}`, //수량
//             unique: '123', //해당 상품을 구분짓는 primary key
//             price: `${paymentAmount}`, //상품 단가
//         }
//     ],
//     order_id: '고유order_id_1234', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
// }).error(function (data) {
//     //결제 진행시 에러가 발생하면 수행됩니다.
//     console.log(data);
// }).cancel(function (data) {
//     //결제가 취소되면 수행됩니다.
//     console.log(data);
// }).close(function (data) {
//     // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
//     console.log(data);
// }).done(function (data) {
//     //결제가 정상적으로 완료되면 수행됩니다
//     //비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
//     console.log(data);
// });
// });


/*----------------------------------------------------------------------------*/
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
          <td>${payments.paymentDate}</td>
          <td>
            <button class="content__detail__btn button__type_2 button__color__green">
              상세보기
            </button>
          </td>
        </tr>
`
    return text;
}
payments.forEach((payments, i) => {
    paymentTableContainer.append(
        createDOM(payments)
    );

});
$(".modal-stage").append(
    `
     <section class="modal">
      <div class="modal__header">
        <h3 class="modal__title">결제 상세보기</h3>
        <a class="modal-close">
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
              <h5>주문번호</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="12345" readonly="true" />
              </div>
            </div>
            <div class="user__profile">
              <h5>결제일자</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="2023-03-19" readonly="true" />
              </div>
            </div>
            <div class="user__profile">
              <h5>항목 1</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="신용카드" readonly="true" />
              </div>
            </div>
            <div class="user__profile">
              <h5>항목 2</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="신용카드" readonly="true" />
              </div>
            </div>
            <div class="user__profile">
              <h5>결제금액</h5>
              <div class="user__profile__input">
                <input type="text" name="" value="100,000원" readonly="true" />
              </div>
            </div>
            <div class="user__profile__button">
              <button id="completeBtn" class="button__type_2 button__color__green">작성완료</button>
            </div>
          </div>
        </main>
      </form>
    </section>
    `
);

console.log("detailButton: " + $detailButton);

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
        url: "/admin/payment/delete",
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