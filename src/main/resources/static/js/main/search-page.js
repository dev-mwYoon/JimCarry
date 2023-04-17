let page = 1;
let keyword = "";
let order;
let size;

$('.filter-select-button').click(function(){
    if($(this).find('.filter-path').attr('fill') == 'none') {
        $(this).find('.filter-path').attr('fill', '#5f0080');
    } else {
        $(this).find('.filter-path').attr('fill', 'none');
    }
});

/*헤더 문자열 비교 리스트*/
const $region_list = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

/*검색페이지 지역별 문자열 `비교 리스트*/
const region_list = ["전체보기", "서울특별시", "경기도", "강원도", "충청북도", "충청남도",
    "경상북도", "경상남도", "전라북도", "전라남도", "제주도"];
var way = 0;
function selectPlace() {
    for (var i = 0; i < region_list.length; i++) {
        if(region_list[i] == $('.place-select').text()) {
            $(".search-title").html($('.place-select').text());
            way = i;
        }
    }
    // $(e).text() == region_list

}

/*지역별 클릭시 클래스 바꾸기*/
$(function() {
    $('.place-ul a').click(function(e){
        e.preventDefault();
        $('.place-ul a').each((index, e) => {
            $('.place-ul a').eq(index).attr('class', 'place');
        })
        $(this).attr('class', 'place-select');
        page = 1;
        loadAll();
    });
});

/* ajax 부르는 함수*/
function load() {

    selectPlace(); // way 값 찾는 함수
    way *= 1; // number로 타입 변경, way = way * 1
    page *= 1; // number로 타입 변경, page = page * 1
    $.ajax({
        type: "post",
        url: "/storages/search/list",
        contentType: 'application/json',
        dataType : 'json',
        data: JSON.stringify({
            "storageAddressNumber": way,
            "page": page,
            "keyword": keyword,
            "size": size,
            "order": order,
        }),
        success:function (result) {
            /*console.log(result);
            console.log(result.storageList)
            console.log(result.total)*/
            showList(result);
            showPage(result);
            console.log("result");
            console.log(result);
        },
        error:function (e) {
            alert("통신에러");
        }
    });
}

/* 목록 조회 */
function showList(result) {
    var html = ``;
    var paging = ``;
    $('.product').replaceWith(html);
    $('.product').replaceWith(paging);
    (result.paginationDTO.storageDTO).forEach(e => {
        html += `
                        <a href="/storages/search/detail/${e.storageId}" class="product">
                            <div class="product-img-div">
                                <img src="/storages/search/files/display?fileName=${e.files[0].filePath + '/' + e.files[0].fileUuid + '_' + e.files[0].fileOrgName}">
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
    })
    $('.result-real-body').append(html);


}

function showPage(result) {
    // 페이징 HTML 태그를 추가하는 코드 작성
    var paging = "";
    paging += `
                        <div class="paging" style="text-align: center">
                       `
    if(result.paginationDTO.pageDTO.prev) {
        paging += `
                        <a class="changePage" data-page="${result.paginationDTO.pageDTO.startPage - 1}" onclick="preventDefault(this)" style="color: black"><code><</code></a>
                        `;
    }
    for (let i = 1; i <= result.paginationDTO.pageDTO.endPage; i++) {
        let page = i;
        if(result.paginationDTO.pageDTO.criteria.page != page) {
            paging += `
                    <a class="changePage" data-page="${page}" onclick="findPage(this)" style="color: black" ><code>${page}</code></a>
                     `
        }
        else {
            paging += `
                            <code id="currentPage">${page}</code>
                        `
        }
    }
    if(result.paginationDTO.pageDTO.next) {
        paging += `
                        <a class="changePage" data-page="${result.paginationDTO.pageDTO.endPage + 1}" onclick="findPage(this)" style="color: black"><code>></code></a>
                    `
    }

    paging += `
                    </div>
                    <form action="/storages/search" method="get" name="pageForm">
                        <input type="hidden" name="page" value="${result.paginationDTO.pageDTO.criteria.page}">
                        <input type="hidden" name="amount" value="${result.paginationDTO.pageDTO.criteria.amount}">
                    </form>
                `
    $('.paging-div').html(paging); // 생성된 HTML 태그를 product 클래스를 가진 DOM에 추가합니다.
    $('.total-number').html("총 " + result.total + "건");


}

function findPage(currentPage) {
    // currentPage.preventDefault();
    page = currentPage.dataset.page;
    page *= 1;
    // console.log(typeof page);
    loadAll(); //현재 페이지에서 모든 데이터를 로드하고 화면에 표시하는 역할
}


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



