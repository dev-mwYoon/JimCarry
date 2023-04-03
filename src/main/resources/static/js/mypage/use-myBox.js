function numberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

payments.forEach(payment => {
    let date = payment.paymentDate.split(" ")[0];
    let time = payment.paymentDate.split(" ")[1];
    $(".mypage-list-right-list-container").append(
    `
    <div class="use-box-container">
        <div class="use-box-date-container">
            <span class="use-box-date">${date}</span>
        </div>
        <div class="use-box-content-container">
            <div class="use-box-content-left-container">
                <div class="use-box-content-left-list-container">
                    <d1 class="left-list-wrapper">
                        <dt class="left-list-text">창고규모</dt>
                        <dd class="left-list-text-content">[ ${payment.storageSize} ]</dd>
                    </d1>
                    <d1 class="left-list-wrapper">
                        <dt class="left-list-text">이용기간</dt>
                        <dd class="left-list-text-content">${payment.storageUseDate} ~ ${payment.storageEndDate}</dd>
                    </d1>
                    <d1 class="left-list-wrapper">
                        <dt class="left-list-text">결제금액</dt>
                        <dd class="left-list-text-content">${numberWithCommas(payment.paymentAmount)}원</dd>
                    </d1>
                </div>
            </div>
            <div class="use-box-content-right-container">
                <div class="right-btn-container">
                    <button type="button" class="right-btn flip">
                        <span class="right-btn-text">주문 상세보기</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="qna-slide-container panel">
        <div class="qna-slide-wrapper">
            <div class="detail-pay-container">
                <h3 class="detail-pay-text">결제정보</h3>
            </div>
            <ul style="list-style-type: none;">
                <li class="detail-pay-content">
                    <span class="detail-pay-content-text">창고금액</span>
                    <span class="detail-pay-content-text-pay">
                        ${numberWithCommas(payment.storagePrice)}
                        <span class="detail-pay-content-text-pay-next">원</span>
                    </span>
                </li>
                <li class="detail-pay-content">
                    <span class="detail-pay-content-text">결제금액</span>
                    <span class="detail-pay-content-text-pay">
                        ${numberWithCommas(payment.paymentAmount)}
                        <span class="detail-pay-content-text-pay-next">원</span>
                    </span>
                </li>
                <li class="detail-pay-content">
                    <span class="detail-pay-content-text">결제방법</span>
                    <span class="detail-pay-content-text-pay">
                        카카오페이(카카오페이머니)
                    </span>
                </li>
            </ul>
            <div class="detail-pay-container" style="padding-top: 70px;">
                <h3 class="detail-pay-text">주문정보</h3>
            </div>
            <ul style="list-style-type: none;">
                <li class="detail-order-content">
                    <span class="detail-order-content-text">예약번호</span>
                    <span class="detail-order-content-text-next">${date.split("-").join("")}${payment.payId}</span>
                </li>
                <li class="detail-order-content">
                    <span class="detail-order-content-text">이용고객</span>
                    <span class="detail-order-content-text-next">${$userName}</span>
                </li>
                <li class="detail-order-content">
                    <span class="detail-order-content-text">결제일시</span>
                    <span class="detail-order-content-text-next">${date} ${time}</span>
                </li>
            </ul>
        </div>
    </div>
    `
    );
    }
);

$(".flip").click(function () {
    let i = $(".flip").index($(this));
    $(".panel").eq(i).slideToggle(600);
});