/* 모달창 */
const modal = document.querySelector(".modalContainer");
const clickModalBtn= document.querySelector(".modalCheckButton");

function showModal() {
    modal.classList.add("showModal");
}

/* 모달창 확인버튼 누르면 없애기 */
function hideModal() {
    modal.classList.remove("showModal");
}