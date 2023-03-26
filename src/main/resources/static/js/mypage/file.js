/* ===================================================================================== */
/* ===================================================================================== */

globalThis.uuids;
FileList.prototype.forEach = Array.prototype.forEach;

/* 저장할 파일들의 Array */


const $photoPicker = $("#photo-picker");
const uploadAjaxConfig = (data) => {
    return {
        url: "/users/mypage/files/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}

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
            //전송에 성공하면 실행될 코드
            callback(result)
        },
        error: function () {
            console.log(config.data);
        }
    });
}

$photoPicker.on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();

    $files.forEach(file => formData.append("file", file));

    doAjax(uploadAjaxConfig(formData), (result) => {
        console.log(result)
        globalThis.uuids = result.uuids;
        $thumbnailWrap.eq(inquiryIndex).empty();
        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $thumbnailWrap.append(`<!--<a href="/files/download">--><img class="imageThumbnail" src="/users/mypage/files/display?fileName=${result.paths[i]}"><!--</a>-->`);
            } else $thumbnailWrap.append(`<!--<a>--><img style="width: 100px"/><!--</a>-->`);
        });
    });
});

$(".change-modal-ok-btn").on("click", function () {
    const $files = $photoPicker[0].files;

    $files.forEach((file, i) => {
        let fileVO = new Object();
        fileVO.fileOrgName = file.name;
        fileVO.fileUuid = globalThis.uuids[i];
        fileVO.fileType = file.type.startsWith("image");
        fileVO.inquiryId = inquiries[inquiryIndex].inquiryId
        fileVOs.push(fileVO);
    });

    let config = {
        url: "/users/mypage/files/save",
        method: "POST",
        data: JSON.stringify(fileVOs),
        contentType: "application/json; charset=utf-8",
    }

    doAjax(config, (result) => {
        console.log(fileVOs);
        alert("전송 성공!");
    });
});