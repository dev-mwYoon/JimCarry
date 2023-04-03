const $submitBtn = $('.submit-button');



/*실제 저장할 fileVO들의 배열*/

let fileVOs = new Array();

let setInquiryDTO = function() {
    const $files = $photoPicker[0].files;

    fileVOs = new Array();

    $files.forEach((file, i) => {
        let fileVO = new Object();
        fileVO.fileOrgName = file.name;
        fileVO.fileUuid = globalThis.uuids[i];
        fileVOs.push(fileVO);
    });
    /* 입력된 값을 가져와서 reviewDTO 객체 설정 */

    const inquiryDTO = {
        inquiryTitle: $("input[name='inquiryTitle']").val(),
        inquiryContent: $("textarea[name='inquiryContent']").val(),

        files: fileVOs
    }

    return inquiryDTO;
}

$submitBtn.on("click", function () {
  /*  checkRegion();
    console.log($('input[name=storageAddressNumber]').val())*/

    config = {
        url: `/notice/write`,
        method: "POST",
        data: JSON.stringify(setInquiryDTO()),
        contentType: "application/json; charset=utf-8",
    }

    console.log("ㅇㅁㅇㅁㅇㅁㄴㅇㅁㄴㅇ");

    $doAjax(config, (result) => {
        location.href = result;
    });

});