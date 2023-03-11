$('.header-dropbox').mouseover(() => {
    $('.header-dropbox-ul').css('display', 'block');
}).mouseout(() => {
    $('.header-dropbox-ul').css('display', 'none');
});

$(window).scroll(function(){
    var scrollTop = $(window).scrollTop();
    if (scrollTop == 0) {
        $('.menu-fix-change').attr('class', 'menu-fix');
    } else {
        $('.menu-fix').attr('class', 'menu-fix-change');
    }
});