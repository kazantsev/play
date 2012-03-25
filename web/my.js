/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function handleFocus(fieldId, labelId, defValue, isPassw){
    var field = document.getElementById(fieldId);
    if (field.value==defValue){
        field.value = '';
    }
    if (isPassw){
        field.type='password'; 
    }
    var label=document.getElementById(labelId);
    label.innerHTML=defValue;
    
}

function handleBlur(fieldId, labelId, defValue, isPassw){
    var field = document.getElementById(fieldId);
    var label=document.getElementById(labelId);
    if (field.value==''){
        if (isPassw){
            field.type = 'text';
        }        
        field.value = defValue
        label.innerHTML = '';
    } 
}


