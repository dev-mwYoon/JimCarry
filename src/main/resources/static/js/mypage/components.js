
const ajaxErrorMsgs = {
    identification : "중복된 아이디입니다.",
    password : "현재 비밀번호가 아닙니다.",
    email : "중복된 이메일입니다."
}
const ajaxSuccessMsgs = {
    identification : "사용 가능한 아이디입니다.",
    email : "사용 가능한 이메일입니다."
}

/**
 * Ajax용 아이디 중복체크 설정값 반환 함수
 *
 * @param data 보낼 데이터
 * */
const checkIdentificationAjaxConfig = (data) => {
    return {
        url: "/users/mypage/checkIdentification",
        method: "POST",
        data: JSON.stringify({userIdentification: data}),
    }
}

/**
 * Ajax용 현재 비밀번호 설정값 반환 함수
 *
 * @param data 보낼 데이터
 * */
const checkPasswordAjaxConfig = (data) => {
    return {
        url: "/users/mypage/checkPassword",
        method: "POST",
        data: JSON.stringify({userPassword: data}),
    }
}

/**
 * Ajax용 이메일 중복체크 설정값 반환 함수
 *
 * @param data 보낼 데이터
 * */
const checkEmailAjaxConfig = (data) => {
    return {
        url: "/users/mypage/checkEmail",
        method: "POST",
        data: JSON.stringify({userEmail: data}),
    }
}

/**
 * ajax 모듈
 * @param config ajax 설정 객체
 * @param callback success시 콜백함수
 * */
const doAjax = function (config, callback) {
    let isContentType = true;
    let isProcesData = true;

    /* contentType과 processData의 false 값이 제대로 들어가게끔 조건문 설정 */
    if (!config.contentType) {
        isContentType = config.contentType === false ? true : false;
    }
    if (!config.processData) {
        isProcesData = config.processData === false ? true : false;
    }

    console.log("으으악으가악");

    $.ajax({
        url: config.url,
        data: config.data,
        method: config.method,
        processData: isProcesData ? config.processData : true,
        contentType: isContentType ? config.contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log(config.data);
        }
    });
}