let formData;
    function getImageFiles(e){
        let files = e.currentTarget.files;
        let imgPreview = document.querySelect('.img_preview');
        if([..files].length >= 7) {
            alert('이미지는 6개까지 업로드 가능합니다.');
            return;
        }

        [..files].forEach(file => {
            if (!file.type.match('image/.*')) {
                alert('이미지 파일만 업로드 가능합니다.');
                return;
            }
            let reader = new FileReader();
            reader.onload = (e) => {
                let preview = createElement(e, file);
                imgPreview.appendChild(preview);
            }
            reader.readAsDataURL(file);

            formData = new FormData();
            formData.append('images',file);
        }
    }
    function createElement(e, file) {
        let li = document.createElement('li');
        let img = document.createElement('img');
        img.setAttribute('src', e.target.result);
        img.setAttribute('data-file',file.name);
        li.appendChild(img);
        return li;
    }

    const upload = document.querySelector('#input_img');
    const upload_btn = document.querySelector('#upload');

    upload_btn.addEventListener('click', function() {
        upload.click();
    });
    upload.addEventListener('change', function(){
        getImageFiles();
    });

    let category;
    $('.category_option').on('click', function(){
        category = $(this).val();
    });

    function submit(){
        let title = $.trim($('#input_title').text());
        let content = $.trim($('#input_content').text());

        $.ajax({
            type : 'post',
            url : '/board/write',
            data : {
                title: title,
                content: content
            },
            success : function(){

            },
            error : function(){}
        });
    }
}