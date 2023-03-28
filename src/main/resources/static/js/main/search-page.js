$('.filter-select-button').click(function(){
    if($(this).find('.filter-path').attr('fill') == 'none') {
        $(this).find('.filter-path').attr('fill', '#5f0080');
    } else {
        $(this).find('.filter-path').attr('fill', 'none');
    }
});

/*지역별 클릭시 클래스 바꾸기*/
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
        location.href = "/storages/search/?storageAddress=" + href;
    });
});

// 헤더의 지역별 클릭했을때 해당 지역별 색깔 바꾸기
const $region_list = ["전체", "서울", "경기", "강원", "충북", "충남",
    "경북", "경남", "전북", "전남", "제주특별자치도"];

$region_list.forEach((value, index) => {
    console.log("value :" + value);
    if(value === storageAddress) {
        console.log("주소 똑같음")
        $('.place-ul a').eq(index).attr('class', 'place-select');
    }
    else{
        $(this).attr('class', 'place');
    }
})

/*
storageAddress*/
