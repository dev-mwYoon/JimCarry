$('.filter-select-button').click(function(){
    if($(this).find('.filter-path').attr('fill') == 'none') {
        $(this).find('.filter-path').attr('fill', '#5f0080');
    } else {
        $(this).find('.filter-path').attr('fill', 'none');
    }
});

/*헤더 문자열 비교 리스트*/
const $region_list = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

/*검색페이지 지역별 문자열 비교 리스트*/
const region_list = ["전체보기", "서울특별시", "경기도", "강원도", "충청북도", "충청남도",
    "경상북도", "경상남도", "전라북도", "전라남도", "제주도"];
var way = 0;
$(".place").on('click', function() {
    console.log("들어온거:" + $(this).text())
    for (var i = 0; i < region_list.length; i++) {
        if(region_list[i] == $(this).text()) {
            $(".search-title").html($(this).text());
            way = i;
        }
    }
    console.log(way);
    // $(e).text() == region_list
})

/*지역별 클릭시 창고주소번호로 창고 목록 조회하기*/
$(function() {
    $('.place-ul a').click(function(event) {
        event.preventDefault();  // 기본 동작인 링크 이동을 막음

        $('.place-ul a').each((index, e) => {
            $('.place-ul a').eq(index).attr('class', 'place');
        })
        $(this).attr('class', 'place-select');

        $.ajax({
            type: "post",
            url: "/storages/search/list",
            data: { storageAddressNumber: way },
            success:function (result) {
                console.log("들어옴");
                console.log(result);
                var html = ``;
                var count = 0;
                $('.product').replaceWith(html);
                result.forEach(e => {
                        html += `
                        <a href="/storages/search/detail/${e.storageId}" class="product">
                            <div class="product-img-div">
                                <img src="https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/12/31/GKRZjZYBpPhE637765486789400308.jpg" class="product-img">
                            </div>
                            <div class="product-explain">
                                <span class="product-title">${e.storageTitle}</span>
                                <span class="product-short-explain">${e.storageAddress}</span>
                            </div>
                            <div class="product-price-div">
                                <span class="product-price">
                                    ${e.storagePrice}원
                                </span>
                            </div>
                            <div class="review-count-div">
                                <span class="review-img">
                                    <svg width="100%" height="100%" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <mask id="path-1-inside-1_1513_17755" fill="white">
                                            <path fill-rule="evenodd" clip-rule="evenodd" d="M3 2C1.89543 2 1 2.89543 1 4V8.67201C1 9.77658 1.89543 10.672 3 10.672H5.11212L6.33682 12.7653C6.5299 13.0954 7.00688 13.0954 7.19995 12.7653L8.42465 10.672H10.5C11.6046 10.672 12.5 9.77658 12.5 8.67201V4C12.5 2.89543 11.6046 2 10.5 2H3Z"></path>
                                        </mask>
                                        <path fill="#999" d="M5.11212 10.672L5.97526 10.167L5.68564 9.67201H5.11212V10.672ZM6.33682 12.7653L5.47369 13.2703L5.47369 13.2703L6.33682 12.7653ZM7.19995 12.7653L6.33682 12.2604L6.33682 12.2604L7.19995 12.7653ZM8.42465 10.672V9.67201H7.85113L7.56152 10.167L8.42465 10.672ZM2 4C2 3.44772 2.44772 3 3 3V1C1.34315 1 0 2.34315 0 4H2ZM2 8.67201V4H0V8.67201H2ZM3 9.67201C2.44772 9.67201 2 9.22429 2 8.67201H0C0 10.3289 1.34315 11.672 3 11.672V9.67201ZM5.11212 9.67201H3V11.672H5.11212V9.67201ZM7.19995 12.2604L5.97526 10.167L4.24899 11.177L5.47369 13.2703L7.19995 12.2604ZM6.33682 12.2604C6.5299 11.9304 7.00688 11.9304 7.19995 12.2604L5.47369 13.2703C6.05291 14.2604 7.48386 14.2604 8.06309 13.2703L6.33682 12.2604ZM7.56152 10.167L6.33682 12.2604L8.06309 13.2703L9.28779 11.177L7.56152 10.167ZM10.5 9.67201H8.42465V11.672H10.5V9.67201ZM11.5 8.67201C11.5 9.22429 11.0523 9.67201 10.5 9.67201V11.672C12.1569 11.672 13.5 10.3289 13.5 8.67201H11.5ZM11.5 4V8.67201H13.5V4H11.5ZM10.5 3C11.0523 3 11.5 3.44772 11.5 4H13.5C13.5 2.34315 12.1569 1 10.5 1V3ZM3 3H10.5V1H3V3Z" mask="url(#path-1-inside-1_1513_17755)"></path>
                                        <circle fill="#999" cx="4.34998" cy="6.17871" r="0.75"></circle>
                                        <circle fill="#999" cx="6.75" cy="6.17871" r="0.75"></circle>
                                        <circle fill="#999" cx="9.15002" cy="6.17871" r="0.75"></circle>
                                    </svg>
                                </span>
                                후기
                                <span class="review-number">${e.reviewCount}</span>
                            </div>
                        </a>
                    `;
                        count++;
                })
                $('.result-real-body').append(html);
                $('.total-number').html("총 " + count + "건");
            },
            error:function (e) {
                alert("통신에러");
            }
        });
    });
});

// 헤더의 지역별 클릭했을때 해당 지역별 색깔 바꾸기
/* 창고주소번호로 창고 목록 조회 */
$region_list.forEach((value, index) => {
    if(index == headerStorageAddressNumber) {
        console.log("index: " + index);
        console.log("storageAddressNumber: " + headerStorageAddressNumber);
        $('.place-ul a').eq(index).attr('class', 'place-select');
        $(".search-title").html($('.place-ul a').eq(index).html());
    }
    else{
        $('.place-ul a').eq(index).attr('class', 'place');
    }
});

/* 헤더 창고주소번호로 창고 목록 append */
/*목록이 추가될 div 부모*/
const listContainer = $(".result-real-body");

/* 목록이 추가될 div에 화면에서 필요한 필드멤버 뿌려주기 */
const createDOM = function (storage) {
    let text = `
    <a href="/storages/search/detail/${storage.storageId}" class="product">
        <div class="product-img-div">
            <img src="https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/12/31/GKRZjZYBpPhE637765486789400308.jpg" class="product-img">
        </div>
        <div class="product-explain">
            <span class="product-title">${storage.storageTitle}</span>
            <span class="product-short-explain">${storage.storageAddress}</span>
        </div>
        <div class="product-price-div">
            <span class="product-price">
                ${storage.storagePrice}원
            </span>
        </div>
        <div class="review-count-div">
            <span class="review-img">
                <svg width="100%" height="100%" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <mask id="path-1-inside-1_1513_17755" fill="white">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M3 2C1.89543 2 1 2.89543 1 4V8.67201C1 9.77658 1.89543 10.672 3 10.672H5.11212L6.33682 12.7653C6.5299 13.0954 7.00688 13.0954 7.19995 12.7653L8.42465 10.672H10.5C11.6046 10.672 12.5 9.77658 12.5 8.67201V4C12.5 2.89543 11.6046 2 10.5 2H3Z"></path>
                    </mask>
                    <path fill="#999" d="M5.11212 10.672L5.97526 10.167L5.68564 9.67201H5.11212V10.672ZM6.33682 12.7653L5.47369 13.2703L5.47369 13.2703L6.33682 12.7653ZM7.19995 12.7653L6.33682 12.2604L6.33682 12.2604L7.19995 12.7653ZM8.42465 10.672V9.67201H7.85113L7.56152 10.167L8.42465 10.672ZM2 4C2 3.44772 2.44772 3 3 3V1C1.34315 1 0 2.34315 0 4H2ZM2 8.67201V4H0V8.67201H2ZM3 9.67201C2.44772 9.67201 2 9.22429 2 8.67201H0C0 10.3289 1.34315 11.672 3 11.672V9.67201ZM5.11212 9.67201H3V11.672H5.11212V9.67201ZM7.19995 12.2604L5.97526 10.167L4.24899 11.177L5.47369 13.2703L7.19995 12.2604ZM6.33682 12.2604C6.5299 11.9304 7.00688 11.9304 7.19995 12.2604L5.47369 13.2703C6.05291 14.2604 7.48386 14.2604 8.06309 13.2703L6.33682 12.2604ZM7.56152 10.167L6.33682 12.2604L8.06309 13.2703L9.28779 11.177L7.56152 10.167ZM10.5 9.67201H8.42465V11.672H10.5V9.67201ZM11.5 8.67201C11.5 9.22429 11.0523 9.67201 10.5 9.67201V11.672C12.1569 11.672 13.5 10.3289 13.5 8.67201H11.5ZM11.5 4V8.67201H13.5V4H11.5ZM10.5 3C11.0523 3 11.5 3.44772 11.5 4H13.5C13.5 2.34315 12.1569 1 10.5 1V3ZM3 3H10.5V1H3V3Z" mask="url(#path-1-inside-1_1513_17755)"></path>
                    <circle fill="#999" cx="4.34998" cy="6.17871" r="0.75"></circle>
                    <circle fill="#999" cx="6.75" cy="6.17871" r="0.75"></circle>
                    <circle fill="#999" cx="9.15002" cy="6.17871" r="0.75"></circle>
                </svg>
            </span>
            후기
            <span class="review-number">${storage.reviewCount}</span>
        </div>
    </a>
`
    return text;
}
/* html script에서 받아준 모델객체 */
storage.forEach((storage, i) => {
    listContainer.append(
        createDOM(storage)
    );
});




