package com.zenjiai;
//xtra functions to be used
import ai.expert.nlapi.v2.model.AnalyzeDocument;
import java.io.*;
import com.zenjiai.Imprints;
import com.google.gson.Gson;
public class Utilcortex
{
 public AnalyzeDocument xtractMemory(String raw_memory)
 {
   Gson mem_impulse=new Gson();
  AnalyzeDocument ripe_mem=mem_impulse.fromJson(raw_memory,AnalyzeDocument.class); 
  
  return ripe_mem;
 }


public String storeMemory(AnalyzeDocument ripe_memory)
{
 return "yes_sir";
}
}