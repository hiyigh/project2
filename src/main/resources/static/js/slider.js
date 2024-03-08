const sliderWrapMain = document.querySelector(".slider_wrap_main");
const sliderImgMain = sliderWrap.querySelector(".slider_img_main");
const sliderMain = sliderWrap.querySelectorAll(".slider_main");

const sliderWrapSub = document.querySelector(".slider_wrap_sub");
const sliderImgMain = sliderWrap.querySelector(".slider_img_sub");
const sliderSub = sliderWrap.querySelectorAll(".slider_sub");

let currentIndex = 0;               // 현재 보이는 이미지
let sliderMainCount = sliderMain.length;    // 이미지 갯수
let sliderInterval = 3000;          // 이미지 변경 간격 시간

setInterval(() => {
    let nextIndex =(currentIndex + 1) % sliderMainCount;

    sliderMain[currentIndex].style.opacity = "0";
    sliderMain[nextIndex].style.opacity = "1";

    currentIndex = nextIndex;

    console.log(currentIndex);
}, sliderInterval);

