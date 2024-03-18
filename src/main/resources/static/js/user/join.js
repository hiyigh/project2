const fields = ['email', 'name', 'password'];

const valid = new Validation(fields);

let valid_email = false;
let valid_userName = false;
let valid_password = false;
let validPasswordConfirm = false;

$('#email').on('input', function(){
    let email = $.trim($('#email').val());
    $.ajax({
        type:'post',
        url:'/user/api/join/email/check',
        data:{email : email},
        success:function(data){
            if (data == ture) {
                valid_email = true;
                Validation.validSuccess('email',"success");
            }
        },
        error:function(data) {
            valid_email = false;
            Validation.validFail(data); // 서버에서 error 정보 전달
        }
    });
});

$('#name').on('input', function(){
    let userName = $.trim($('#name').val());
    $.ajax({
        type:'post',
        url:'/user/api/join/name/check',
        data:{userName : userName},
        success:function(data){
            if(data == true){
                valid_userName = true;
                ValidUtil.validSuccess('name');
            } 
        },
        error:function(data){
            valid_userName = false;
            ValidUtil.validError(data);
        }
    });
});
$('#password').on('input', function(){ // ??? 어떤 검사 ???
    let password = $.trim($('#password').val());
    $.ajax({
        type:'post',
        url:'/user/api/join/password/check',
        data:{password : password},
        success:function(data){
            if(data == true){
                valid_userName = true;
                ValidUtil.validSuccess('password');
            } 
        },
        error:function(data){
            valid_userName = false;
            ValidUtil.validError(data);
        }
    });
});

$('#password , #checkPassword').on('change keyup', function(){
    let password = $.trim($('#password').val());
    let check_password = $.trim($('#checkPassword').val());

    if (password != null && check_password != null) {
        if (password != check_password) {
            Validation.switchStatus(checkPassword, "error");
            validPasswordConfirm = false;
        } else {
            Validation.switchStatus(checkPassword,"success");
            validPasswordConfirm = true;
        }
    }
});
function submit(){
    if (valid_email && valid_password && valid_userName && validPasswordConfirm) {
        let email = $.trim($('#email').val());
        let password = $.trim($('#password').val());
        let userName = $.trim($('#name').val());

        $.ajax({
            type:'post',
            url:'/user/api/join/submit',
            data:{
                email : email,
                password : password,
                userName : userName
            },
            success:function() {
                window.location.href='/joinSuccess';
            },
            error:function(){
                console.log('join submit is wrong');
            }
        });
    }
    $('#email, #password, #name, #checkPassword').on('keyup', function(){ // keyup 할 때마다 계속 check
        if (valid_email && valid_password && valid_userName && validPasswordConfirm) {
            $('register_btn').attr('disabled', false);
        } else {
            $('register_btn').attr('disabled', true);
        }
    });
}