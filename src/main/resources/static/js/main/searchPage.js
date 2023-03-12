$('.filter-select-button').click(function(){
    if($(this).find('.filter-path').attr('fill') == 'none') {
        $(this).find('.filter-path').attr('fill', '#5f0080');
    } else {
        $(this).find('.filter-path').attr('fill', 'none');
    }
});

$('.place').mouseover(function() {
    $(this).attr('class', 'place-select');
}).mouseout(function() {
    $(this).attr('class', 'place');
});