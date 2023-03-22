
$(".faq-list-table-content-title-layout").each((i,e) => {
    let $e = $(e);

    $(".faq-list-table-content-title-layout").eq(i).on("click",function(){
        $(".faq-detail-wrapper").eq(i).slideToggle("slow");;
    })
})