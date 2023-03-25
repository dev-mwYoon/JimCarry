const boxContainer = $(".review-detail-container");
const boxStandards = [
    "우체국 5호 박스 24개",
    "우체국 5호 박스 60개",
    "우체국 5호 박스 120개",
    "우체국 5호 박스 150개"
];
const createDOM = function (box) {
    let text = `
    <div class="review-detail-wrap">
        <div class="review-detail-name-wrap">
            <span class="review-name">이**</span>
        </div>
    </div>

    <article class="review-content-wrap">
        <div>
            <div class="review-title-wrap">
                <h3 class="review-title">[일상味소] 다짐육 200g (냉장)</h3>
            </div>
                    <p class="review-context">처음 소고기미음 시작할땐 한우1등급 안심만 먹이다가 
            하나도아닌 둘이라 (쌍디에요) 비용이 후덜덜..
            그래서 안심에서 우둔살로 바꾸고 양늘면서 그마저도 부담이라 
            결국 컬리 2등급다짐육으로 왔네요ㅜㅜ
            나중에 알고보니 소고기는 1등급보단 2등급이 지방이적어
            아기들한텐 더좋다고 하더라구요~ 
            다행이 저희아가들은 저렴한 고기라도 잘먹어줘서
            오히려 부담없이 고기 팍팍넣어 만들어주고있어요^^;;
            처음 두팩사고 요번엔 4팩샀는데
            점점 먹는양이 확 늘어서 이것도 2주정도밖에 안갈듯하지만
            그래도 저렴하게 살수있어서 부담이 많이줄었어요
            컬리에서도 인기상품인지 품절이 잘떠서 기다렸다 샀네요ㅋㅋ
            50그램 저울달면서 큐브에 소분하면서 넣다보니까
            한팩당 200그램 살짝넘어요 (3~6그램정도 더되네요)
            샛별배송지역이 아니라 택배로 오다보니
            그나마 저희동네는 롯데택배가 점심전 오전에 오는데도
            이제 날이 풀려서그런가 많이 녹아서 오네요ㅠㅠ
            저녁에오는 택배사였음 못시키겠어요 
            이제 더워지면 어떡해야하나 벌써부터 걱정이..</p>
           
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
                    <span class="review-date">2018.03.20</span>
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

boxes.forEach((box, i) => {
    boxContainer.append(
        createDOM(box)
    );
});