/* 슬라이드 */
$(".qna-slide-container").append(
    `
        <div class="qna-slide-wrapper">
            <div class="detail-pay-container">
                <h3 class="detail-pay-text">결제정보</h3>
            </div>
            <ul style="list-style-type: none;">
                <li class="detail-pay-content">
                    <span class="detail-pay-content-text">창고금액</span>
                    <span class="detail-pay-content-text-pay">
                        116,000
                        <span class="detail-pay-content-text-pay-next">원</span>
                    </span>
                </li>
                <li class="detail-pay-content">
                    <span class="detail-pay-content-text">결제금액</span>
                    <span class="detail-pay-content-text-pay">
                        116,000
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
                    <span class="detail-order-content-text-next">123456789</span>
                </li>
                <li class="detail-order-content">
                    <span class="detail-order-content-text">이용고객</span>
                    <span class="detail-order-content-text-next">김서현</span>
                </li>
                <li class="detail-order-content">
                    <span class="detail-order-content-text">결제일시</span>
                    <span class="detail-order-content-text-next">2023-03-20 17:28:32</span>
                </li>
            </ul>
        </div>
    `
);

$(".flip").click(function () {
    $(".panel").slideToggle(600);
});