


/*리뷰 목록 불러오기*/
const userTableContainer = $(".table");
const createDOM = function(users){
    let text = `
           <tr class="table__content">
            <td>
                <label class="check-label">
                <input type="checkbox" name="check" />
                </label>
                </td>
                    <td class="content__id">${users.userId}</td>
                    <td>${users.userIdentification}</td>
                    <td>${users.userEmail}</td>
                    <td>${users.userBirth}</td>
                    <td>${users.userGender}</td>
                <td>
                    <button class="content__detail__btn button__type_2 button__color__green">
                     상세보기
                      </button>
                </td>
            </tr>
`
    return text;
}
users.forEach((reviews, i) => {
    userTableContainer.append(
        createDOM(reviews)
    );
});