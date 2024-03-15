class ValidUtil{    
    constructor(fields){
        this.fields = fields; // email, nickname, password,
    }
    validSuccess(field){
        this.toggle_valid_status(field,'green');
        this.change_display_status(field,'none', null);
    }
    validError(error_list){ // 서버에서 전달받은 error 목록
        let list = [];

        if (error_list == undefined) { // 정의되지 않은 데이터면
            list.push(error_list); // 왜? error_list push?
            error_list = list; // list 를 error_list 에 할당???
        }
        for (let i = 0; i < this.fields.length; ++i) { // fields length 3
            let findErrorObj = error_list.find((error)=>{ // error_list 에서 find () -> 전달받은 error.cause 가 this.fields 요소와 같은지
                                    return error.cause == this.fields[i];   // 서버 errorCode cause 명과 일치시켜야 한다.
                                });
            if (findErrorObj != undefined) {
                this.toggle_valid_status(findErrorObj.cause, 'red');
                this.change_display_status(findErrorObj.cause, 'block', findErrorObj.message);
                break;
                return false;
            } else {
                this.toggle_valid_status(this.fields[i],'green');
                this.change_display_status(this.fields[i],'none',null);
            }
        }

    }   
    toggle_valid_status(field, color){
        if (color == 'green'){
            $('#' + field).addClass('is-valid').removeClass('is-invalid');
        } 
        if (color == 'red') {
            $('#' + field).addClass('is-invalid').removeClass('is-valid');
        }
    }
    change_display_status(field, display, msg){
        if (display == 'block') {
            $('#' + field + 'Valid').css('display','block').text(msg);
        }
        if (display == 'none') {
            $('#' + field + 'Valid').css('display','none');
        }
    }
}