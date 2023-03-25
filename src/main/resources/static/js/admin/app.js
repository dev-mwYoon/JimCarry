/**
 * ==================================================================================================
 *   DOM
 *   ==================================================================================================
 *
 * @format
 */

/* 사이드바 */
const $sidebar = $('.sidebar__main');
const $sidebarSlide = $('.sidebar__wrapper__slider');

/* 테이블 내용(컨텐츠) */
const $tableContent = $('.table__content');

/* 체크박스 */
const $checkAll = $('#checkAll');
const $check = $("input[name='check']");

/* 검색조건 select-box */
const $selectBox = $('.listbox-selecter');
const $listbox = $('.listbox');
const $list = $('.list');
const $searchType = $("input[name='searchType']");

/* 검색바 */
const $searchBar = $('.search-input');

/* 상세보기 관련 */
const $detailButton = $('.content__detail__btn');
const $modalStage = $('.modal-stage');

/* 시게 */
const clock = document.querySelector('.clock h1');

/* 모달창 */
/* 파일 썸네일교체 */
const $storageFile = $("input[name='file']");
const $thumbnail = $(".content__img img");


/* 창고 목록 불러오기*/

/*
<tr class="table__content">
    <td>
    <label class="check-label">
    <input type="checkbox" name="check" />
    </label>
    </td>
    <td class="content__id">1</td>
    <td>kmg2331</td>
    <td>kmg2331@gmail.com</td>
    <td>경기 의왕시 경수대로 262</td>
    <td>2023-01-01</td>
    <td>중</td>
    <td>
    <button class="content__detail__btn button__type_2 button__color__green">
    상세보기
    </button>
    </td>
</tr>*/
const tableContainer = $(".table");
const createDOM = function(box){
    let text = `
        <tr>
        <td>
        <label class="check-label">
            <input type="checkbox" name="check" />
            </label>
            </td>
            <td class="content__id">1[ ${box.storageSize} ]</td>
            <td>kmg233 ${box.storageId} </td>
            <td>kmg2331@gmail.com</td>
        <td>경기 의왕시 경수대로 262 ${box.storagePrice} 원</td>
        <td>2023-01-01 ${box.storageUseDate} ~ ${box.storageEndDate}</td>
        <td>중</td>
        <td> <button class="content__detail__btn button__type_2 button__color__green"> 상세보기 </button> </td>
        </tr> 
`
    return text;
}
boxes.forEach((box, i) => {
    tableContainer.append(
        createDOM(box)
    );
});
