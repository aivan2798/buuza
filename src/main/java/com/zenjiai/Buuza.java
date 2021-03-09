package com.zenjiai;
//Entry file to app bundle
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zenjiai.Parser;
import com.zenjiai.Retina;
import com.zenjiai.tests.*;
import java.util.Map;

@SpringBootApplication
@Controller
//@RequestMapping("/")
public class Buuza {
  
  Test test_class;
   public static void main(String[] args) {
      SpringApplication.run(Buuza.class, args);
   }
   
    
  /*@GetMapping("/")
  @ResponseBody
  public String getName()
  {
   return "index";
  }
   */
   @GetMapping(value="/{endpt}")
   public String index(@PathVariable String endpt)
   {
      return endpt;
   }
   
  
 /*  @RequestMapping("/upfile")
   public String upload()
   {
    return "upfile";  
   }
  
     
  @RequestMapping("/")
   public String upload()
   {
    return "upfile";  
   }
    
      
   @RequestMapping("/mainmenu")
   public String upload()
   {
    return "upfile";  
   }
  */ 
   
   
   @PostMapping("/genesis")
   @ResponseBody
   public String fire(HttpSession user_env_tag,@RequestParam("name") String user_name,@RequestParam("pass") String user_pass)
   {
 
    Retina retina=new Retina(user_name,user_pass);
       user_env_tag.setAttribute("active_id",retina.active_id);
    /*   String pathname="/user_files/"+user_name;
 
      user_env_tag.setAttribute("name",user_name);
      user_env_tag.setAttribute("roots",pathname);
       */
    return retina.shout();
   }
   
 
  @PostMapping("/xpert")
  @ResponseBody
   public String xparse(HttpSession sess,@RequestParam("cmd") int cmd,@RequestParam("payload")String data)
   {
   // test_class=new Test(name,pass,cmd,data);
   Retina active_retina=new Retina(sess,cmd,data);
   
   active_retina.coordinate();
    //retina.auth();
    //Parser brain=new Parser(Integer.parseInt(data.get("method_id")),data.get("xpert_payload"));
   // brain.parse();
    
    return active_retina.msg;
  
   }
   
     @PostMapping("/content")
     @ResponseBody
   public String xparse(HttpSession sess,@RequestParam("content_name") String content_name,@RequestParam("cmd") int type,@RequestParam("payload")byte data[])
   {
   Retina active_retina=new Retina(sess,content_name,type,data);
  
   active_retina.coordinate();
    
    return active_retina.shout();
   }
   

     @PostMapping("/xcontent")
     @ResponseBody
   public String xparse(HttpSession sess)
   {
 /*  Retina active_retina=new Retina(sess,content_name,type,data);
  
   active_retina.coordinate();
    
    return active_retina.shout();*/
    return "testin";
   }
   
   
   @PostMapping("/testa")
   @ResponseBody
   public String tst(@RequestParam("cmd") String datum,@RequestParam("payload") String dt)
   {
    return datum;
   }
  
  @GetMapping("/data")
  @ResponseBody
  public String getName(HttpSession sess)
  {
   sess.setAttribute("name","blank");
   return sess.getId();
  }
  
    
  @GetMapping("/gdata")
  @ResponseBody
  public String getSName(HttpSession sess)
  {
  
  try
  {
  return (String)sess.getAttribute("name");
     
  }
  catch(IllegalStateException e)
  {
   return "failed";
  }  
  
  }
  
  
  
 public String data()
 {
  return "wakaflow";
 }
}