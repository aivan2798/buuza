
function inflater(tin_id,data)
{
//var tin=document.getElementById(tin_id);
//tin.innerHTML=" ";
var node="<input class='file_names' value='"+data+"' onclick='setFile(value)' readonly/>";
//alert(node);
tin_id.innerHTML+=node;
}


function indexinflater(tin_id,data)
{
//var tin=document.getElementById(tin_id);
//tin.innerHTML=" ";
var node="<input class='file_names' value='"+data.lemma_name+data.lemma_start+"' onclick='setFile(value)' readonly/>";
//alert(node);
tin_id.innerHTML+=node;
}





function authmsg(method,endpoint,data)
{
var httpman;
var mkdata=data;
 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.open(method,endpoint,false);
httpman.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
httpman.send(mkdata);
var node=httpman.responseText;

return node;
// var divtag="'<img id='mini' onClick='clearMenu()' src='/res/mini.png'/><iframe id='menu_div' src='adder'></iframe>";
 //document.getElementById("heada").innerHTML+=node;
}



function amsg(method,endpoint,cmd,data)
{
var httpman;
var mkdata="cmd="+cmd+"&payload="+data;
 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.open(method,endpoint,false);
httpman.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
httpman.send(mkdata);
var node=httpman.responseText;

return node;
// var divtag="'<img id='mini' onClick='clearMenu()' src='/res/mini.png'/><iframe id='menu_div' src='adder.html'></iframe>";
 //document.getElementById("heada").innerHTML+=node;
}

function addUser()
{
 var user=authmsg("POST",URL_AUTH,"name=admin&pass=admin");
 log(user);
}

function genInd()
{
  var indexes=amsg("POST",URL_PARSE,MK_INDEX,"no_payload");
 // log(indexes)
 var countx=0;
 var ripe_index=JSON.parse(indexes);
 var ind_container=document.getElementById("index_list");
 if(window.getComputedStyle(ind_container)=="none")
 {
 // ind_container.style.display="initial";
  log(ind.container.style.display);
 }
 else
 {
 // ind_container.style.display="none";
  log(ind_container.style.display);
 }
 //ind_container.removeAttribute("hidden");
 var len=ripe_index.length;
 while(countx<len)
 {
  indexinflater(ind_container,ripe_index[countx]);
  countx=countx+1;
 }
// log("index loaded "+len);
 // document.getElementById("txt_view").innerHTML+="\n"+indexes;
}


function getUserFiles()
{
 //alert("data");
var data=amsg("POST",URL_PARSE,GET_CONTENTS,"great");
  //alert(data);
var tin=document.getElementById("files_list");
tin.innerHTML=" ";
 var file_list=JSON.parse(data);
 var countx=0;
 var len=file_list.length;
 //alert(len);
 while(countx<len)
 {
  inflater(tin,file_list[countx]);
  countx=countx+1;
 }
 log(len);
}


function setFile(file_name)
{
// alert(file_name);
 var set_ans=amsg("POST",URL_PARSE,SET_ACTIVATED,file_name);
 document.getElementById("txt_view").innerHTML=set_ans;

//gog(set_ans);
  
}

function ring()
{
 
 //var msgg=authmsg("POST",MK_TEST,URL_TST,"tryagain");
 //alert(msgg);
 var httpman;
//var mkdata="cmd="+cmd+"&payload="+data;
 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.open("GET","/data",false);
//httpman.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
httpman.send();
var node=httpman.responseText;
alert(node);
}
