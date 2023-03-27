const $numberElement = $('.count-c');
const $plusBtn = $('.count-up');
const $minusBtn = $('.count-down');
const $count = $('.detail-price').text();
const $price = $('.detail-price');
console.log($count);
console.log($numberElement);
let $number = $numberElement.text();
console.log($number);

/* 월 차이 구하기 */
const startDate = storages.storageUseDate;
const endDate = storages.storageEndDate;

const start_date_arr = startDate.split("-");
const end_date_arr = endDate.split("-");

const numberOfMonths = (end_date_arr[0] - start_date_arr[0]) * 12 + (end_date_arr[1] - start_date_arr[1]) + 1;

/* detail-date-use 클래스 text에 numberOfMonths 넣어주기 */
$(".detail-date-use").text(numberOfMonths + "개월");

/* 이용가능한 개월수 초과하면 막기 */
$plusBtn.on("click", function() {
    if($number < numberOfMonths) {
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