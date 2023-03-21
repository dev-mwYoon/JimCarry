
/* 소중대특대벼튼 클릭 */
$("#small-size-button").on('click', function(){
    $(".img-wrapper img").attr("src", "../../static/image/size-001.png");
    $('.size-button-select').attr('class', 'size-button');
    $(this).attr("class", "size-button-select");
})

$("#mid-size-button").on('click', function(){
    $(".img-wrapper img").attr("src", "../../static/image/size-002.png");
    $('.size-button-select').attr('class', 'size-button');
    $(this).attr("class", "size-button-select");
})


$("#large-size-button").on('click', function(){
    $(".img-wrapper img").attr("src", "../../static/image/size-003.png");
    $('.size-button-select').attr('class', 'size-button');
    $(this).attr("class", "size-button-select");
})

$("#extra-large-size-button").on('click', function(){
    $(".img-wrapper img").attr("src", "../../static/image/size-004.png");
    $('.size-button-select').attr('class', 'size-button');
    $(this).attr("class", "size-button-select");
})

/* const $sizeButton = $('.size-button');
HTMLCollection.prototype.forEach = Array.prototype.forEach;

$sizeButton.forEach((e) => {
    e.on('click', (ele) => {
        console.log($(ele.target).index());
    })
}); */