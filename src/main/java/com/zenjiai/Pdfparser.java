package com.zenjiai;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
//import android.widget.*;
//import android.test.*;

public class Pdfparser
{
PDFTextStripper textmake;
PDDocument loaded_doc;
String extracted;
String ex_msg;

int total_pgs,curent_pgn;

/*public Pdfparser(InputStream sst)
{
  
  
}
*/
 public Pdfparser(byte doc[])
 {
	 
	
	 try
	 {
		 textmake=new PDFTextStripper();
		 loaded_doc=PDDocument.load(doc);

		 total_pgs=loaded_doc.getNumberOfPages();
	//text_board.append("\nPLEASE LOAD YOUR DOCUMENT");
	 }
	 catch(IOException ioe)
	 {

	 ex_msg="\nException: "+ioe.getMessage();
	 }
 }
 
	public String fileLoader(int page_no)
	{
	 String text_board=null;
	if(page_no<total_pgs)
	{
		try
		{
			
 		
		textmake.setStartPage(page_no);
		
		textmake.setEndPage(page_no);
		extracted=textmake.getText(loaded_doc);
		text_board=extracted;
		text_board+="\nTotal: "+total_pgs;
		
		}
		catch(IOException ioe)
		{
			text_board+=(ioe.getMessage());
		}
	}
	else
	{
	text_board="Invalid Page Number";
	}
		return text_board;
	}
 
 public String xtractAll()
 {
  StringBuilder stringa=new StringBuilder();
  curent_pgn=0;
  while(curent_pgn<total_pgs)
  {
  stringa.append(fileLoader(curent_pgn++));
  }
  
  return stringa.toString();
 }
 
 
 
 public int getTotalPgs()
 {
  return total_pgs;
 }
}