const IMG_WIDTH=500;
let position = 0;
let imgIndex = 0;

const $btnNext = $('#home-img-btn-next');
const $btnPrev = $('#home-img-btn-prev');
const $slide-img = $('.home-img-slide');

$btnNext.on("click",function(){
    if (imgIndex > 0) {
        $btnNext.removeAttr('disabled');
        position += IMG_WIDTH;
        $slide-img.css('transform', 'translateX(${position}px)');
        --imgIndex;
    }
    if (imgIndex === 0) {
        $btnPrev.attr('disabled', true);
    }
});

$btnPrev.on("click",function(){
    if (imgIndex < 5) {
        $btnPrev.removeAttr('disabled');
        position -= IMG_WIDTH;
        $slide-img.css('transform', 'translateX(${position}px)');
        ++imgIndex;
    }
    if (imgIndex === 4) {
        $btnPrev.attr('disabled', true);
    }
});

document.ready(function(){
    $btnPrev.prop
});