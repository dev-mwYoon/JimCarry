//웹 페이지에서의 검색 기능

// 검색창에서 엔터 쳤을 때 loadAll()함수를 호출해서 검색 목록 보여주기
$("form[name='searchForm']").on('submit', function (e) {
    e.preventDefault();
    loadAll();
});

// 검색어 입력란에서 백스페이스 키가 눌렸을 때, 검색어가 없을 경우 loadAll() 함수를 호출
// keyCode === 8 이면 백스페이스
//키를 누를 때는 keydown 키를 놓을 때는 keyup
$('.search-detail-address').on("keyup", function (e) {
    if(e.keyCode === 8 && $(this).val().length == 0){
        loadAll();
    }
});

// loadAll() 함수를 호출하여 필터링 조건을 적용한 검색 결과
function loadAll() {

    showKeyword(); //검색어
    showOrder();  //정렬순서
    showSize();  //규모

    load(); //// 검색 결과 로드
}

// 페이지 로딩 시, 초기 검색 결과를 로드
loadAll();

$('.search-button').on('click', loadAll) // 검색 버튼 클릭 시 loadAll() 함수를 호출

$('.array-li').on('click', function () { // order 선택 시

    // 정렬 순서를 선택할 수 있는 메뉴에서 항목을 클릭했을 때, css를 변경하고 loadAll() 함수를 호출
    $('.array-li').each(function (i, o) {
        $(o).find("a").attr('class', 'array-a'); // css 변경
    })
    $(this).find("a").attr('class', 'array-a-select'); // css 변경

    loadAll();
});

// 검색어를 보여주는 함수
function showKeyword() {
    keyword = $('.search-detail-address').val();
}

// 정렬 순서를 보여주는 함수
function showOrder() {
    order = $('.array-a-select').text().replace(/\s/g, '');
}

// 규모 선택하는 버튼을 클릭했을 때, loadAll() 함수를 호출
$(".filter-select-button").on('click', function () {
    var condition = $(this).data("check") === 0;
    console.log("====인덱스==" + $(".filter-select-button").index($(this)));
    console.log("====컨디션==" + condition);
    if (condition) {
        $(this).data("check", 1);
    } else {
        $(this).data("check", 0);
    }
    loadAll();
});


// 선택한 규모를 보여주는 함수
function showSize() {
    size = new Array();
    let $lis = $(".filter-select-button")
    $lis.each(function (i, e) {
        var condition = $(e).data("check") === 1;
        if (condition) {
            size.push($(e).next().text());
        }
    })
}









