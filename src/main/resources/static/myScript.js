function validateForm() {
    let nameHolder = document.getElementById("name");
    let nameValidator = document.getElementById("name-validator");
    let nameMessage = "Imię nie może być puste";

    if(!(nameHolder.value.length>0)){
        nameValidator.innerHTML = nameMessage;
        return false;
    }else{
        nameValidator.innerHTML = "";
    }

    let mailHolder = document.getElementById("email-address")
    let mailValidator = document.getElementById("mail-validator")
    let mailMessage = "Niepoprawny email"
    const regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if(!(mailHolder.value.match(regex))){
        mailValidator.innerHTML = mailMessage;
        return false;
    }else{
        mailValidator.innerHTML = "";
    }

    let titleHolder = document.getElementById("title");
    let titleValidator = document.getElementById("title-validator");
    let titleMessage = "Tytuł nie może być pusty";
    if(!(checkIfNotEmptyAndPutProperMessage(titleMessage, titleHolder, titleValidator))){
        return false;
    }

    if(!(titleHolder.value.length>0)){
        titleValidator.innerHTML = titleMessage;
        return false;
    }else{
        nameValidator.innerHTML = "";
    }

    let contentHolder = document.getElementById("content");
    let contentValidator = document.getElementById("content-validator");
    let contentMessage = "Treść nie może być pusta";
    if(!( checkIfNotEmptyAndPutProperMessage(contentMessage, contentHolder, contentValidator))){
        return false;
    }

    if(!(contentHolder.value.length>0)){
        contentValidator.innerHTML = contentMessage;
        return false;
    }else{
        contentValidator.innerHTML = "";
    }
}