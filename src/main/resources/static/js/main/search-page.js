$('.filter-select-button').click(function(){
    if($(this).find('.filter-path').attr('fill') == 'none') {
        $(this).find('.filter-path').attr('fill', '#5f0080');
    } else {
        $(this).find('.filter-path').attr('fill', 'none');
    }
});
/*
$('.place').mouseover(function() {
    $(this).attr('class', 'place-select');
}).mouseout(function() {
    $(this).attr('class', 'place');
});*/

/*$('.place-li a').click(function() {
    event.preventDefault();
    console.log("클릭됨");
    $(this).attr('class', 'place-select');
    $(this).attr('class', 'place');
/!*    if($(this).hasClass('place')) {
        console.log("place가지고 있음")
        $(this).attr('class', 'place-select');
    } else {
        console.log("place 안가지고 있음")
        $(this).attr('class', 'place');
    }*!/
});*/

$(function() {
    $('.place-ul a').click(function(event) {
        event.preventDefault();  // 기본 동작인 링크 이동을 막음

        // 클릭한 링크의 href 속성 값을 가져옴
        var href = $(this).attr('href');

        // 모든 링크의 클래스를 place로 초기화
        $('.place-ul a').removeClass('place-select').addClass('place');

        // 클릭한 링크의 클래스를 place-select로 변경
        $(this).removeClass('place').addClass('place-select');

        // 가져온 href 속성 값으로 페이지 이동
        window.location.href = href;
    });
});

/*
$(document).ready(function() {
    $('.place-li a').click(function() {
        // 현재 클릭한 요소의 클래스를 place-select로 변경
        $(this).removeClass('place').addClass('place-select');
        // 이전에 클릭한 요소의 클래스를 place로 변경
        $(this).parent().siblings().children('a').removeClass('place-select').addClass('place');
    });
});*/
