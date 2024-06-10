//previousMonth()
//nextMonth()
/*addEvent()
deleteEvent() */

var fixedDays = [6,0,1,2,3,4,5]; //[dom,lun,mar,mer,gio,ven,sab]
var months = ["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto",
"Settembre","Ottobre","Novembre","Dicembre"];

//Salvo in dayOfMonth il valore del giorno corrispondente a fixedDays
//cioè se domenica ->  dayOfMonth=6
var dayOfWeek; //valori da 0 a 6

var dayOfMonth;//salvo l'attuale giorno del mese da 1 a 31
var dayToday = new Date(); //giorno attuale
console.log(dayToday.getMonth());

var actualMonthInTable = dayToday.getMonth(); //salvo il mese
var actualYearInTable = dayToday.getFullYear(); //salvo l'anno

var agenda=[];
agenda[4] = "la scoteca";
agenda[16] = "very important appuntamento";

var agenda2 =[];
agenda2[12] = "oculista"; 

var mappaAgenda = new Map();
mappaAgenda.set('Maggio 2024', agenda);
mappaAgenda.set('Settembre 2024',agenda2);

//variabile in cui salvo mese e anno 
var keyDate;


//funzione che genererà la tabella con i giorni e i corrispettivi valori
function generateTable(anno,mese){
    var table = document.getElementById("pippo");
    var data = new Date(anno,mese); 
    //variabile in cui salvo il primo giorno del mese
    //dovrò settare anno,mese,giorno
    var dataStartMonthActual = new Date(data.getFullYear(),data.getMonth(),1); //01-4-2024
    //console.log(dataStartMonthActual.getDate() + " " + dataStartMonthActual.getMonth() + " " +
    //dataStartMonthActual.getFullYear());
    keyDate = months[mese] + ' ' + anno;
    console.log("key Date: " + keyDate);
    console.log(mappaAgenda);
    if(!mappaAgenda.has(keyDate)){//Maggio 2024, []
        mappaAgenda.set(keyDate,[])
    }
    console.log(mappaAgenda);
    
    //dobbiamo definire il mese in cui si sta visualizzando la tabella e lo settiamo come
    //mese di default
    document.getElementById("mese").innerText = keyDate;

    dayOfWeek = data.getDay();
    dayOfMonth = data.getMonth();

    var nDay = 1;
    var appoggio = "";
    //con questo for creo tante righe quante sono le settimane del mese
    for(var i = 0; i < 6; i++){ 
         //scorro con un ciclo i giorni della settimana da 0 (lun) a 6 (dom)
    //mi sposto sulla riga che contiene i valori Lunedì	Martedì	Mercoledì	Giovedì	Venerdì	Sabato	Domenica
        appoggio += "<tr>";
        for(var j = 0; j < 7;j++){
            //se nDay == 1 e fixedDays[2] == 0
            //ottobre 2024
            //dataStartMonthActual -> 01/10/2024
            //fixedDays[6,0,1,2,3,4,5]; -> [dom,lun,mar,mer,gio,ven,sab]
            //dataStartMonthActual.getDay() -> 1
            //fixedDays[1] -> 0
            if((nDay == 1 && fixedDays[dataStartMonthActual.getDay()]== j) || (nDay >=2 && nDay<= getLastDate(data))){

                if(nDay == dayToday.getDate() && mese == dayToday.getMonth() &&
                    anno == dayToday.getFullYear()){
                        appoggio += "<td>" + getDayButton(nDay++,keyDate,"actualDay") + "</td>"; //funzione che permette di avere un bottone sul giorno
                   }else{
                    appoggio += "<td>" + getDayButton(nDay++,keyDate) + "</td>";
                   }
            }else{
                appoggio += "<td> </td>";
            }

        }
        appoggio += "</tr>";
    }
    table.innerHTML += appoggio;
}

function getDayButton(nDay,keyDate,actualDay){
    return "<button id='" + nDay + "' class ='dayButton " + actualDay + " " 
    + (mappaAgenda.get(keyDate)[nDay] != null ? "dayEvent" : "") + "' onclick='viewDay("+nDay+")'>" +nDay+"</button> ";
}

function nextMonth(){
    document.getElementById("pippo").innerHTML = "<tr><th>Lunedì</th><th>Martedì</th><th>Mercoledì</th><th>Giovedì</th><th>Venerdì</th><th>Sabato</<th><th>Domenica</th></tr>";
    if(actualMonthInTable == 11){
        actualMonthInTable = 0;
        actualYearInTable++;
    }else{
        actualMonthInTable++;
    }
    generateTable(actualYearInTable,actualMonthInTable);
}

function previousMonth() {
    document.getElementById("pippo").innerHTML = "<tr><th>Lunedì</th><th>Martedì</th><th>Mercoledì</th><th>Giovedì</th><th>Venerdì</th><th>Sabato</th><th>Domenica</th></tr>";
    if (actualMonthInTable == 0) {
        actualMonthInTable = 11;
        actualYearInTable--;
    }
    else {
        actualMonthInTable--;
    }
    generateTable(actualYearInTable, actualMonthInTable);
}

function getLastDate(actualDay){
    //data = 2024,maggio,31
    var data = new Date(actualDay.getFullYear(),actualDay.getMonth()+1,0);
    return data.getDate();
}

function viewDay(day){
    document.getElementById("modal").style.display = 'block';
    document.getElementById("detailsDay").innerHTML = day + ' ' +keyDate;
    document.getElementById("detailsArea").setAttribute("detailsDay",day);
    document.getElementById("detailsArea").value = mappaAgenda.get(keyDate)[day] != null ? 
                                                    mappaAgenda.get(keyDate)[day] : "";
}


//funzione addEvent()
function addEvent(){
    var newEvento = document.getElementById("detailsArea");
    mappaAgenda.get(keyDate)[newEvento.getAttribute('detailsDay')] = newEvento.value;
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderBottom = "solid 3px";
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderColor = "red";
    document.getElementById('modal').style.display = 'none';
}
//deleteEvent()
function deleteEvent() {
    var newEvento = document.getElementById("detailsArea");
    mappaAgenda.get(keyDate)[newEvento.getAttribute('detailsDay')] = '';
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderBottom = "solid 1px";
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderColor = "grey";
    document.getElementById('modal').style.display = 'none';
}

generateTable(dayToday.getFullYear(),dayToday.getMonth());