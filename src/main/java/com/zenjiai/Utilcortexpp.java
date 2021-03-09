package com.zenjiai;

import org.springframework.util.LinkedCaseInsensitiveMap;
import com.google.common.collect.*;
import ai.expert.nlapi.v2.model.TokenType;
import ai.expert.nlapi.v2.model.Atom;
import ai.expert.nlapi.v2.model.POSTag;
import ai.expert.nlapi.v2.model.Token;
import ai.expert.nlapi.v2.model.Sentence;
import ai.expert.nlapi.v2.model.Relation;
import java.util.*;

public class Utilcortexpp
{
private Table<String,Long,List<Atom>>sentence_graph=HashBasedTable.create();
 
 
 //map token lemma to sentence index
 public Table<String,Long,List<Atom>> sentenceMapper(Token sentence_frags)
 {
 
  sentence_graph.put(sentence_frags.getLemma(),sentence_frags.getSentence(),sentence_frags.getAtoms());
   
   
  return sentence_graph;
 }
 
 public String qVerb(String question)
 {
  
  return (question.split(" "))[0];
 }
 
public Multimap<Long,Long> sentencePhaseMap(List<Sentence> doc_sentences)
{
 Multimap<Long,Long> sentences=ArrayListMultimap.create();
 Long sentence_index[]=new Long[1];
 sentence_index[0]=new Long(0);
 doc_sentences.forEach((sentence)->
 {
  for(Long phrase:sentence.getPhrases())
  {
  sentences.put(sentence_index[0],phrase);
  }
 sentence_index[0]++;
 });
 
 return sentences;
}
 
 
 
//get sentence index where the needed lemma's are present
 public ArrayList<Long> sentenceSniff(String lemma1,String lemma2)
 {
  Set<Long>lemma1_entry=sentence_graph.row(lemma1).keySet();
  Set<Long>lemma2_entry=sentence_graph.row(lemma2).keySet();
  ArrayList<Long> indexes=new ArrayList<Long>();
  
  for(long lemma1_sentence:lemma1_entry)
  {
   
      if(lemma2_entry.contains(lemma1_sentence))
    {
     indexes.add(lemma1_sentence);
    }
    
   }
   
  return indexes; 
  }
  
 
 //relation verb to (relation,lemma,phaseMap); 
  public Multimap<String,Table<String,String,Long>> relationMap(List<Relation> relations)
  {
    
    Multimap<String,Table<String,String,Long>>relation_multimap=ArrayListMultimap.create();
    
    relations.forEach((relation)->
    {
     Table<String,String,Long> relationtable=HashBasedTable.create();
     relation.getRelated().forEach((related_item)->{
       
     relationtable.put(related_item.getRelation(),related_item.getText(),related_item.getPhrase());
     });
     
     relation_multimap.put(relation.getVerb().getLemma(),relationtable);
       
     
    });
    
   return relation_multimap;
  }
  
  
  
  public String getMiss(List<Relation> question_relationship,String qv)
  {
  String miss[]={null};
 Multimap<String,Table<String,String,Long>>query_relmap=relationMap(question_relationship);
// List<Table<String,String,Integer>> table=new List<Table<String,String,Integer>>();
 
 
 
 question_relationship.forEach((active_relation)->
 {
 
 List<Table<String,String,Long>>table=(List<Table<String,String,Long>>)query_relmap.get(active_relation.getVerb().getLemma());
   
for(Table<String,String,Long>child_table:table)
{
 //LinkedCaseInsensitiveMap<String,Long> mapper=child_table.column("who");
if(child_table.column(qv).containsKey("sbj_who")||child_table.column(qv).containsKey("sbj_what"))
{

 miss[0]="sbj";
}else if(child_table.column(qv).containsKey("obj_who")||child_table.column(qv).containsKey("obj_what"))
{
 miss[0]=qv+" obj";
}
else if(child_table.containsRow("sbj_what")||child_table.containsRow("sbj_who"))
{
 miss[0]="obj";
}
else if(child_table.containsRow("obj_what")||child_table.containsRow("obj_who"))
{
 miss[0]="sbj";
}
 }
 
 });
 
 
 return miss[0];
   }
   
   //token id to lemma map
 public HashMap<Long,String> tokenMap(List<Token> tokens)
  {
    HashMap<Long,String> token_map=new HashMap();
    tokens.forEach((token)->
    {
       token_map.put(token.getDependency().getId(),token.getLemma());
    });
   return token_map;
   }
  
  //token head to (dep lemma,dep label, sentence) 
  public Multimap<String,Table<String,String,Long>> depMap(List<Token> tokens)
  {
    
    Multimap<String,Table<String,String,Long>> token_multimap=ArrayListMultimap.create();
    HashMap<Long,String> tks=tokenMap(tokens);
    
    
    tokens.forEach((token)->
    {
     
 Table<String,String,Long> tokentable=HashBasedTable.create();
   
        
     
     tokentable.put(token.getLemma(),token.getDependency().getLabel(),token.getSentence());
   
      token_multimap.put((token.getDependency().getHead()).toString(),tokentable);
      
    });
    
   return token_multimap;
  }
  
 //token type list 
 public HashMap<String,POSTag> tokenTypeMap(List<Token> tokens)
 {
  HashMap<String,POSTag> token_type_map=new HashMap();
  for(Token tk:tokens)
  {
   token_type_map.put(tk.getLemma(),tk.getPos());
  }
  
  return token_type_map;
 }
 //token method miss get
 public String getTMiss(List<Token> question_token,String qv)
  {
  String miss[]={null};
  miss[0]="not found 🤨";
 Multimap<String,Table<String,String,Long>>query_depmap=depMap(question_token);
// List<Table<String,String,Integer>> table=new List<Table<String,String,Integer>>();
 ArrayList<Table<String,String,Long>> map_keys= new ArrayList<>(query_depmap.values());
 
 
 map_keys.forEach((active_dep)->
 {
 
/*if(active_dep.row(qv).containsKey("nsubj"))
{

 miss[0]=qv+" sbj";
 
}else if(active_dep.row(qv).containsKey("obj"))
{
 miss[0]=qv+" obj";*/
 active_dep.row(qv).keySet().forEach((que_verv)->
 {
  if(que_verv!=null)
  {
   miss[0]=que_verv;
  }
 });
 
}

/*else if(active_dep.containsRow("sbj")||active_dep.containsRow("sbj"))
{
 miss[0]="obj";
}
else if(active_dep.containsRow("obj")||active_dep.containsRow("obj"))
{
 miss[0]="sbj";
}*/

);
 
 
 return miss[0];
   }
 
  //relation verb,lemma lis,relation map
  
 }
 
