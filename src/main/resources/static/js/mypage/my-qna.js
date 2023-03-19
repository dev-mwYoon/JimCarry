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
    
    //모달창 열기
    btn.addEventListener("click", function(){
        container.style.display="block";
    });

    //모달창 닫기
    close.addEventListener("click", function(){
        container.style.display="none";
    });

    //모달창 외부 클릭 시 닫기
    window.onclick = function(event) {
        if(event.target == container) {
            container.style.display = "none";
        }
    };

    /* 삭제 모달 */
    const deleteBtn = document.querySelector(".delete-btn");
    const deleteContainer = document.querySelector(".deleteModal");
    const deleteClose = document.querySelector(".pay-popup-check");
    
    //모달창 열기
    deleteBtn.addEventListener("click", function(){
        deleteContainer.style.display="block";
    });

    //모달창 닫기
    deleteClose.addEventListener("click", function(){
        deleteContainer.style.display="none";
    });

    //모달창 외부 클릭 시 닫기
    window.onclick = function(event) {
        if(event.target == deleteContainer) {
            deleteContainer.style.display = "none";
        }
    };