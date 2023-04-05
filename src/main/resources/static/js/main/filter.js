$(document).ready(function() {
    // 검색창에서 키보드를 눌렀을 때
    $('.search__searchbox__form').on('keydown', function(e) {
        if (e.keyCode == 13) { // Enter 키를 눌렀을 때
            e.preventDefault(); // 기본 이벤트 막기
        }
    });
});
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



loadAll();

$('.search-button').on('click', filter) // 검색 버튼 클릭 시
$('.search-detail-address').on('keyup', filter) // input 태그 입력 시
$('.filter-select-button').on('click', filter) // size 태그 버튼 클릭 시
$('.array-li').on('click', function(){ // order 선택 시
    console.log("여긴 왜 안들어와?");
    // let i = $('.array-li').index($(this));
    $('.array-li').each(function(i,o){
        $(o).find("a").attr('class', 'array-a'); // css 변경
    })
    $(this).find("a").attr('class', 'array-a-select'); // css 변경

    loadAll();

});



function showKeyword() {
    keyword = $('.search-detail-address').val();
    // page = 1
    // load();
}

function showOrder() {
    order = $('.array-a-select').text().replace(/\s/g, '');
    // page = 1
    // load();
}

$(".filter-select-button").on('click', function(){
    console.log($(this).data("check"));
    var condition = $(this).data("check") === 0;
    if(condition) {
        $(this).data("check", 1);
    } else {
        $(this).data("check", 0);
    }

    loadAll();
})

function showSize() {
    size = new Array();
    let $lis = $(".filter-select-button")
    $lis.each(function(i,e){
        var condition = $(e).data("check") === 1;
        console.log("==========");
        console.log("condition");
        console.log(condition);
        if (condition) {
            size.push($(e).next().text());
        }
    })
}


// size = new Array();
// function showSize() {
//     let $lis = $(".filter-select-button")
//     $lis.on("click", function () {
//         console.log($(this).next().text());
//         let sizeOfBox = $(this).next().text();
//         if (size.indexOf(sizeOfBox) === -1) { // 중복값 검사
//             size.push(sizeOfBox);
//         }
//         console.log(size)
//     })
// }
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









