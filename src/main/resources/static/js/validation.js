class Validation {
    constructor(fields){
        this.fields = fields;
    }
    validationSuccess(field){
        switchStatus(field, "success");
    }
    validationFail(errorList){
        let list = [];
        errorList = errorList || list; // ||연산자?
        for(let i = 0; i < fields.length; ++i) {
            let findError = errorList.find((error)=>{ return error.cause == this.fields[i]; });
            if (findError != undefined){
                switchStatus(fields[i], "error");
                return false;
            } else {
                validationSuccess(fields[i], "success");
            }
        }
    }
     switchStatus(field, status) {
        let inputField = document.querySelector('#' + field);
        let validMsg = document.querySelector('#valid_' + field + '_feedback');
        let invalidMsg = document.querySelector('#invalid_' + field + '_feedback');

        if (status === 'success') {
            inputField.classList.remove('is-invalid');
            invalidMsg.style.display = 'none';
            validMsg.style.display = 'block';
            inputField.classList.add('is-valid');
        } else {
            inputField.classList.remove('is-valid');
            validMsg.style.display = 'none';
            invalidMsg.style.display = 'block';
            inputField.classList.add('is-invalid');
        }
    }
}
// email -> is-valid, is-invalid , valid-feedback, invalid-feedback,
//min max , length , regexp , required , valid-feedback