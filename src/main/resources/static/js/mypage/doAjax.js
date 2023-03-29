const $doAjax = function (config, callback) {
    const isContentTypeDefined = config.contentType !== undefined;

    $.ajax({
        url: config.url,
        data: config.data,
        method: config.method,
        processData: config.processData !== false,
        contentType: isContentTypeDefined ? config.contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log(config.data);
        }
    });
}