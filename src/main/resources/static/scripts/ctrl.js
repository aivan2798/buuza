var clicked=true;

var amenu_loaded=true;
var menu_loaded=false;
var menu_clicked=true;
function showCtrls()
{

 var ctrl_pic=document.getElementById("dropper");
 var ctrl_tab=document.getElementById("ctrls");
 
 if(clicked==false)
 {

 ctrl_pic.setAttribute("src","/res/dup.png");
ctrl_tab.removeAttribute("hidden");
 clicked=true;
 }
 else
 {
 
ctrl_pic.setAttribute("src","/res/ddown.png");

ctrl_tab.hidden="hidden";

clicked=false;
 }
}

function clearMenu()
{
 document.getElementById("menu_div").remove();
document.getElementById("mini").remove();
 
}

function inflate(endpoint)
{
var httpman;

 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.open("GET",endpoint,false);
httpman.send();
var node=httpman.responseText;


 var divtag="'<img id='mini' onClick='clearMenu()' src='/res/mini.png'/><iframe id='menu_div' src='adder.html'></iframe>";
 document.getElementById("heada").innerHTML+=node;


}



function menu()
{
 var httpman;
 
 if(amenu_loaded!=false)
 {
 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.open("GET","adder.html",false);
httpman.send();
var node=httpman.responseText;


 var divtag="'<img id='mini' onClick='clearMenu()' src='/res/mini.png'/><iframe id='menu_div' src='adder.html'></iframe>";
 document.getElementById("heada").innerHTML+=node;
 amenu_loaded=false;
 }
 else
 {
  clearMenu();
  amenu_loaded=true;
 }
}

function mains()
{
 //alert("boom");
 /*if(menu_clicked!=false)
 {

 //ctrl_pic.setAttribute("src","/res/dup.png");
 //child_menu.removeAttribu;
 if(menu_loaded==false)
 {
 inflate("mainmenu.html");
 child_menu= document.getElementById("mainmenu");
 //alert("false");
 log("one tyme");
 menu_clicked=true;
 menu_loaded=true;
 }
 else
 {
 child_menu.removeAttribute("hidden");
 log("un_hidden");
 //menu_loaded=true;
 menu_clicked=false;
 }
 }
 else
 {
 
//ctrl_pic.setAttribute("src","/res/ddown.png");

child_menu.setAttribute("hidden","true");
log("hidden");
//alert(child_menu.id);
//"hidden";
//menu_loaded=true
menu_clicked=true;
 }*/
 var child_menu=document.getElementById("mainmenu");
 if(menu_clicked!=false)
 {
  child_menu.style.setProperty("display","block");
  menu_clicked=false; 
 }
 else
 {
  child_menu.style.setProperty("display","none"); 
   
  menu_clicked=true;
 }
 
 
}

function log(log_data)
{
 var logman=document.getElementById("footer");
  logman.innerHTML=log_data;
 //alert(logman);
}

function askit()
{
 var txt=document.getElementById("ask_txt").value;
 
 var msgg=amsg("POST",URL_PARSE,EXE_QUERY,txt);
 
 ctrllog(msgg);
}

function ctrllog(lodata)
{
 var logm=document.getElementById("ctrls");
  logm.innerHTML="answer: "+lodata;
}
