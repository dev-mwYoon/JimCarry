/* banner.html */
HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector("div.banner");
const imageDiv = document.querySelectorAll("div.banner div");
const lastImageDiv = document.createElement("div");
const firstImageDiv = document.createElement("div");
const next = document.querySelector("div.next");
const prev = document.querySelector("div.prev");
const bannerCount = document.querySelector("span.banner-count");
const container = document.querySelector("section.container");
let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 2000);

HTMLCollection.prototype.forEach = Array.prototype.forEach;

imageDiv.forEach((div, i) => div.style.backgroundImage = `url(/image/00${i+1}.png)`)

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(/image/001.png)`;

banner.insertBefore(firstImageDiv, document.querySelector("div.banner div"));
firstImageDiv.style.backgroundImage = `url(/image/00${imageDiv.length}.png)`

banner.style.transform = `translate(-1728px)`;
updateBannerCount();

function autoSlide(){
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1728 * ++count}px)`;
    if(count == 4) {
        count = 1;
        updateBannerCount();
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-1728px)";
        }, 300);
    }
    updateBannerCount();
}

prev.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1728 * --count}px)`;
    if(count == 0) {
        count = 3;
        updateBannerCount();
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(${-1728 * (imageDiv.length)}px)`;
        }, 300);
    }
    updateBannerCount();
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
});

next.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1728 * ++count}px)`;
    if(count == 4) {
        count = 1;
        updateBannerCount();
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-1728px)";
        }, 300);
    }
    updateBannerCount();
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
});

container.addEventListener('mouseover', function() {
    prev.style.opacity = 1;
    next.style.opacity = 1;
})
container.addEventListener('mouseout', function() {
    prev.style.opacity = 0;
    next.style.opacity = 0;
})

function updateBannerCount() {
    bannerCount.innerHTML = `${count} / ${imageDiv.length}`;
}

/*신규, 후기*/
const $prev = $('.prev-button');
const $next = $('.next-button');
let countcount = 0;
let countcount1 = 0;
let nowPage = 1;
let nowPage1 = 1;

$prev.css('display', 'none');
$prev.click(function(){
    let fourBanner = $(this).parent().children().children();
    fourBanner.css('transition', 'transform 0.3s');
    fourBanner.css('transform', `translate(${-1065 * (this == $prev[0] ? --countcount1 : --countcount)}px)`);
    if((this == $prev[0] ? --nowPage1 : --nowPage) == 1) {
        $(this).css('display', 'none');
        $(this).next().css('display', 'inline-block');
    }
});

$next.click(function(){
    let fourBanner = $(this).parent().children().children();
    fourBanner.css('transition', 'transform 0.3s');
    fourBanner.css('transform', `translate(${-1065 * (this == $next[0] ? ++countcount1 : ++countcount)}px)`);
    if((this == $next[0] ? ++nowPage1 : ++nowPage) == 2) {
        $(this).css('display', 'none');
        $(this).prev().css('display', 'inline-block');
    }
});

/*----------------------------------------------------------------*/
/*목록이 추가될 div 부모*/
const storageContainer = $("#four-static-banner-inner-div");

/* 목록이 추가될 div에 화면에서 필요한 필드멤버 뿌려주기 */
const createDOM = function (storage, file, review) {
    let text = `
    <div class="four-static-banner-one-div">
        <a href="" class="four-static-banner-one-a">
            <div class="four-static-banner-img-div">
                <img src="https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/12/31/GKRZjZYBpPhE637765486789400308.jpg">
            </div>
            <div class="four-static-banner-product-div">
                <h3 class="four-static-banner-name">${storage.storageTitle}</h3>
                <div class="four-static-banner-detail-div">
                    <div class="four-static-banner-price-div">
                        <span class="four-static-banner-price">
                            ${storage.storagePrice}원
                        </span>
                    </div>
                </div>
                <div class="four-static-banner-review-div">
                    <div class="four-static-banner-review-icon">
                        <svg width="100%" height="100%" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <mask id="path-1-inside-1_1513_17755" fill="white">
                                <path fill-rule="evenodd" clip-rule="evenodd" d="M3 2C1.89543 2 1 2.89543 1 4V8.67201C1 9.77658 1.89543 10.672 3 10.672H5.11212L6.33682 12.7653C6.5299 13.0954 7.00688 13.0954 7.19995 12.7653L8.42465 10.672H10.5C11.6046 10.672 12.5 9.77658 12.5 8.67201V4C12.5 2.89543 11.6046 2 10.5 2H3Z"></path>
                            </mask>
                            <path fill="#999" d="M5.11212 10.672L5.97526 10.167L5.68564 9.67201H5.11212V10.672ZM6.33682 12.7653L5.47369 13.2703L5.47369 13.2703L6.33682 12.7653ZM7.19995 12.7653L6.33682 12.2604L6.33682 12.2604L7.19995 12.7653ZM8.42465 10.672V9.67201H7.85113L7.56152 10.167L8.42465 10.672ZM2 4C2 3.44772 2.44772 3 3 3V1C1.34315 1 0 2.34315 0 4H2ZM2 8.67201V4H0V8.67201H2ZM3 9.67201C2.44772 9.67201 2 9.22429 2 8.67201H0C0 10.3289 1.34315 11.672 3 11.672V9.67201ZM5.11212 9.67201H3V11.672H5.11212V9.67201ZM7.19995 12.2604L5.97526 10.167L4.24899 11.177L5.47369 13.2703L7.19995 12.2604ZM6.33682 12.2604C6.5299 11.9304 7.00688 11.9304 7.19995 12.2604L5.47369 13.2703C6.05291 14.2604 7.48386 14.2604 8.06309 13.2703L6.33682 12.2604ZM7.56152 10.167L6.33682 12.2604L8.06309 13.2703L9.28779 11.177L7.56152 10.167ZM10.5 9.67201H8.42465V11.672H10.5V9.67201ZM11.5 8.67201C11.5 9.22429 11.0523 9.67201 10.5 9.67201V11.672C12.1569 11.672 13.5 10.3289 13.5 8.67201H11.5ZM11.5 4V8.67201H13.5V4H11.5ZM10.5 3C11.0523 3 11.5 3.44772 11.5 4H13.5C13.5 2.34315 12.1569 1 10.5 1V3ZM3 3H10.5V1H3V3Z" mask="url(#path-1-inside-1_1513_17755)"></path>
                            <circle fill="#999" cx="4.34998" cy="6.17871" r="0.75"></circle>
                            <circle fill="#999" cx="6.75" cy="6.17871" r="0.75"></circle>
                            <circle fill="#999" cx="9.15002" cy="6.17871" r="0.75"></circle>
                        </svg>
                    </div>
                    후기
                    <span>${storage.reviewCount}</span>
                </div>
            </div>
        </a>
    </div>
`
    return text;
}
/* html script에서 받아준 모델객체 */
storages.forEach((storage, i) => {
    storageContainer.append(
        createDOM(storage, files[i], reviews)
    );
});



/*----------------------------------------------------------------*/
/*리뷰많은순*/
/*목록이 추가될 div 부모*/
const storageContainer2 = $("#storageReview");

/* 목록이 추가될 div에 화면에서 필요한 필드멤버 뿌려주기 */
function createDOMs(a, reviews) {
    let texts = `
    <div class="four-static-banner-one-div">
        <a href="#" class="four-static-banner-one-a">
            <div class="four-static-banner-img-div">
                <img src="https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/12/31/GKRZjZYBpPhE637765486789400308.jpg">
            </div>
            <div class="four-static-banner-product-div">
                <h3 class="four-static-banner-name">${a.storageTitle}</h3>
                <div class="four-static-banner-detail-div">
                    <div class="four-static-banner-price-div">
                        <span class="four-static-banner-price">
                            ${a.storagePrice}원
                        </span>
                    </div>
                </div>
                <div class="four-static-banner-review-div">
                    <div class="four-static-banner-review-icon">
                        <svg width="100%" height="100%" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <mask id="path-1-inside-1_1513_17755" fill="white">
                                <path fill-rule="evenodd" clip-rule="evenodd" d="M3 2C1.89543 2 1 2.89543 1 4V8.67201C1 9.77658 1.89543 10.672 3 10.672H5.11212L6.33682 12.7653C6.5299 13.0954 7.00688 13.0954 7.19995 12.7653L8.42465 10.672H10.5C11.6046 10.672 12.5 9.77658 12.5 8.67201V4C12.5 2.89543 11.6046 2 10.5 2H3Z"></path>
                            </mask>
                            <path fill="#999" d="M5.11212 10.672L5.97526 10.167L5.68564 9.67201H5.11212V10.672ZM6.33682 12.7653L5.47369 13.2703L5.47369 13.2703L6.33682 12.7653ZM7.19995 12.7653L6.33682 12.2604L6.33682 12.2604L7.19995 12.7653ZM8.42465 10.672V9.67201H7.85113L7.56152 10.167L8.42465 10.672ZM2 4C2 3.44772 2.44772 3 3 3V1C1.34315 1 0 2.34315 0 4H2ZM2 8.67201V4H0V8.67201H2ZM3 9.67201C2.44772 9.67201 2 9.22429 2 8.67201H0C0 10.3289 1.34315 11.672 3 11.672V9.67201ZM5.11212 9.67201H3V11.672H5.11212V9.67201ZM7.19995 12.2604L5.97526 10.167L4.24899 11.177L5.47369 13.2703L7.19995 12.2604ZM6.33682 12.2604C6.5299 11.9304 7.00688 11.9304 7.19995 12.2604L5.47369 13.2703C6.05291 14.2604 7.48386 14.2604 8.06309 13.2703L6.33682 12.2604ZM7.56152 10.167L6.33682 12.2604L8.06309 13.2703L9.28779 11.177L7.56152 10.167ZM10.5 9.67201H8.42465V11.672H10.5V9.67201ZM11.5 8.67201C11.5 9.22429 11.0523 9.67201 10.5 9.67201V11.672C12.1569 11.672 13.5 10.3289 13.5 8.67201H11.5ZM11.5 4V8.67201H13.5V4H11.5ZM10.5 3C11.0523 3 11.5 3.44772 11.5 4H13.5C13.5 2.34315 12.1569 1 10.5 1V3ZM3 3H10.5V1H3V3Z" mask="url(#path-1-inside-1_1513_17755)"></path>
                            <circle fill="#999" cx="4.34998" cy="6.17871" r="0.75"></circle>
                            <circle fill="#999" cx="6.75" cy="6.17871" r="0.75"></circle>
                            <circle fill="#999" cx="9.15002" cy="6.17871" r="0.75"></circle>
                        </svg>
                    </div>
                    후기
                    <span>${a.reviewCount}</span>
                </div>
            </div>
        </a>
    </div>
`   ;
    storageContainer2.append(texts);
}
/* html script에서 받아준 모델객체 */
countReviews.forEach((a, i) => {
    createDOMs(a, reviews);
});



