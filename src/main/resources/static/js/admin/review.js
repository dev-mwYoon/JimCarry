/*리뷰 목록 불러오기*/
const reviewTableContainer = $(".table");
const createDOM = function(reviews){
    let text = `
        <tr class="table__content">
          <td>
            <label class="check-label">
              <input type="checkbox" name="check" />
            </label>
          </td>
          <td class="content__id">${reviews.reviewId}</td>
          <td>${reviews.userIdentification}</td>
          <td>${reviews.userEmail}</td>
          <td>${reviews.reviewTitle}</td>
          <td>${reviews.reviewWriteDate}</td>
          <td>
            <button class="content__detail__btn button__type_2 button__color__green">
              상세보기
            </button>
          </td>
        </tr>
`
    return text;
}
reviews.forEach((reviews, i) => {
    reviewTableContainer.append(
        createDOM(reviews)
    );
});