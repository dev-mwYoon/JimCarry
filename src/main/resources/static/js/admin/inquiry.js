const inquiryTableContainer = $(".table");

const createDOM = function(inquiries){
    let text =
        `<tr class="table__content">
          <td>
              <label class="check-label">
                <input type="checkbox" name="check" />
              </label>
          </td>
          <td class="content__id">${inquiries.inquiryId}</td>
          <td>${inquiries.userIdentification}</td>
          <td>${inquiries.userEmail}</td>
          <td>${inquiries.inquiryRegist}</td>
          <td class="${inquiries.inquiryAnswer == 1 ? 'enquiryOk' : 'enquiryNo'}">${inquiries.inquiryAnswer == 1 ? '답변완료' : '답변대기'}</td>
          <td>
            <button class="content__detail__btn button__type_2 button__color__green">
              상세보기
            </button>
          </td>
         </tr>`

    return text;

}



inquiries.forEach((inquiries, i)=>{
    inquiryTableContainer.append(
        createDOM(inquiries)
    );
});

