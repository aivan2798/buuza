package com.zenjiai;

import ai.expert.nlapi.v2.model.*;
import java.util.*;
import com.google.common.collect.*;
import com.zenjiai.Imprints;


public class Magezi
{
 //tokens list
 ArrayList<Token> main_gezi=new ArrayList<>();
 Long temp_id=new Long(0);
 //token verb_-lemma_id,group_id,dep_label map
 LinkedHashMap<String,Table<Long,Long,String>> gezi_map=new LinkedHashMap<>();
 //token id-lemma map
 HashMap<Long,String> manya=new HashMap<>();
// Multimap<String,Long> manya=ArrayListMultimap.create();
 TreeSet<Long> colsets=new TreeSet<>();
 Set<Long> mitwe=new LinkedHashSet<>();
 
  public Magezi(List<Token> gezi)
  {
   main_gezi.addAll(gezi);
  tokenMap();
  zimba();
  }
  
  public String loki()
  {
   return "gezi: "+main_gezi;
  }
  
  public void zimba()
  {
   for(Token token:main_gezi)
   {
   // mitwe.add(token.getDependency().getHead());
   Long head_id=token.getDependency().getHead();
   Long token_id=token.getDependency().getId();
   String token_label=token.getDependency().getLabel();
    
   if(gezi_map.containsKey(getTag(head_id)))
   {
   TreeSet<Long> colset=new TreeSet<>(gezi_map.get(getTag(head_id)).columnKeySet());
   
    
    gezi_map.get(getTag(head_id)).put(token_id,depIdGen(head_id,colset.last()),token_label);
   
   temp_id=head_id;
   }
   else
   {
   Table<Long,Long,String> dep_map=HashBasedTable.create();
   dep_map.put(token_id,depIdGen(head_id,Long.valueOf(0)),token_label);
   
   gezi_map.put(getTag(head_id),dep_map);
   
   temp_id=head_id;
   }
   }
  }
  
  
     //token id to lemma map
 public void tokenMap()
  {
    
    main_gezi.forEach((maingezi)->
    {
       manya.put(maingezi.getDependency().getId(),maingezi.getLemma());
    });
   
   }

 public String getTag(Long tid)
   {
    return manya.get(tid);
   }
   
   public int depGroupCheck(Long tid1,Long tid2)
   {
    int flags=0;
     if((tid1==tid2)&&(getTag(tid1))==(getTag(tid2)))
     {
      flags=Imprints.NO_GROUP_CHANGE;
     }
     else if((tid1!=tid2)&&(getTag(tid1))==(getTag(tid2)))
     {
      flags=Imprints.GROUP_CHANGE;
     }
     else if((tid1!=tid2)&&(getTag(tid1))!=(getTag(tid2)))
     {
     flags=Imprints.NEW_GROUP;
     }
    return flags; 
   }
   
  public Long depIdGen(Long tidc,Long cgid)
  {
   int switch_b=depGroupCheck(temp_id,tidc);
  Long gim=new Long(0);
   switch(switch_b)
   {
    case Imprints.NO_GROUP_CHANGE:
      gim=Long.valueOf(cgid);
    break;
    case Imprints.GROUP_CHANGE:
        gim=Long.valueOf(cgid+1);
    break;
    case Imprints.NEW_GROUP:
      gim=Long.valueOf(0);  
    break;
   }
   return gim;
  }
   
   public LinkedHashMap<String,Table<Long,Long,String>> getDepMap()
   {
     return gezi_map;
   }
   
   public HashMap<Long,String> getIdMap()
   {
    return manya;
   }
}