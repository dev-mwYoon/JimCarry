// 검색창에서 키보드를 눌렀을 때
$("form[name='searchForm']").on('submit', function (e) {
    e.preventDefault();
    loadAll();
});

$('.search-detail-address').on("keyup", function (e) {
    if(e.keyCode === 8 && $(this).val().length == 0){
        loadAll();
    }
});

function loadAll() {

    showKeyword();
    showOrder();
    showSize();

    load();
}

loadAll();

$('.search-button').on('click', loadAll) // 검색 버튼 클릭 시
$('.array-li').on('click', function () { // order 선택 시
    // let i = $('.array-li').index($(this));
    $('.array-li').each(function (i, o) {
        $(o).find("a").attr('class', 'array-a'); // css 변경
    })
    $(this).find("a").attr('class', 'array-a-select'); // css 변경

    loadAll();
});


function showKeyword() {
    keyword = $('.search-detail-address').val();
}

function showOrder() {
    order = $('.array-a-select').text().replace(/\s/g, '');
}

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









