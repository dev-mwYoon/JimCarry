
/* 클릭시 해당 이미지 띄우기 */
const $buttonTest = $('.sharebox-img-container button');
console.log($buttonTest);

$buttonTest.on('click', function(){

    let $imgUrl = $(this).css("background-image").replace(/^url\(['"](.+)['"]\)/, '$1');
    let $image = $imgUrl.replace("http://127.0.0.1:5500","");


    console.log($image);
    $("#expandedImg").attr("src", $image);
    console.log($("#expandedImg"));
});



