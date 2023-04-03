const $inputTitle = $('#inputTitle');
const $inputName = $('#inputName');
const $inputPhone = $('#inputPhone');
const $inputSize = $('.inputSize');
const $inputAddress = $('#address');
const $inputDetail = $('#inputDetail');
const $inputDateStart = $('#inputDateStart');
const $inputDateEnd = $('#inputDateEnd');
const $submitBtn = $('.submitButton');
// const $photoPicker = $("#photo-picker");
/*실제 저장할 fileVO들의 배열*/
let fileVOs = new Array();

let setStorageDTO = function() {
    const $files = $photoPicker[0].files;

    fileVOs = new Array();

    $files.forEach((file, i) => {
        let fileVO = new Object();
        fileVO.fileOrgName = file.name;
        fileVO.fileUuid = globalThis.uuids[i];
        fileVOs.push(fileVO);
    });
    /* 입력된 값을 가져와서 reviewDTO 객체 설정 */

    const storageDTO = {
        storageTitle: $("input[name='storageTitle']").val(),
        storageName: $("input[name='storageName']").val(),
        storagePhone: $("input[name='storagePhone']").val(),
        storageSize: $("input:radio[name=storageSize]:checked"). val(),
        storagePrice: $("input[name='storagePrice']").val(),
        storageAddress: $("input[name='storageAddress']").val(),
        storageAddressDetail: $("input[name='storageAddressDetail']").val(),
        storageUseDate: $("input[name='storageUseDate']").val(),
        storageEndDate: $("input[name='storageEndDate']").val(),
        storageAddressNumber: $('input[name=storageAddressNumber]').val(),

        files: fileVOs
    }

    return storageDTO;
}

$submitBtn.on("click", function () {
    checkRegion();
    console.log($('input[name=storageAddressNumber]').val())

    config = {
        url: `/storages/register`,
        method: "POST",
        data: JSON.stringify(setStorageDTO()),
        contentType: "application/json; charset=utf-8",
    }

    console.log("ㅇㅁㅇㅁㅇㅁㄴㅇㅁㄴㅇ");

    $doAjax(config, (result) => {
        location.href = result;
    });

});


    /* 리뷰 Id 확인 */
    /*storageId = reviews[contentIndex]?.reviewId;*/
 /* storageId = storageDTO.storageId
    /!* 새로 작성 *!/
    if(storageId)  {
        config = {
            url: `/storages/register`,
            method: "POST",
            data: JSON.stringify(storageDTO),
            contentType: "application/json; charset=utf-8",
        };
    }

    $doAjax(config, (result) => {
        location.href = result;
    });
});*/