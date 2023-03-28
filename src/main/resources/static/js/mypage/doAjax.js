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