const joinService = (function() {
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
    return { checkIdentification : checkIdentification, checkEmail : checkEmail, sendSMS : sendSMS };
})();