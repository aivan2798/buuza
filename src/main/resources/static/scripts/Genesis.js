
function aposter(endpoint,data)
{
var httpman;
var node_data;
//log(data);
 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }

/*httpman.onreadystatechange=function()
{
if (httpman.readyState == 4 &&httpman.status== 200)
  {
   node_data=httpman.responseText;
  // txt_log.innerHTML=httpman.responseText;
  // logga.innerHTML="DOCUMENT LOADED";
   //log(node_data);
  }
}
*/
httpman.open("POST",endpoint,false);
httpman.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
httpman.send(data);
node_data=httpman.responseText;

return node_data;
// var divtag="'<img id='mini' onClick='clearMenu()' src='/res/mini.png'/><iframe id='menu_div' src='adder.php'></iframe>";
 //document.getElementById("heada").innerHTML+=node;
}

function genesis()
{
//var d=aposter(URL_TST,"name=admin&pass=admin");
//log(d);
//alert(d);
}