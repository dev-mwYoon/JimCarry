/* 슬라이드 */
$(document).ready(function(){
    $("#flip").click(function(){
      $("#panel").slideToggle("slow");
    });
  });

    /* 수정모달 */
    const btn = document.querySelector(".modal-btn");
    const container = document.querySelector(".change-modal");
    const close = document.querySelector(".close-btn");
    const cancel = document.querySelector(".change-modal-delete-btn")
    
    //모달창 열기
    btn.addEventListener("click", function(){
        container.style.display="block";
    });

    //모달창 닫기
    close.addEventListener("click", function(){
        container.style.display="none";
    });

    //모달창 취소 닫기
    cancel.addEventListener("click", function(){
        container.style.display="none";
    });

    //모달창 외부 클릭 시 닫기
    window.onclick = function(event) {
        if(event.target == container) {
            container.style.display = "none";
        }
    };

    /* 삭제 모달 */
const modal = document.querySelector("#delete-btn");
const openBtn = document.querySelector("#delete-modal");
const span = document.querySelector("#close");

// 모달창 열기
modal.addEventListener("click", function(){
    openBtn.style.display="block";
});

// 모달창 닫기
span.addEventListener("click", function(){
    openBtn.style.display="none";
});

