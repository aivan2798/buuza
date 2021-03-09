function shout()
{
 var httman;
  if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.open("GET","/data",false);
httpman.send();
var node=httpman.responseText;
alert(node);
}