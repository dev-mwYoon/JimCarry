$(document).ready(function() {
    // 검색창에서 키보드를 눌렀을 때
    $('.search__searchbox__form').on('keydown', function(e) {
        if (e.keyCode == 13) { // Enter 키를 눌렀을 때
            e.preventDefault(); // 기본 이벤트 막기
        }
    });
});




loadAll();

$('.search-button').on('click', filter) // 검색 버튼 클릭 시
$('.search-detail-address').on('keyup', filter) // input 태그 입력 시
$('.filter-select-button').on('click', filter) // size 태그 버튼 클릭 시
// $('.array-li').on('click', function(){ // order 선택 시
//     console.log("여긴 왜 안들어와?");
//     // let i = $('.array-li').index($(this));
//     $('.array-li').each(function(i,o){
//         $(o).find("a").attr('class', 'array-a'); // css 변경
//     })
//     $(this).find("a").attr('class', 'array-a-select'); // css 변경
//
//     loadAll();
// })

function clickOrder(event) {
    console.log("왜 안들어옴?");
    $('.array-li a').each(function(i,o){
        $(o).attr('class', 'array-a'); // css 변경
    })
    $(event).find("a").attr('class', 'array-a-select'); // css 변경

    console.log($('.array-a-select').val());
    loadAll();
}


function showKeyword() {
    keyword = $('.search-detail-address').val();
    // page = 1
    // load();
}

function showOrder() {
    order = $('.array-a-select').val();
    // page = 1
    // load();
}

function showSize() {
    size = new Array();
    let $lis = $('.filter-select-li')
    $lis.each(function(i,e){
        var condition = $lis.find("svg").eq(0).attr('fill') == "#5F0080"
        if(condition) {
            size.push($lis.find("span").val());
        } else {
            size = size.filter(function(value) {
                return value !== $lis.find("span").val();
            });
        }
    })

}

function filter(event) {
    event.preventDefault();
    showKeyword();
    showOrder();
    showSize();
    console.log(page);
    console.log(keyword);
    console.log(order);
    console.log(size);

    load();
}

function loadAll() {
    console.log("loadAll 여긴 들어왔?");

    showKeyword();
    showOrder();
    showSize();

    console.log(page);
    console.log(keyword);
    console.log(order);
    console.log(size);

    load();
}







