package com.zenjiai;

import java.util.*;
import com.google.common.collect.*;



public class Bwongo
{

 LinkedHashMap<String,Table<Long,Long,String>> data_map=new LinkedHashMap<>();
 LinkedHashMap<String,Table<Long,Long,String>> qn_map=new LinkedHashMap<>();
 BiMap<Long,String> qtoken_ids=HashBiMap.create();
 HashMap<Long,String> dtoken_ids=new HashMap<>();
 Multimap<String,Long> data_tids=ArrayListMultimap.create();
 Multimap<String,Long> qn_tids=ArrayListMultimap.create();
 
 String qvv;
 
 public Bwongo(LinkedHashMap<String,Table<Long,Long,String>> data,LinkedHashMap<String,Table<Long,Long,String>> qn,HashMap<Long,String> data_id,HashMap<Long,String> q_id,String qverb)
 {
  data_map.putAll(data);
  qn_map.putAll(qn);
  dtoken_ids.putAll(data_id);
  qtoken_ids.putAll(q_id);
  data_tids=Multimaps.invertFrom(Multimaps.forMap(data_id),ArrayListMultimap.create());
  
  qvv=getMissDep(qverb);
 }
 
 public String parse()
 {
  String ans[]={null};
  ans[0]="results: ";
  String roots[]={null};
  roots[0]=" rootv: ";
  HashMap<Long,Integer> groups=new HashMap<>();
  HashMap<Long,String> pans=new HashMap<>();
  Multimap<String,Long> ans_list=ArrayListMultimap.create();
  
  Table<Long,Long,String> dt_table=HashBasedTable.create();
  
  
  
  qn_map.keySet().forEach((verb)->
  {
   
 Table<Long,Long,String> qn_table=qn_map.get(verb);
 
 if(data_map.containsKey(verb))
 {
   dt_table.putAll(data_map.get(verb));
   roots[0]+=verb+" "; 
   ans[0]+=" ";
  for(Long tid:qn_table.rowKeySet())
   {
    
    String tid_qn_value=qtoken_ids.get(tid);
   if(data_tids.containsKey(tid_qn_value))
   {
    //get id list matchin to a question word
    data_tids.get(tid_qn_value).forEach((dvalue)->{
   
   
  for(Long grp:dt_table.row(dvalue).keySet())
    {
   Integer curr_grp=groups.putIfAbsent(grp,1);
  
     if(curr_grp!=null)
     {
     groups.put(grp,curr_grp+1);
     }
  
 // ans[0]+="group: ";
    }
 
   //  ans[0]+=" key:"+tid_qn_value+" value: "+dvalue;
   //  ans[0]+="gtable: "+dt_table.row(dvalue);
   });
   
    }
    }
   }
   else
   {
   // ans[0]+=" "+verb+": null";
   }
   }
 // ans[0]+=dt_table;
  
  );
  //ans[0]+=qn_map;
    Long real_grp=Collections.max(groups.entrySet(), Map.Entry.comparingByValue()).getKey();      

   pans.putAll(dt_table.column(real_grp));
   ans_list.putAll(Multimaps.invertFrom(Multimaps.forMap(pans),ArrayListMultimap.create()));
  
  
  
  for(Object ans_id:new HashSet<>(new ArrayList(ans_list.get(qvv))))
  {
  ans[0]+=dtoken_ids.get((Long)ans_id);
  }
 // return "ans: "+new HashSet<>(new ArrayList(ans_list.get(qvv)))+" verbs: "+roots[0]+" missin: "+qvv+"    data_tids"+data_tids;
 
 return ans[0]+" <br/>missing:"+qvv+"<br/>Table: "+pans;
 }
 
 public String getMissDep(String qv)
 {
  String miss[]={null};
  miss[0]="no missing found";
  for(Table<Long,Long,String> tables:qn_map.values())
  {
  
  tables.row(qtoken_ids.inverse().get(qv)).values().forEach((missq)->{
  miss[0]=missq;//.toString(); 
   });
  }
  //qvv=miss[0];
  return miss[0];
 }
}
