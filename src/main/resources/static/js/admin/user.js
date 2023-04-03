
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
$('#Glyph').on('click', function() {
    const conditiontest = $('.listbox-selecter').text();

    var condition;
    if (conditiontest == '이름') {
        condition = "userName";
    } else if (conditiontest == '주소') {
        condition = "userAddress";
    } else if( conditiontest == '검색조건 선택'){
        alert("검색 조건을 선택해주세요.");
        return;
    }
    $("input[name='condition']").val(condition);

    document.searchForm.submit();
});
users.forEach((users, i) => {
    userTableContainer.append(createDOM(users));

});





/*=====================  상세보기 모달 =====================*/
$(".modal-stage").append(
    `
    <section class="modal">
          <div class="modal__header">
            <h3 class="modal__title">회원 상세보기</h3>
            <a class="modal-close">
              <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                <path
                        d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z" />
              </svg>
            </a>
          </div>
          <form action="">
            <main class="modal__main">
              <div class="modal__profile__top">
                <h4>수정가능</h4>
                <div class="user__profile">
                  <h5>아이디</h5>
                  <div class="user__profile__input">
                    <input type="text" name="userIdentification" value="${users.userIdentification}" />
                  </div>
                </div>
                <div class="user__profile">
                  <h5>이메일</h5>
                  <div class="user__profile__input">
                    <input type="text" name="userEmail" value="example@gmail.com" />
                  </div>
                </div>
                <div class="user__profile">
                  <h5>전화번호</h5>
                  <div class="user__profile__input">
                    <input type="text" name="userPhone" value="01012341234" />
                  </div>
                </div>
              </div>
              <div class="modal__profile__bottom">
                <h4>수정 불가능</h4>
                <div class="user__profile">
                  <h5>이름</h5>
                  <div class="user__profile__input">
                    <input type="text" name="userName" value="홍길동" readonly="true" />
                  </div>
                </div>
                <div class="user__profile">
                  <h5>생년월일</h5>
                  <div class="user__profile__input">
                    <input type="text" name="userBirth" value="2023-01-01" readonly="true" />
                  </div>
                </div>
                <div class="user__profile">
                  <h5>성별</h5>
                  <div class="user__profile__input">
                    <input type="text" name="" value="성별" readonly="true" />
                  </div>
                </div>
              </div>
              <div class="user__profile__button">
                <button class="button__type_2 button__color__green">수정완료</button>
              </div>
            </main>
          </form>
        </section>
      </section>
    `
);


$(".content__detail__btn").on('click', function () {
    const i = $('.content__detail__btn').index($(this));
    console.log(i);

    /* 해당 컨텐츠 번호 */
    const userId = $('.content__detail__btn').eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    $.ajax({
        url: "/admins/user/detail",
        type: "post",
        data: { userId : userId },
        traditional : true,
        success : function(userdetail){
            $(".modal-stage").html(
                `
                <section class="modal">
                      <div class="modal__header">
                        <h3 class="modal__title">회원 상세보기</h3>
                        <a class="modal-close" id="modal-close">
                          <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                            <path
                                    d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z" />
                          </svg>
                        </a>
                      </div>
                      <form action="">
                        <main class="modal__main">
                          <div class="modal__profile__top">
                            <h4>수정가능</h4>
                            <div class="user__profile">
                              <h5>아이디</h5>
                              <div class="user__profile__input">
                                <input type="text" name="userIdentification" value="${userdetail.userIdentification}" />
                              </div>
                            </div>
                            <div class="user__profile">
                              <h5>이메일</h5>
                              <div class="user__profile__input">
                                <input type="text" name="userEmail" value="${userdetail.userEmail}" />
                              </div>
                            </div>
                            <div class="user__profile">
                              <h5>전화번호</h5>
                              <div class="user__profile__input">
                                <input type="text" name="userPhone" value="${userdetail.userPhone}" />
                              </div>
                            </div>
                          </div>
                          <div class="modal__profile__bottom">
                            <h4>수정 불가능</h4>
                            <div class="user__profile">
                              <h5>이름</h5>
                              <div class="user__profile__input">
                                <input type="text" name="userName" value="${userdetail.userName}" readonly="true" />
                              </div>
                            </div>
                            <div class="user__profile">
                              <h5>생년월일</h5>
                              <div class="user__profile__input">
                                <input type="text" name="userBirth" value="${userdetail.userBirth}" readonly="true" />
                              </div>
                            </div>
                            <div class="user__profile">
                              <h5>성별</h5>
                              <div class="user__profile__input">
                                <input type="text" name="" value="${userdetail.userGender}" readonly="true" />
                              </div>
                            </div>
                          </div>
                          <div class="user__profile__button">
                            <button class="button__type_2 button__color__green">수정완료</button>
                          </div>
                        </main>
                      </form>
                    </section>
                  </section>
                `
            );

        }
    });
    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

});
console.log($modalStage);
/* 모달 닫는 이벤트 */
/* 추후 외부로 빼야함 */
console.log($('a#modal-close'));
$('a#modal-close').on('click', function () {
    console.log($('#modal-close'));
    $modalStage.fadeOut(500);
});

/* 상세보기 모달 내용 submit 이벤트 */
$('#completeBtn').on('click', function (e) {
    e.preventDefault();
    return new Promise(
        function () {
            console.log('으으아');
            $modalStage.fadeOut(500);
        },
        () => {
            $('.storage_form').submit();
        }
    );
});


/*=====================  목록 삭제 이벤트 =====================*/
/* 체크박스 */
const $checkAll = $('#checkAll');
const $check = $("input[name='check']");
let $checkArr = [];
$('input[name=check]').on('click', function() {
    if ($(this).is(':checked')) {
        $checkArr.push($(this).parent().parent().next().text());
    } else {
        var toRemove = $(this).parent().parent().next().text();
        $checkArr = $checkArr.filter(function (item) {
            return item !== toRemove;
        });
    }
    console.log($checkArr);
});

/* 체크박스 이벤트 */
$checkAll.click(function () {
    if ($checkAll.is(':checked')) {
        $check.prop('checked', true);
        $check.each((i,e)=> {
            $checkArr.push($check.eq(i).parent().parent().next().text());
        });
    }
    else {
        $check.prop('checked', false);
        $check.each((i, e) => {
            var toRemove = $check.eq(i).parent().parent().next().text();
            $checkArr = $checkArr.filter(function (item) {
                return item !== toRemove;
            });
        });
    }
    console.log($checkArr);
});

$check.click(function () {
    var total = $check.length;
    var checked = $('input[name=check]:checked').length;
    if (total != checked) {
        $checkAll.prop('checked', false);
    }
    else {
        $checkAll.prop('checked', true);
    }
});
/* 목록 삭제 ajax */
confirmButton.on('click', function () {

    $.ajax({
        url: "/admin/user/delete",
        type: "post",
        data: { userIds : $checkArr },
        traditional : true,
        success : function(result){
            if(result) {
                location.reload();
            }
        }
    });
});


