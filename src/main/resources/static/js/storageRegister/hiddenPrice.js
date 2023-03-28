const $smallBtn = $('#sizeSmall');
const $middleBtn = $('#sizeMiddle');
const $sizeBigBtn = $('#sizeBig');
const $sizeBBigBtn = $('#sizeBBig');
const $sizePrice = $('#hidePrice');

$smallBtn.click(function () {
    console.log("들어옴");
    $sizePrice.val(24000);
});


$middleBtn.click(function () {
    console.log("들어옴2");
    $sizePrice.val(56000);
});


$sizeBigBtn.click(function () {
    console.log("들어옴3");
    $sizePrice.val(88000);
});

$sizeBBigBtn.click(function () {
    console.log("들어옴4");
    $sizePrice.val(120000);
});

