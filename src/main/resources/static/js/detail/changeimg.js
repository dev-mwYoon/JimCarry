





// function myFunction() {
//     console.log("들옴");
//     // Get the expanded image
//     var $expandImg = $("#expandedImg");
//     // let buttons = document.querySelector('.review-img').style.backgroundImage;
//     let $buttons = $('#review-img').css("background-image");
//     let $url = $buttons.replace(/^url\(['"](.+)['"]\)/, '$1');
//     let $image = $url.replace("http://127.0.0.1:5500","");
//     console.log($buttons);
//     console.log($url);
//     console.log($image);
//     // Get the image text
//     /* var imgText = document.getElementById("imgtext"); */
//     // Use the same src in the expanded image as the image being clicked on from the grid
    
//   /*   $buttons = "url('../static/image/chocoloate.jpg')"; */
    
//     // expandImg.src =  buttons.style.backgroundImage;
//     /* $expandImg.src = $image;
//     console.log($expandImg.src); */

//     // Use the value of the alt attribute of the clickable image as text inside the expanded image
//    /*  imgText.innerHTML = imgs.alt; */
//     // Show the container element (hidden with CSS)
//      ("$expandImg").attr("src", $image);
// } 

// let $buttons = $('#review-img');
// $buttons.on('click', function(){
//     let $imgUrl = $(this).css("background-image").replace(/^url\(['"](.+)['"]\)/, '$1');
//     let $image = $imgUrl.replace("http://127.0.0.1:5500","");

//     console.log($image);
//     $("#expandedImg").attr("src", $image);
//     console.log($("#expandedImg"));
// });

const $buttonTest = $('button[type=button]');
console.log($buttonTest);

$buttonTest.on('click', function(){
    /* let i = $buttonTest.index($(this)); */


    let $imgUrl = $(this).css("background-image").replace(/^url\(['"](.+)['"]\)/, '$1');
    let $image = $imgUrl.replace("http://127.0.0.1:5500","");


    console.log($image);
    $("#expandedImg").attr("src", $image);
    console.log($("#expandedImg"));
});


//