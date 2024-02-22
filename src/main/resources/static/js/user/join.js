const fields = ['input_email', 'input_userName', 'input_password'];
const valid = new ValidUtil(fields);

let valid_email = false;
let valid_userName = false;
let valid_password = false;
let validPasswordConfirm = false;

$('#input_email').on('input', function(){
    let email = $.trim($('#input_email').val());
    $.ajax({
        type:'post',
        url:'/user/join/email/check',
        data:{email : email},
        success:function(data){
            if (data == ture) {
                valid_email = true;
                ValidUtil.validSuccess('input_email');
            }
        },
        error:function(data) {
            valid_email = false;
            ValidUtil.validError(data);
        }
    });
});

$('#input_userName').on('input', function(){
    let userName = $.trim($('#input_userName').val());
    $.ajax({
        type:'post',
        url:'/user/join/userName/check',
        data:{userName : userName},
        success:function(data){
            if(data == true){
                valid_userName = true;
                ValidUtil.validSuccess('input_userName');
            } 
        },
        error:function(data){
            valid_userName = false;
            ValidUtil.validError(data);
        }
    });
});
$('#input_password').on('input', function(){
    let password = $.trim($('#input_password').val());
    $.ajax({
        type:'post',
        url:'/user/join/password/check',
        data:{password : password},
        success:function(data){
            if(data == true){
                valid_userName = true;
                ValidUtil.validSuccess('input_password');
            } 
        },
        error:function(data){
            valid_userName = false;
            ValidUtil.validError(data);
        }
    });
});

$('#input_password , #check_password').on('change keyup', function(){
    let password = $.trim($('#input_password').val());
    let check_password = $.trim($('#check_password').val());
    if (password != null && check_password != null) {
        if (password != check_password) {
            ValidUtil.toggle_valid_status('check_password', 'red');
            ValidUtil.change_display_status('check_password', 'block', '비밀번호가 일치하지 않습니다.');
            validPasswordConfirm = false;
        } else {
            ValidUtil.toggle_valid_status('check_password', 'green');
            ValidUtil.change_display_status('check_password','none',null);
            validPasswordConfirm = true;
        }
    } else {
        ValidUtil.toggle_valid_status('intput_password', 'red');
        ValidUtil.toggle_valid_status('check_password', 'red');
        ValidUtil.change_display_status('check_password','block','비밀번호를 입력해 주세요');
    }

});
function submit(){
    if (valid_email && valid_password && valid_userName && validPasswordConfirm) {
        let email = $.trim($('#input_email').val());
        let password = $.trim($('#input_password').val());
        let userName = $.trim($('#input_userName').val());

        $.ajax({
            type:'post',
            url:'/user/join/submit',
            data:{
                email : email,
                password : password,
                userName : userName
            },
            success:function() {
                window.location.href='/joinSuccess';
            },
            error:function(){
                console.log('join submit went wrong');
            }
        });
    }
    $('#input_email, #input_password, #input_userName, #check_password').on('keyup', function(){
        if (valid_email && valid_password && valid_userName && validPasswordConfirm) {
            $('register_btn').attr('disabled', false);
        } else {
            $('register_btn').attr('disbaled', true);
        }
    });
}