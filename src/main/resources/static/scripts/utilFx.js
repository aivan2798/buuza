
async function fileParse(div_elem,file_id)
{
  //var logga=document.getElementById("mini_log");
  
 // logga.innerHTML="LOADING...";
 var afile=file_id.files[0];
 let byteArray = await fileToByteArray(afile);

 var impulse_obj="content_name="+afile.name+"&cmd="+TYPE_PDF+"&payload="+byteArray;
 load(impulse_obj);
 //log("lodded file: "+afile.name);
 div_elem.style.display="none";
}

function load(data)
{
  //var logga=document.getElementById("mini_log");
  var log_data;
 var httpman;
  //var txt_log=document.getElementById("txt_view");
var template;
 if (window.XMLHttpRequest)
        {
          httpman = new XMLHttpRequest();
        }
        else
        {
        httpman = new ActiveXObject("Microsoft.XMLHTTP");
        }
httpman.onreadystatechange=function()
{
if (httpman.readyState == 4 &&httpman.status== 200)
  {
   log_data=httpman.responseText;
  // txt_log.innerHTML=httpman.responseText;
  // logga.innerHTML="DOCUMENT LOADED";
   log("data:"+log_data);
  }
}

httpman.open("POST",URL_UPLOAD,true);
httpman.setRequestHeader("Content-type","application/x-www-form-urlencoded");
httpman.send(data);
}

function txtParse(div_elem,txt_view_id)
{
  var logga=document.getElementById("footer");
  var title_txt=document.getElementById("txt_title").value;
  div_elem.style.display="none";
  logga.innerHTML="LOADING...";
 var txt_data=document.getElementById("txt_pad").value;
 var impulse_obj="content_name="+title_txt+"&cmd="+TYPE_TXT+"&payload="+txt_data;

 load(impulse_obj);
 
}


//answer from https://stackoverflow.com/a/63018301
function fileToByteArray(file) {
    return new Promise((resolve, reject) => {
        try {
            let reader = new FileReader();
            let fileByteArray = [];
            reader.readAsArrayBuffer(file);
            reader.onloadend = (evt) => {
                if (evt.target.readyState == FileReader.DONE) {
                    let arrayBuffer = evt.target.result,
                        array = new Uint8Array(arrayBuffer);
                    for (byte of array) {
                        fileByteArray.push(byte);
                    }
                }
                resolve(fileByteArray);
            }
        }
        catch (e) {
            reject(e);
        } 
    })
}