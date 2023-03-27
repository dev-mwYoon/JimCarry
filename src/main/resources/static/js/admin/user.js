


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
users.forEach((users, i) => {
    userTableContainer.append(createDOM(users));
});

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
console.log("detailButton: " + $detailButton);

$(".content__detail__btn").on('click', function () {
    console.log("click");
    const i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    const contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
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
