package com.zenjiai;
//for constants and properties
import java.io.*;
import ai.expert.nlapi.v2.model.AnalyzeDocument;

public interface Imprints
{
 int MK_SUMMARY=199820;
 int MK_INDEX=199821;
 int EXE_QUERY=199822;
 int MK_INFO=199823;
 int MK_TEST=199824;
 
 int ADD_USER=209820;
 int DEL_USER=209821;
 int ADD_FILE=209822;
 int DEL_FILE=209823;
 int ADD_ANALYZE=209824;
 int GET_CONTENTS=209825;
 int SET_ACTIVATED=209826;
 
 int TYPE_TXT=219820;
 int TYPE_PDF=219821;
 
 int NO_GROUP_CHANGE=47692;
 int GROUP_CHANGE=47702;
 int NEW_GROUP=48692;
 
 public class FileProps
 {
  public String file_name;
  public int file_type;
  public byte[] file_data;
 }
 
}