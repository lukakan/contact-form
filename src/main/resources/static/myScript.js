function validateForm() {
    let mailHolder = document.getElementById("email-address")
    let mailValidator = document.getElementById("mail-validator")
    let mailMessage = "Niepoprawny email"
    const regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if(!(checkIfMailIsCorrectAndPutProperMessage(mailMessage, mailHolder, mailValidator, regex))){
        return false;
    }

    let nameHolder = document.getElementById("name");
    let nameValidator = document.getElementById("name-validator");
    let nameMessage = "Tytuł nie może być pusty";
    if(!(checkIfNotEmptyAndPutProperMessage(nameMessage, nameHolder, nameValidator))){
        return false;
    }

    let titleHolder = document.getElementById("title");
    let titleValidator = document.getElementById("title-validator");
    let titleMessage = "Tytuł nie może być pusty";
    if(!(checkIfNotEmptyAndPutProperMessage(titleMessage, titleHolder, titleValidator))){
        return false;
    }

    let contentHolder = document.getElementById("content");
    let contentValidator = document.getElementById("content-validator");
    let contentMessage = "Treść nie może być pusta";
    if(!( checkIfNotEmptyAndPutProperMessage(contentMessage, contentHolder, contentValidator))){
        return false;
    }

}

function checkIfNotEmptyAndPutProperMessage(message, htmlElementForValidation, htmlElementWithMessage){
    if(!(htmlElementForValidation.value.length>0)){
        htmlElementWithMessage.innerHTML = message;
        return false;
    }else{
        htmlElementWithMessage.innerHTML = "";
        return true;
    }
}

function checkIfMailIsCorrectAndPutProperMessage(message, htmlElementForValidation, htmlElementWithMessage, regex){
    if(!(htmlElementForValidation.value.match(regex))){
        htmlElementWithMessage.innerHTML = message;
        return false;
    }else{
        htmlElementWithMessage.innerHTML = "";
        return true;
    }
}