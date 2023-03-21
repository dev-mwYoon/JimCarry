const $numberElement = $('.count-c');
const $plusBtn = $('.count-up');
const $minusBtn = $('.count-down');
const $count = $('.detail-price').text();
const $price = $('.detail-price');
console.log($count);
console.log($numberElement);
let $number = $numberElement.text();
console.log($number);

$plusBtn.on("click", function() {
    if($number < 12) {
        ++$number;
    }
    $numberElement.text($number);
    console.log($number);

    console.log("들어옴");
    console.log($count);
    let $resultPrice;
    $resultPrice = $number * parseInt($count);
    console.log($resultPrice);
    $price.text($resultPrice);

    

});


$minusBtn.on("click", function() {
    console.log($number);
    
    if( $number > 1) {
        --$number;
     console.log($number);
    }

    $numberElement.text($number);
    console.log($number);

    console.log("들어옴");
    console.log($count);
    let $resultPrice;
    $resultPrice = $number * parseInt($count);
    console.log($resultPrice);
    $price.text($resultPrice);

});
