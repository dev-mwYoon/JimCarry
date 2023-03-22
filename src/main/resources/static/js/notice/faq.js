/* $(".faq-list-table-content-title-layout").on('click', () =>{
    $(".faq-detail-wrapper").css('display', 'flex');
}) */

/* 
$(document).ready(function(){
    $(".faq-list-table-content-title-layout").click( () =>{
        $(".faq-detail-wrapper").slideToggle("slow");
    })
}); */
// console.log($(".faq-list-table-content-title-layout").eq(0))
// console.log($(".faq-list-table-content-title-layout").eq(1))



$(".faq-list-table-content-title-layout").each((i,e) => {
    let $e = $(e);
    console.log($(".faq-list-table-content-title-layout").eq(i));
    $(".faq-list-table-content-title-layout").eq(i).on("click",function(){


        $(".faq-detail-wrapper").each( (index,e) => {
            let $d = $(e);
            $d.eq(i).slideToggle("slow");

        })
    })
})