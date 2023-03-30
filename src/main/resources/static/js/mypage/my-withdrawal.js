// 동의 버튼 효과
const $checkboxes = $('.termCheckBox');
const $path = $('.path1');

let check = false;

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

$(".amend-password-inputBox-in").on("blur", function () {
    doAjax({
        url: "/users/mypage/checkPassword",
        method: "POST",
        data: JSON.stringify({userPassword: $(this).val()}),
        contentType: "application/json; charset=utf-8"
    }, (result) => {
        if(result == true){
            $(".errorDiv").css("display", "none");
            check = true;
        } else {
            $(".errorDiv").css("display", "block");
            $(".errorMessage").css("color", "red");
            check = false;
        }
    });
})

$(".withdrawal-right-btn").on("click", function () {
    check ?
        setTimeout(() => {$("form[name='unregister-form']").submit()}, 1500) : () => {};
})
