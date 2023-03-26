/*목록이 추가될 div 부모*/
const reviewContainer = $(".review-detail-container");

/* 목록이 추가될 div에 화면에서 필요한 필드멤버 뿌려주기 */
const createDOM = function (review) {
    let text = `
    <div class="review-detail-wrap">
        <div class="review-detail-name-wrap">
            <span class="review-name">${review.userName}</span>
        </div>
    </div>

    <article class="review-content-wrap">
        <div>
            <div class="review-title-wrap">
                <h3 class="review-title">${review.reviewTitle}</h3>
            </div>
                    <p class="review-context">${review.reviewContext}</p>
           
            <!-- 리뷰사진 -->
            <div class="review-img-container">
                <img class="review-photo">
                <img class="review-photo">
                <img class="review-photo">
                <img class="review-photo">
               <!--  <button class="review-img"></button>
                <button class="review-img"></button>
                <button class="review-img"></button> -->
            </div>


            <footer class="review-register-container">
                <div>
                    <span class="review-date">${review.reviewEditDate}</span>
                </div>
                <!--
                <button class="css-g3a39p e198bwfo1">
                    <span class="ico css-xdee1z e198bwfo0"></span>
                    <span>도움돼요 39</span>
                </button> -->
            </footer>
        </div>
    </article>
`
    return text;
}
/* html script에서 받아준 모델객체 */
reviews.forEach((review, i) => {
    reviewContainer.append(
        createDOM(review)
    );
});