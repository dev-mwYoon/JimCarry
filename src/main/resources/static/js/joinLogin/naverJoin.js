var naver_id_login = new naver_id_login("EVE2RFzPw29yZMdwRAx9", "http://localhost:10000/main/");
// 접근 토큰 값 출력
naver_id_login.oauthParams.access_token;
// 네이버 사용자 프로필 조회
naver_id_login.get_naver_userprofile("naverSignInCallback()");
// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
function naverSignInCallback() {
    $('input[name=userName]').val(naver_id_login.getProfileData('name'));
    $('input[name=userEmail]').val(naver_id_login.getProfileData('email'));

    if(naver_id_login.getProfileData('gender') == 'M') {
        $($('.genderRadio')[0]).prop('checked', true);
        $('.man').attr('class', 'radioSpanClick');
        $('.man2').attr('class', 'radioBoxDivClick');
    }
    if(naver_id_login.getProfileData('gender') == 'F') {
        $($('.genderRadio')[1]).prop('checked', true);
        $('.woman').attr('class', 'radioSpanClick');
        $('.woman2').attr('class', 'radioBoxDivClick');
    }
    if(naver_id_login.getProfileData('gender') == 'U') {
        $($('.genderRadio')[2]).prop('checked', true);
        $('.none').attr('class', 'radioSpanClick');
        $('.none2').attr('class', 'radioBoxDivClick');
    }

    $('input[name=userStatus]').val(2);
    // alert(naver_id_login.getProfileData('name'));
    // alert(naver_id_login.getProfileData('email'));
    // alert(naver_id_login.getProfileData('gender'));
}