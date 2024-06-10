function modificaProdotto(oggetto){
    

    var form= document.getElementById("form-modifica");
    form.hidden=false;
    var id= document.getElementById("modifica-id");
    var nome= document.getElementById("modifica-nome");
    var prezzo= document.getElementById("modifica-prezzo");
    
    
     id.value=oggetto.getAttribute("modifica-id");
        nome.value=oggetto.getAttribute("modifica-nome");
        prezzo.value=oggetto.getAttribute("modifica-prezzo");
  
    }
    function chiudiFormModifica(){
        var form= document.getElementById("form-modifica");
        form.hidden=true;
    
        var id= document.getElementById("modifica-id");
        var nome= document.getElementById("modifica-nome");
        var prezzo= document.getElementById("modifica-prezzo");
       
        id.value="";
        nome.value="";
        prezzo.value="";
      
         
    }
    