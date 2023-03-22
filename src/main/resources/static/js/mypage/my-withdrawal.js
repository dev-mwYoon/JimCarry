// 동의 버튼 효과
const $checkboxes = $('.termCheckBox');
const $path = $('.path1');

$checkboxes.each((i,e)=>{
    $(e).parent().on('click', function(){
        var $ischecked = $(e).is(':checked');
        if($ischecked){
            $path.eq(i).attr('fill', '#fff');
            $(e).prop('checked', false);
        }else{
            $path.eq(i).attr('fill', '#5f0080');
            $(e).prop('checked', true);
            
        }
    });
});