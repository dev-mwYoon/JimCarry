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