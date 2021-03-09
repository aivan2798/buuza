var cache_clicked;
var active_id;
function more(endpoint,active)
{
  var httpman;
 if (window.XMLHttpRequest){
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
    }
httpman.open("GET",endpoint,false);
httpman.send();
var node=httpman.responseText;

if(active_id!=null)
{
  document.getElementById(active_id).remove();
}

 document.getElementById("heada").innerHTML+=node;
active_id=active;
}


function clickColor(elem_id)
{
 var clicked_elem=document.getElementById(elem_id);
 clicked_elem.style.backgroundColor="green";
 
 cache_clicked=elem_id;
}


function txtAdd()
{
var clicked_elem="txt_select";
if(cache_clicked!=null)
{
 
document.getElementById(cache_clicked).style.backgroundColor="white";
}
  clickColor(clicked_elem);
  more("inputdiv.html","txt_div");
}


function fileAdd()
{
   var clicked_elem;
   if(cache_clicked!=null)
{
 
document.getElementById(cache_clicked).style.backgroundColor="white";
}
  clickColor("pdf_select");
  more("upfile.html","file_div");
}



function urlAdd()
{
   var clicked_elem="url_select";
 if(cache_clicked!=null)
{
 document.getElementById(cache_clicked).style.backgroundColor="white";
}
  clickColor(clicked_elem);
}


function closeup(elem_idd)
{
 //alert(elem_idd.id);
 elem_idd.remove();
 active_id=null;
 cache_clicked=null;
}

function mkMenu()
{
 more("mainmenu.html",null);
}