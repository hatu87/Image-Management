/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function onChangedFiles(){
    var input = $("#images").prop("files");
    
    if (input) {
        var reader = null;
        var image = null;
        var container = $("#preview-container");
        
        for(var i = 0; i < input.length; i++) {
            reader = new FileReader();
            reader.onload = function(e) {
                image = document.createElement("img");
                image.width = 300;
                image.height = 200;
                image.src = e.target.result;
                
                $(container).append(image);
            }
            
            reader.readAsDataURL(input[i]);
        }
    }
    
}

