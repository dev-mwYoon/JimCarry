/**
 * ==================================================================================================
 *   이벤트
 *   ==================================================================================================
 *
 * @format
 */

/*  listbox 선택 이벤트 ======================================= */
$selectBox.on("click", function (e) {
  e.preventDefault();

  var listbox = $listbox;

  /* 박스 닫기 */
  if (listbox.hasClass("displayNone")) {
    listbox.removeClass("displayNone");
  } else {
    listbox.addClass("displayNone");
  }
});

$list.on("click", function (e) {
  e.preventDefault();

  var i = $list.index($(this));
  var text = $list.eq(i).text();
  $selectBox.text(text);

  /* 인덱스에 따라 검색조건 설정 */
  switch (i) {
    case 0:
      text = "all";
      break;
    case 1:
      text = "searchType1";
      break;
    case 2:
      text = "searchType2";
      break;
    default:
      break;
  }

  /* 실제 input안에 value 값 변경 */
  $searchType.val(text);

  /* 박스 닫기 */
  $listbox.addClass("displayNone");
});

/* 검색바 이벤트 ======================================= */
$searchBar.on("keyup", function (key) {
  /* 추후 검색조건과 연동하여 ajax 작성 요망 */
  if (key.keyCode == 13) {
    alert("검색 실행");
  }
});

/* 상세보기 모달창 ======================================= */
$detailButton.on("click", function () {
  var i = $detailButton.index($(this));
  /* 해당 컨텐츠 번호 */
  var contentId = $detailButton.eq(i).parent().siblings(".content__id").text();

  /* 추후 타임리프로 대체할 예정 */
  $modalStage.css("display", "block");
  $modalStage.load("/templates/admin/user-modal.html");

  /* ajax 코드 작성해야 함 */
});

/* 체크박스 이벤트 ======================================= */

$checkAll.click(function () {
  if ($checkAll.is(":checked")) $check.prop("checked", true);
  else $check.prop("checked", false);
});

$check.click(function () {
  var total = $check.length;
  var checked = $("input[name=check]:checked").length;

  if (total != checked) $checkAll.prop("checked", false);
  else $checkAll.prop("checked", true);
});
