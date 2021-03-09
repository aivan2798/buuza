package com.zenjiai;

//bridge between frontend and backend
import java.util.Map;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.springframework.web.multipart.MultipartFile;
import com.zenjiai.Efferents;
import com.zenjiai.Imprints;
import com.zenjiai.Logik;
import com.zenjiai.Parser;
import com.zenjiai.Pdfparser;
import com.zenjiai.Utilcortex;
import com.zenjiai.Magezi;
import com.zenjiai.Bwongo;
import com.zenjiai.Utilcortexpp;
import com.zenjiai.Imprintspp.Mainlemmas;

import com.google.gson.Gson;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ai.expert.nlapi.v2.model.AnalyzeDocument;

public class Retina
{
 Efferents effector;
 HttpSession impulse_bundle;
 
 String active_user;
 int im_key_id;
 int im_cmd,im_id,im_file_type;
 byte im_data[];
 String im_data_str;
 
 String msg="\t\tMessage___start\n";
 
 String xfile_name;
 int active_id;
 boolean auth_pass;
 
 Utilcortex utilcortex;
 
 String effector_impulse;
 
 //authentication construtor
 public Retina(String name,String pass)
 {
  effector=new Efferents();
  utilcortex=new Utilcortex();
 if(effector.auth(name,pass))
 {
   
 active_user=name; 
  active_id=effector.current_id;
  //im_cmd=Imprints.SET_ENV;
 
 /* 
  try
 {
   File file_man=new File(((String)session.getAttribute("roots")));
 
 file_man.createNewFile();
 } catch(Exception e) {
 }
 
 */
  
  msg=name+" authentication success\n";
 }
 else
 {
   msg=name+" failed\n";
 }
 }
 
 public String shout()
 {
   return msg;
 }
 
 //mostly endpoint for uploading data or deleting
 public Retina(HttpSession session,String rfile_name,int type,byte rdata[])
 {
 //,String excitory_tag)
utilcortex=new Utilcortex();
 Gson raw_object=new Gson();
AnalyzeDocument ripe_impulse_bundle=null;
 effector=new Efferents();
 Parser xpert_cortex=new Parser();

// File file_man=new File(fpath);

 effector.setActiveKey((int)session.getAttribute("active_id"));
 //String fpath=((String)session.getAttribute("roots"))+rfile_name;
 
  //File file_man=new File(fpath);
 
/* try
 {
  
 
 file_man.createNewFile();
 
 OutputStream os=new FileOutputStream(file_man); 
 os.write(rdata); 

 } catch(Exception e) {
 }
 
*/ 
if(type==Imprints.TYPE_TXT)
{
 effector.uploadContent(rfile_name,type,rdata);
     
     
   ripe_impulse_bundle=xpert_cortex.synapse(type,rdata);

    if(ripe_impulse_bundle!=null)
    {
    String ripe=raw_object.toJson(ripe_impulse_bundle);
    
   effector.setSerialData(rfile_name,ripe);
    }
  //impulse_data=impulse; 
  msg="txt data success";
}
else
{
  xpert_cortex.pdfman(rdata);
  msg="totals: "+xpert_cortex.pdfs.total_pgs;
 }
 }
 public Retina(int key_id)
 {
  
 }
 
 //endpoint for co-ordinating commands
 public Retina(HttpSession session,int rcmd,String rdata)
 {
 //,String excitory_tag)
 impulse_bundle=session;
 utilcortex=new Utilcortex();
 effector=new Efferents();
 
 effector.setActiveKey((int)session.getAttribute("active_id"));
 
 
 
 im_cmd=rcmd;
 im_data_str=rdata;
 
 active_id=effector.current_id;
 msg="co-ordinating message...."+active_id;

 //impulse_data=impulse; 
 }
 
 
 public void coordinate()
 {
  switch(im_cmd)
  {
    case Imprints.MK_SUMMARY:
      if(auth_pass)
      {
       
      }
      else
      {
        
      }
    break;
    
    case Imprints.MK_INDEX:
      ArrayList<Mainlemmas> indexes;
      Emputu anpu=new Emputu();
           String raw_memory=effector.getSerialData((String)impulse_bundle.getAttribute("ACTIVE"));
     AnalyzeDocument ripe_memory= utilcortex.xtractMemory(raw_memory);
     indexes=anpu.genIndex(ripe_memory);
     msg=new Gson().toJson(indexes);
      
     
    break;
    
    case Imprints.EXE_QUERY:
  //    Logik bwongo=new Logik();
  /*    Gson mem_impulse=new Gson();*
      Parser interpret_cortex=new Parser();
      AnalyzeDocument interpreted_bundle=interpret_cortex.parse(im_data_str);
     
      String stored_memory=effector.getSerialData(impulse_bundle.getAttribute("ACTIVE"));
      AnalyzeDocument ripe_mem=mem_impulse.fromGson(stored_memory,AnalyzeDocument);
    */
    Utilcortexpp utilfx=new Utilcortexpp();
    
   // Parser interpret_cortex=new Parser();
   AnalyzeDocument ripe_mem0=getAnalysisMem();
      AnalyzeDocument interpreted_bundlee=new Parser().parse(im_data_str);
   Magezi qmagezi=new Magezi(interpreted_bundlee.getTokens());
   Magezi dmagezi=new Magezi(ripe_mem0.getTokens());
  //   String miss=utilfx.getTMiss(interpreted_bundlee.getTokens(),utilfx.qVerb(im_data_str));
     
   //  msg=bwongo.whoFx2(utilfx.depMap(interpreted_bundlee.getTokens()),utilfx.depMap(ripe_mem0.getTokens()),miss); //   msg=interpreted_bundlee.getContent();
 //
Bwongo adam=new Bwongo(dmagezi.getDepMap(),qmagezi.getDepMap(),dmagezi.getIdMap(),qmagezi.getIdMap(),utilfx.qVerb(im_data_str));
  //  msg+="\n tokens: "+impulse_bundle.getAttribute("ACTIVE");
 //  msg=ripe_mem0.getTokens().toString();
  msg=adam.parse();
//msg="data: "+dmagezi.getDepMap();
   
  break;
     
    case Imprints.ADD_ANALYZE:
      if(auth_pass)
      {
       Parser think=new Parser();
      Imprints.FileProps active_file= effector.pullFile(im_data_str);
   /*   byte serial_data[]=utilcortex.makeSerial((Analysis)think.synapse(active_file.file_type,active_file.file_data));*/
     }
    break;
    
    case Imprints.ADD_FILE:
    
    break;
   
   case Imprints.GET_CONTENTS:
   ArrayList<String> temp_list=effector.getUserFiles();
   String template_json="["+'"'+"magic.txt"+'"'+","+'"'+"tata.txt"+'"'+","+'"'+"merlin.txt"+'"'+"]";
    String json_temp=new Gson().toJson(temp_list);
    msg=json_temp;
    break;
    
    case Imprints.SET_ACTIVATED:

     Imprints.FileProps file_meta;
   //  Parser xpert_cortex=new Parser();
     byte raw_xpert_data[];
     impulse_bundle.setAttribute("ACTIVE",im_data_str);
  
    file_meta=effector.pullFile(im_data_str);

 // Gson mem_impulse=new Gson();
    //  Parser interpret_cortex=new Parser();
  //    AnalyzeDocument interpreted_bundle=interpret_cortex.parse(im_data_str);
      
     /* String stored_memory=effector.getSerialData((String)impulse_bundle.getAttribute("ACTIVE"));*/
     
    //  AnalyzeDocument ripe_mem=mem_impulse.fromJson(stored_memory,AnalyzeDocument.class);
    AnalyzeDocument ripe_mem=getAnalysisMem();
    //utilcortex.xtractMemory(stored_memory);
  switch(file_meta.file_type)
  {
   case Imprints.TYPE_TXT:
     msg="data: "+ripe_mem.getContent();
    break;
  case Imprints.TYPE_PDF:
    Pdfparser pdfman=new Pdfparser(file_meta.file_data);
    msg="type_pdf"+file_meta.file_name+"<br/>"+pdfman.getTotalPgs()+"<br/>.....finished.....";
  break;
  }
  
//sgmsg=ripe;
    break;
     
     case Imprints.MK_TEST:
     
     break;
  }
 }
 
 
 public AnalyzeDocument getAnalysisMem()
 {
  String stored_memory=effector.getSerialData((String)impulse_bundle.getAttribute("ACTIVE"));
  
    //  AnalyzeDocument ripe_mem=mem_impulse.fromJson(stored_memory,AnalyzeDocument.class);
    
    return utilcortex.xtractMemory(stored_memory);
    
    
 }
 
}