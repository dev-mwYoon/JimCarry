$(document).ready(function(){
    $("#flip").click(function(){
      $("#panel").slideToggle("slow");
    });
  });

    /* 모달 */
    const btn = document.querySelector(".modal-btn");
    const container = document.querySelector("#modal");
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