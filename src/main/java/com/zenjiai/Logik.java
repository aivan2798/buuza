package com.zenjiai;


import ai.expert.nlapi.v2.model.*;
import java.util.*;
import com.google.common.collect.*;

public class Logik
{
 
 public void whoFx1(String rverb,Multimap<String,Table<String,String,Integer>> relations)
 {
 /* relations.forEach(relation->
  {
   if(relation.getVerb().getLemma()==rverb)
   {
    
   }
  });*/
 }
 
 public String whoFx2(Multimap<String,Table<String,String,Long>> question_map,Multimap<String,Table<String,String,Long>> data_map,String miss_dep)
 {
  Set<String> question_verbs=question_map.keySet();
  Set<String> sentence_verbs=data_map.keySet();
  //existance
  String msg[]={null};
  msg[0]="start: ";
 /* for(String qverb:question_verbs)
  {
  msg[0]+="root: "+qverb+"\n";
  msg[0]+=data_map.get(qverb);*//*.forEach((data_table)->
  {
  msg[0]+=data_table+" :data: ";
  });
  msg[0]+="\n out-loop \n";*/
  //}
 
    
  return msg[0]+" fin: "+data_map;
 
  }
 //}
 
 
 public void spyda(Map<String,Long> rels,String missy)
 {
   
 }
 
 
}