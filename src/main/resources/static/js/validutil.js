class ValidUtil{    
    constructor(fields){
        this.fields = fields;
    }
    validSuccess(field){
        this.toggle_valid_status(field,'green');
        this.change_display_status(field,'none', null);
    }
    validError(error_list){
        let list = [];

        if (error_list == undefined) {
            list.push(error_list);
            error_list = list;
        }
        for (let i = 0; i < this.fields.length; ++i) {
            let findErrorObj = error_list.find((error)=>{
                                    return error.cause == this.fields[i];
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