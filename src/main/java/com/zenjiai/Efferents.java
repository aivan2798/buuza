package com.zenjiai;
//for database operations
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.google.common.collect.*;
import com.google.common.primitives.*;
import java.io.*;



import com.zenjiai.Imprints;

public class Efferents
{
 String db_url;
 String session_id;
 String master_user;
 String master_pass;
 int query_ans;
 String query_anss;
 Connection brain_stem;
 
 String current_user;
 String current_pass;
 int current_id;
String current_file;
 String active_session;
 
 //wrapper to access database
 public PreparedStatement exquery(String query)
 {
  
   try
   {
   
   PreparedStatement statement = brain_stem.prepareStatement(query);

 return statement;
 
  
   }
   catch(SQLException sqlex)
   {
    return null;
   }
 }
 
 //efferents constructor
 public Efferents()
 {
     URI dbUri=null;
   //  active_session=id_session;
   try{
  dbUri = new URI(System.getenv("DATABASE_URL"));
  
        master_user = dbUri.getUserInfo().split(":")[0];
        master_pass= dbUri.getUserInfo().split(":")[1];
   }
   catch(URISyntaxException use)
   {
     
   }
   try
   {
        db_url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        brain_stem=DriverManager.getConnection(db_url,master_user,master_pass);
   }
   catch(SQLException ex)
   {
     
   }
 }
 
 public void setActiveKey(int key)
 {
  current_id=key;
 }
 
 public void setCurrentFile(String fname)
 {
  current_file=fname;
 }
 //authenticate user and store associated user_key
  public boolean auth(String userid,String userpass)
  {
   ResultSet auth_result=null;
   boolean auth_bool=false;
   int userkey;
   current_pass=userpass;
   current_user=userid;
   String auth_query="SELECT user_key FROM Celebrum WHERE user_id='"+userid+"' AND user_pass='"+userpass+"'";
   
  
  try
  {
  auth_result=exquery(auth_query).executeQuery();

  auth_bool= auth_result.next();
  if(auth_bool)
  {
  userkey=auth_result.getInt(1);
  current_id=userkey;
  }
  
 }
 catch(SQLException sqlex)
 {
   
 }
  return auth_bool;
 }
 
 
//upload a file to database
/*public void uploadContent(String impulse_name,int impulse_type,String impulse_content)
{
 String query="INSERT INTO neural_data (impulse_id,impulse_name,impulse_type,impulse_data) VALUES ('"+current_id+"','"+impulse_name+"','"+impulse_type+"','"+impulse_content+"')";
 try
 {
 PreparedStatement ps=exquery(query);
//ps.setBytes(1,impulse_content);
 ps.executeUpdate();
 query_anss="data in";
}
catch(SQLException sqlex)
{
  query_anss="upload: "+sqlex.getMessage();
}
}*/

public void uploadContent(String impulse_name,int impulse_type,byte impulse_content[])
{
 String query="INSERT INTO neural_data (impulse_id,impulse_name,impulse_type,impulse_data) VALUES ('"+current_id+"','"+impulse_name+"','"+impulse_type+"',?);";
 try
 {
 PreparedStatement ps=exquery(query);
ps.setBytes(1,impulse_content);
 ps.executeUpdate();
 query_anss="data in";
}
catch(SQLException sqlex)
{
  query_anss="upload: "+sqlex.getMessage();
}
}



//get information about a file in the database
public Imprints.FileProps pullFile(String file_name)
{
 ResultSet query_result=null;
 int count=1;
 
 Imprints.FileProps file_props=new Imprints.FileProps();
 String query="SELECT impulse_type,impulse_data FROM neural_data WHERE impulse_id='"+current_id+"' AND impulse_name='"+file_name+"';";
try
 {
 PreparedStatement ps=exquery(query);
// ps.setBytes(4,impulse_content);
 query_result=ps.executeQuery();
 if(query_result.next())
 {
//query_anss=new String(
file_props.file_name=file_name;
file_props.file_type=query_result.getInt(1);
file_props.file_data=query_result.getBytes(2);
//LinkedList<Byte> bytes=Bytes.asList(query_result.getBytes(2));
//File file_man=new File(query_result.getString(2)+"/"+file_name);
//bytes.remove(1);
/*try
{file_props.file_data=new FileInputStream(file_man);}
catch(FileNotFoundException fnf)
{
  
}*/
}
else
{
  file_props.file_name=file_name;
  String nop="no record";
file_props.file_type=0;
file_props.file_data=null;
//nop.getBytes();
}
}
catch(SQLException sqlex)
{
  query_anss="pull file: "+sqlex.getMessage();
  return null;
}
return file_props;
}

//store xpert analyze class file to database
public String setSerialData(String serialname,String datum)
{
  String query="UPDATE neural_data SET impulse_mem='"+datum+"' WHERE impulse_id="+current_id+" AND impulse_name='"+serialname+"'";
String set_res=null;
/*if(getSerialData(serialname)==null)
{*/
 try
 {
 PreparedStatement ps=exquery(query);
//ps.setString(1,datum);
 ps.executeQuery();
 query_anss="data in";
set_res="serial set";
}
catch(SQLException sqlex)
{
  query_anss="upload: "+sqlex.getMessage();
}
/*}
else
{
 set_res="serial_already_set";
}
*/
return set_res;
}

//read analyze class file serialdata/ file bytes from database
public String getSerialData(String file_name)
{
  ResultSet query_result=null;
 String query="SELECT impulse_mem FROM neural_data WHERE impulse_id="+current_id+" AND impulse_name='"+file_name+"';";
try
 {
 PreparedStatement ps=exquery(query);
// ps.setBytes(4,impulse_content);
 query_result=ps.executeQuery();
 query_result.next();
//query_anss=new String(
return query_result.getString(1);
}
catch(SQLException sqlex)
{
  query_anss="pull file: "+sqlex.getMessage();
  return null;
}
}

//return the user name associated with a given user_key
public String getUserName()
{
 ResultSet auth_result=null;
   boolean auth_bool=false;
   String auth_query="SELECT user_id FROM Celebrum WHERE user_key='"+current_id+"'";
  String sess_name=null; 
  try
  {
  auth_result=exquery(auth_query).executeQuery();

  auth_bool= auth_result.next();
  if(auth_bool)
  {
  sess_name=auth_result.getString(1);
  
  }
 }
 catch(SQLException sqlex)
 {
   
 }
  return sess_name;
}

//adduser to database
public void addUser()
{
  
}

//remove user from db
public void delUser()
{
  
}

//return an array of files the user has loaded
public ArrayList<String> getUserFiles()
{
   ResultSet auth_result=null;
   boolean auth_bool=false;
   int loop_counter=1;
   ArrayList<String> prefrontal_cortex=new ArrayList<String>();
   
   String auth_query="SELECT impulse_name FROM neural_data WHERE impulse_id='"+current_id+"'";
  
  try
  {
  auth_result=exquery(auth_query).executeQuery();

 while(auth_result.next())
 {
  prefrontal_cortex.add(auth_result.getString(loop_counter));
//  loop_counter++;
 }
 }
 catch(SQLException sqlex)
 {
 }
 return prefrontal_cortex;
}

//show active file
public boolean mark(String mark_man)
{
 ResultSet query_result;
 String query="UPDATE Celebrum SET actived_context='"+mark_man+"' WHERE user_key='"+current_id+"'";


  try
 {
 PreparedStatement ps=exquery(query);

 query_result=ps.executeQuery();

return true;
}
catch(SQLException sqlex)
{
  query_anss="pull file: "+sqlex.getMessage();
  return false;
}
}


}