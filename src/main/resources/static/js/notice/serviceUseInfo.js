/* 소중대특대벼튼 클릭 */
const $sizeButton = $('button[type=button]');

$sizeButton.each((i, e) => {
    let $e = $(e);
    $e.on('click', () => {
        $('.size-button-select').attr('class', 'size-button');
        $e.attr("class", "size-button-select");
        $(".img-wrapper img").attr("src", "/image/size-00" + i + ".png");
    })
});