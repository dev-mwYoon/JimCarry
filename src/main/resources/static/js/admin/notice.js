const noticeTableContainer = $(".table");
const creatDOM = function (notices) {
    let text=
        `
         <tr class="table__content">
              <td>
                <label class="check-label">
                  <input type="checkbox" name="check" />
                </label>
              </td>
              <td class="content__id">${notices.noticeId}</td>
              <td>${notices.noticeTitle}</td>
              <td class="notice-content">${notices.noticeContent}</td>
              <td>${notices.noticeRegist}</td>
              <td>${notices.noticeUpdate}</td>
              <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                  상세보기
                </button>
              </td>
        </tr>
        `
    return text;
}
notices.forEach((notices, i)=>{
    noticeTableContainer.append(
        creatDOM(notices)
    );
});