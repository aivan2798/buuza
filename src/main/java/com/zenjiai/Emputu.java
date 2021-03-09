package com.zenjiai;

import com.zenjiai.Utilcortex;
import com.zenjiai.Imprintspp.Mainlemmas;
import ai.expert.nlapi.v2.model.AnalyzeDocument;
//import ai.expert.nlpai.v2.model.MainLemma;
import ai.expert.nlapi.v2.model.*;
import java.util.*;
//buuza co-ordinate center
public class Emputu
{

public void coordinat(AnalyzeDocument impulse,AnalyzeDocument mem)
{
 
}


public ArrayList<Mainlemmas> genIndex(AnalyzeDocument raw_analysis)
{
 List<MainLemma> indexes_list=raw_analysis.getMainLemmas();
 ArrayList<Mainlemmas> main_lemmas=new ArrayList<Mainlemmas>();
  
 indexes_list.forEach((index)->
 {
   Mainlemmas ml=new Mainlemmas();
   ml.lemma_name=index.getValue();
   index.getPositions().forEach((positions)->{ml.lemma_start.add(positions.getStart());
     ml.lemma_end.add(positions.getEnd());
   });
   
   main_lemmas.add(ml);
 });
  
 return main_lemmas;
}

public void parseQuery(int Query_Type,AnalyzeDocument query_resource)
{
  
}


}