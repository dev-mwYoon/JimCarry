const overlapService = (function() {
    function checkIdentification(userIdentification, callback) {
        $.ajax({
            url: "/user/identifications-duplicate",
            type: "post",
            data: { userIdentification : userIdentification },
            success: function(result) {
                if(callback) {
                    callback(result);
                }
            }
        });
    }

    function checkEmail(userEmail, callback) {
        $.ajax({
            url: "/user/emails-duplicate",
            type: "post",
            data: { userEmail : userEmail },
            success: function(result) {
                if(callback) {
                    callback(result);
                }
            }
        });
    }

    function sendSMS(callback) {
        $.ajax({
            url: "/user/send-sms",
            type: "get",
            data: { userPhone : $('input[name=userPhone]').val() },
            success: function(result) {
                if(callback) {
                    callback(result);
                }
            }
        });
    }

    function startTimer(count, display) {
        var minutes, seconds;
        timer = setInterval(function () {
            minutes = parseInt(count / 60, 10);
            seconds = parseInt(count % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.html(minutes + ":" + seconds);

            // 타이머 끝
            if (--count < 0) {
                clearInterval(timer);
                display.html("00:00");
                $modal.css('visibility', 'visible');
                $modalText.text("유효시간이 만료되었습니다.\n다시 시도해주세요.");
                $verifiCheckBtn.attr('disabled', true);
            }
        }, 1000);
    }

    function findByPhone() {
        $verifiCheckBtn.attr('disabled', false);
        clearInterval(timer);

        overlapService.sendSMS(function(result) {
            console.log(result);
            authNumber = result;
        });

        var display = $(".verification-timer");
        // 유효시간 설정
        var leftSec = 180;

        overlapService.startTimer(leftSec, display);
    }

    function checkAuthNumber() {
        if($('input[name=verificationNumber]').val() == authNumber) {
            $modal.css('visibility', 'visible');
            $modalText.text("인증확인이 완료되었습니다.");
            clearInterval(timer);
            $checkButton.on('click',()=>{
                $modal.css('visibility', 'hidden');
                $('#findForm').submit();
            });
        } else {
            $modal.css('visibility', 'visible');
            $modalText.text("인증번호가 일치하지 않습니다.");
            $checkButton.on('click',()=>{
                $modal.css('visibility', 'hidden');
            });
        }
    }
    return { checkIdentification : checkIdentification, checkEmail : checkEmail, sendSMS : sendSMS, startTimer: startTimer,
        findByPhone : findByPhone, checkAuthNumber : checkAuthNumber};
})();