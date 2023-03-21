
/* 클릭시 해당 이미지 띄우기 */
const $buttonTest = $('.sharebox-img-container button');
const $btn = $('.name');
const $background = $('.review-title-a');
console.log($buttonTest);

$buttonTest.on('click', function(){

    let $imgUrl = $(this).css("background-image").replace(/^url\(['"](.+)['"]\)/, '$1');
    let $image = $imgUrl.replace("http://127.0.0.1:5500","");


    console.log($image);
    $("#expandedImg").attr("src", $image);
    console.log($("#expandedImg"));
});

/* $('#review-color').on('click', function() {
    $('#review-color').css("color", "rgb(95, 0, 128)");
    $('review-c').css("color", "rgb(102, 102, 102)");
}); */



$('#review-backcolor').on('click', function() {
    $('#review-backcolor').css("background-color", "white");
    $('#review-a').css("background-color", "rgb(250, 250, 250)");
    $('#review-color').css("color", "rgb(95, 0, 128)");
    $('#review-c').css("color", "rgb(102, 102, 102)");
});


$('#review-a').on('click', function() {
    $('#review-a').css("background-color", "white");
    $('#review-backcolor').css("background-color", "rgb(250, 250, 250)");
    $('#review-c').css("color", "rgb(95, 0, 128)");
    $('#review-color').css("color", "rgb(102, 102, 102)");
});


/* $('review-c').on('click', function() {
    $('review-c').css("color", "rgb(95, 0, 128)");
    $('#review-color').css("color", "rgb(102, 102, 102)");

}); */