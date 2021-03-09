package com.zenjiai;
//covers the nlp dirty work

import java.util.Map;
import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.DefaultCredentialsProvider;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.Credential;
import ai.expert.nlapi.security.BasicAuthenticator;

import ai.expert.nlapi.v2.API;

import ai.expert.nlapi.v2.cloud.AnalyzerConfig;
import ai.expert.nlapi.v2.cloud.Analyzer;
import ai.expert.nlapi.v2.message.AnalyzeResponse;
import ai.expert.nlapi.v2.model.AnalyzeDocument;
import ai.expert.nlapi.exceptions.NLApiException;
import com.zenjiai.Pdfparser;

import com.zenjiai.Imprints;
public class Parser
{
 String parse_data;
 String json_result;
 
 Analyzer brainer;
 //AnalyzeDocument impulse_data;
 String msg;
public Pdfparser pdfs=null;
  public Parser()
  {
  
 // parse_data=data;
  DefaultCredentialsProvider pass_pack=new DefaultCredentialsProvider();
  Authenticator askari=new BasicAuthenticator(pass_pack);
  Authentication pass_id=new Authentication(askari);
  brainer=new Analyzer(AnalyzerConfig.builder().withContext("Standard").withVersion(API.Versions.V2).withLanguage(API.Languages.en).withAuthentication(pass_id).build());
  
  }
  
  public AnalyzeDocument parse(String raw)
  {
  
 AnalyzeResponse impulse=null;
  try
  {
   
   impulse=brainer.analyze(raw);
   //json_result=impulse.toJSON();
  // impulse_data=impulse.getData();
   //msg=json_result;
   
   return impulse.getData();
  } catch(NLApiException e)
  {
    msg=e.getMessage();
    return null;
  }

   }
  
  public String shout()
  {
   return msg;
  }
  
  public void pdfman(byte datin[])
  {
  pdfs=new Pdfparser(datin); 
   
  }
  public AnalyzeDocument synapse(int type_id,byte datain[])
  {
  AnalyzeDocument parsed=null;
   switch(type_id)
   {
    case Imprints.TYPE_TXT:
   parsed=parse(new String(datain));
    break;
    case Imprints.TYPE_PDF:
     
    
   // parsed=parse()
    break;
    }
 return parsed;
  
  //return new String(datain);
  }
  
  
 
  
}