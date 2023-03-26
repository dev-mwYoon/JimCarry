/*창고 목록 불러오기*/
const storageTableContainer = $(".table");
const createDOM = function(box){
    let text =
        `
        <tr class="table__content">
        <td>
        <label class="check-label">
            <input type="checkbox" name="check" />
            </label>
            </td>
            <td class="content__id">${box.storageId} </td>
            <td>${box.userIdentification} </td>
            <td>${box.userEmail}</td>
        <td>${box.storageAddress} ${box.storageAddressDetail}</td>
        <td>${box.storageUseDate} ~ ${box.storageEndDate}</td>
        <td>${box.storageSize}</td>
        <td> <button class="content__detail__btn button__type_2 button__color__green"> 상세보기 </button> </td>
        </tr>
        `
    return text;
}
boxes.forEach((box, i) => {
    storageTableContainer.append(
        createDOM(box)
    );
});

