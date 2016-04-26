package com.test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.*;
import org.dom4j.io.*;

import com.jacob.activeX.*;
import com.jacob.com.*;


public class WordToXml {
	private Paragraph  []paragraph;
	ActiveXComponent app = new ActiveXComponent( "Word.Application"); //����word
	public WordToXml(){
		
	}
	public static void main(String args[]){
		WordToXml wtx=new WordToXml();
		wtx.createXMLDocument();
	//	HashMap hm=new HashMap();
	//	wtx.iterateWholeXML("C:\\12.xml", hm);
	}
		
	public Paragraph[] readWord(){
		try{
		 String inFile = "c:\\Word.doc"; //ָ��Ҫ�򿪵�word�ĵ�
	       app.setProperty("Visible", new Variant(true)); //���ÿɼ�
	       Dispatch docs = app.getProperty("Documents").toDispatch();//�򿪱༭��
	       Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, 
	    		   new Object[] {
	    		   inFile, new Variant(false), new Variant(true)
	    		   } , new int[1]).toDispatch(); //��word�ĵ�
	       Dispatch tables=Dispatch.get(doc, "Tables").toDispatch();
	       Dispatch table=Dispatch.call(tables,"Item",new Variant(1)).toDispatch();
	       Dispatch cols=Dispatch.get(table,"Columns").toDispatch();//��ȡword�б��������
	       Dispatch rows = Dispatch.call(table, "Rows").toDispatch();//��ȡword�б��������
	       Dispatch tableRange=Dispatch.get(table,"Range").toDispatch();
	       Dispatch borders=Dispatch.get(tableRange,"Borders").toDispatch();
	       String insidelinewidth=Dispatch.get(borders,"InsideLineWidth").toString();//�ڲ��߿���ߵĿ��
	       String outsidelinewidth=Dispatch.get(borders,"OutsideLineWidth").toString();
	       System.out.println(insidelinewidth+"Style");
	       
	       Dispatch tableFormat=Dispatch.get(tableRange,"ParagraphFormat").toDispatch();
	       String align=Dispatch.get(tableFormat,"Alignment").toString();
	       System.out.println("align"+align);//�������λ��
	       int rowCount=Dispatch.get(rows,"Count").toInt();
	       int colCount = Dispatch.get(cols, "Count").toInt();//��������
	     
	       for(int p=1;p<=colCount;p++){
	    	   Dispatch col = Dispatch.call(cols, "Item", new Variant(p)).toDispatch();  //ѭ����ÿһ��
	    	   Dispatch cells=Dispatch.get(col, "Cells").toDispatch();//��ǰ���е�Ԫ��
	    	   int cellCount = Dispatch.get(cells, "Count").toInt();// ��ǰ���е�Ԫ���� ʵ�������������row  
	    	   for(int q=1;q<=cellCount;q++){
	    		   Dispatch cell = Dispatch.call(cells, "Item", new  Variant(q)).toDispatch(); //��ǰ��Ԫ�� 
	    		   Dispatch range=Dispatch.get(cell,"Range").toDispatch();
	    		   
	    		   Dispatch cellShading=Dispatch.get(range,"Shading").toDispatch();
	    		   String cellColor=Dispatch.get(cellShading,"BackgroundPatternColor").toString();
	    		   System.out.print(cellColor+"Color");//��ȡ��Ԫ��ı�����ɫ
	    		   String cellText=Dispatch.get(range, "Text").toString();
	   
	    		   cellText=cellText.substring(0,cellText.length()-1);
	    		   String cellWidth=Dispatch.get(cell,"Width").toString();//��ȡ��Ԫ��Ŀ��
	    		   String cellHeight=Dispatch.get(cell,"Height").toString();//��ȡ��Ԫ��ĸ߶�
	    		   
	    
	    	   }
	       
	       }
	       Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // ȡ��word�ļ�������
	       Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs").toDispatch(); // ���ж���
	       int paragraphCount = Dispatch.get(paragraphs, "Count").toInt(); // һ���Ķ�����
	       paragraph=new Paragraph[paragraphCount];
	       for(int i=0;i<paragraphCount;i++){
	    	   Dispatch Paragraph=Dispatch.call(paragraphs,
	    			   "Item",
	    			   new Variant(i +1)).toDispatch(); //��øö�����
	    	   Dispatch ParagraphRange =Dispatch.get(Paragraph, "Range").toDispatch();
	    	   Dispatch ParagraphFormat=Dispatch.get(ParagraphRange, "ParagraphFormat").toDispatch();
	    	 //  String linespace=Dispatch.get(ParagraphFormat,"LineSpacingRule").toString();
	    	   Dispatch chars=Dispatch.get (ParagraphRange, "Characters").toDispatch(); // ��ȡ�ö��ַ�
	    	   int charCount =Dispatch.get (chars, "Count").toInt(); // ȡ���ַ�����
	    	   paragraph[i] =new Paragraph(charCount);
	    	   for(int j =0; j <paragraph[i].getCount(); j++){
	    		   paragraph[i].characters[j]=new Character();
	    		   Dispatch chs=Dispatch.call(chars, "Item", new Variant(j +1)).toDispatch();
	    		   Dispatch font =Dispatch.get (chs, "Font").toDispatch();
	    		   paragraph[i].characters[j].size=Dispatch.get(font, "Size").toString();
	    		   //��ȡ�ַ���С
	    		   paragraph[i].characters[j].color=Dispatch.get(font,"Color").toString();
	    		   //��ȡ�ַ���ɫ
	    		   paragraph[i].characters[j].name=Dispatch.get(font, "Name").toString();
	    		   //��ȡ�ַ�����
	    		   paragraph[i].characters[j].text =Dispatch.get(chs, "Text").toString().trim();
	    		   //��ȡ�ַ�����
	    		   paragraph[i].characters[j].bold=Dispatch.get(font,"Bold").toString();
	    		   //��ȡ�ַ���ϸ
	    		   paragraph[i].characters[j].italic=Dispatch.get(font,"Italic").toString();
	    		   //��ȡ�ַ���б
	    		   paragraph[i].characters[j].underline=Dispatch.get(font,"Underline").toString();
	    		   //��ȡ�ַ��»���
	    		   paragraph[i].characters[j].lable =  paragraph[i].characters[j].size+paragraph[i].characters[j].color+paragraph[i].characters[j].name
	    		   +paragraph[i].characters[j].bold+paragraph[i].characters[j].italic+paragraph[i].characters[j].underline;
	    		   //��ȡ�ַ�������
	    	   }
	    	   
	    	   
	       }
	       
	       
	       
	   }catch (Exception e) {
	         e.printStackTrace();
	   }
	   finally {
		   app.invoke("Quit", new Variant[]{}); 
		 
	   }
	   return this.paragraph;

	}
	
	public Document createXMLDocument(){
		Document doc=null;
		doc=DocumentHelper.createDocument();
		doc.setXMLEncoding("gbk");
		T t=new T();
		t.readWord();
		 String inFile = "c:\\Word.doc"; //ָ��Ҫ�򿪵�word�ĵ�
	       app.setProperty("Visible", new Variant(false)); //���ÿɼ�
	       Dispatch docs = app.getProperty("Documents").toDispatch();//�򿪱༭��
	       Dispatch doc1 = Dispatch.invoke(docs, "Open", Dispatch.Method, 
	    		   new Object[] {
	    		   inFile, new Variant(false), new Variant(true)
	    		   } , new int[1]).toDispatch(); //��word�ĵ�
	       Dispatch tables=Dispatch.get(doc1, "Tables").toDispatch();
	       Dispatch table=Dispatch.call(tables,"Item",new Variant(1)).toDispatch();
	       Dispatch cols=Dispatch.get(table,"Columns").toDispatch();//��ȡword�б��������
	       Dispatch rows = Dispatch.call(table, "Rows").toDispatch();//��ȡword�б��������
	       Dispatch tableRange=Dispatch.get(table,"Range").toDispatch();
	       Dispatch borders=Dispatch.get(tableRange,"Borders").toDispatch();
	       String insidelinewidth=Dispatch.get(borders,"InsideLineWidth").toString();//�ڲ��߿���ߵĿ��
	       String outsidelinewidth=Dispatch.get(borders,"OutsideLineWidth").toString();
	       System.out.println(insidelinewidth+"Style");
	       
	       Dispatch tableFormat=Dispatch.get(tableRange,"ParagraphFormat").toDispatch();
	       String align=Dispatch.get(tableFormat,"Alignment").toString();
	       System.out.println("align"+align);//�������λ��
	       int rowCount=Dispatch.get(rows,"Count").toInt();
	       int colCount = Dispatch.get(cols, "Count").toInt();//��������
	       String Rowcount=""+rowCount;
	       String Colcount=""+colCount;
	       Element root=doc.addElement("Article");//�������ڵ�
	       Element Table=root.addElement("Table");//����Table�ڵ�
	       Table.addAttribute("InsideLineWidth", insidelinewidth);
	       Table.addAttribute("OutsideLineWidth", outsidelinewidth);
	       Table.addAttribute("Alignment", align);
	       Table.addAttribute("Colcount", Colcount);
	       Table.addAttribute("Rowcount", Rowcount);
	       
	       for(int p=1;p<=colCount;p++){
	    	   Dispatch col = Dispatch.call(cols, "Item", new Variant(p)).toDispatch();  //ѭ����ÿһ��
	    	   Dispatch cells=Dispatch.get(col, "Cells").toDispatch();//��ǰ���е�Ԫ��
	    	   int cellCount = Dispatch.get(cells, "Count").toInt();// ��ǰ���е�Ԫ���� ʵ�������������row  
	    	   for(int q=1;q<=cellCount;q++){
	   
	    		   Dispatch cell = Dispatch.call(cells, "Item", new  Variant(q)).toDispatch(); //��ǰ��Ԫ�� 
	    		   Dispatch range=Dispatch.get(cell,"Range").toDispatch();
	    		   
	    		   Dispatch cellShading=Dispatch.get(range,"Shading").toDispatch();
	    		   String cellColor=Dispatch.get(cellShading,"BackgroundPatternColor").toString();
	    		   System.out.print(cellColor+"Color");//��ȡ��Ԫ��ı�����ɫ
	    		   String cellText=Dispatch.get(range, "Text").toString();
	   
	    		   cellText=cellText.substring(0,cellText.length()-1);
	    		   String cellWidth=Dispatch.get(cell,"Width").toString();//��ȡ��Ԫ��Ŀ��
	    		   String cellHeight=Dispatch.get(cell,"Height").toString();//��ȡ��Ԫ��ĸ߶�
	    		   
	    		   Element Cell=Table.addElement("Cell");
	    		   Cell.setText(cellText);
	    		   Cell.addAttribute("Cellcolor", cellColor);
	    		   Cell.addAttribute("Cellwidth", cellWidth);
	    		   Cell.addAttribute("Cellheight", cellHeight);
	    	   }
	       }
		
		for(int i=0;i<t.readWord().length;i++){
			Element Paragraph=root.addElement("Paragraph");
			Character []C=new Character[t.readWord()[i].characters.length];
			C=t.readWord()[i].characters;
			Character []S=new Character[t.readWord()[i].characters.length];
			S=C;
			int k=0,count=0;
			for(int j=1;j<C.length;j++){
				if(S[k].compare(C[j])==true){
					S[count].text+=C[j].text.trim();
					S[count].color=C[j].color;
					S[count].lable=C[j].lable;
					S[count].name=C[j].name;
					S[count].size=C[j].size;
					System.out.println(S[j-1].lable+" "+C[j].lable);
				
				}else{
					Element Font=Paragraph.addElement("Font");
					Font.setText(S[count].text.trim());
					Font.addAttribute("Size", S[count].size);
					Font.addAttribute("Color", S[count].color); 
					Font.addAttribute("Name", S[count].name);
					k=j;
					j--;
					count++;
					S[count].text="";
					continue;
				}
				
			
		}
			Element Font=Paragraph.addElement("Font");
			Font.setText(S[count].text.trim());
			Font.addAttribute("Size", S[count].size);
			Font.addAttribute("Color", S[count].color); 
			Font.addAttribute("Name", S[count].name);
			//k=j;
			//j--;
		//	count++;
			//S[count].text="";
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gbk");
		try {
			XMLWriter writer =new XMLWriter(new FileWriter(new File("c:\\12.xml")),format);
		//	 XMLWriter writer = new XMLWriter( new FileWriter( "output.xml" ));
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
		
		
	}
	public void iterateWholeXML(String filename,HashMap<String,String> hm){
		
		SAXReader saxReader = new SAXReader();
		int count1=0,count2=0;
		try {
			Document document1=saxReader.read("c:\\13.xml");
			Document document=saxReader.read(new File(filename));
			 
			Element root = document.getRootElement();
			Element root1=document1.getRootElement();
			 
			Iterator iter = root.elementIterator("Paragraph"); //��ȡ���ڵ��µ��ӽڵ�paragraph 
			Iterator iter1=root1.elementIterator("Paragraph"); 
			String label_font[]=new String[100];
			String label_para[]=new String[100];
			String label_text[]=new String[100];
			
			String label_font1[]=new String[100];
			String label_para1[]=new String[100];
			String label_text1[]=new String[100];
			int i=0,j=0;
			int i1=0,j1=0;
			while(iter1.hasNext()){
				Element it = (Element) iter1.next();
				 Iterator its=it.elementIterator("Font");
				 while(its.hasNext()){
					 Element item=(Element)its.next();
					 Attribute size=item.attribute("Size");//size
					 Attribute color=item.attribute("Color");
					 Attribute name=item.attribute("Name");
					 
					 label_text1[i1]=item.getText().toString().trim();
					 
					 String Size=size.getValue();
					 String Color=color.getValue();
					 String Name=name.getValue();
					 label_font1[i1]=Name+Color+Size;
					 System.out.println("label_font1"+label_text1[i1]);
					 i1++;
				 }
			}
			Iterator iters1=root1.elementIterator("Table");
			 while(iters1.hasNext()){
				 Element it=(Element)iters1.next();
				 Iterator its=it.elementIterator("Cell");
				 while(its.hasNext()){
					 Element item=(Element)its.next();
					 Attribute Cellwidth=item.attribute("Cellwidth");
					 Attribute Cellheight=item.attribute("Cellheight");
					 Attribute Cellcolor=item.attribute("Cellcolor");
					 String cellheight=Cellheight.getValue();
					 String cellwidth=Cellwidth.getValue();
					 String cellcolor=Cellcolor.getValue();
					 label_para1[j1]=cellheight+cellwidth+cellcolor;
					 System.out.println("label_para1"+label_para1[j1]);
					 j1++;
				 }
			 }
			
			
			
			while (iter.hasNext()){
				 Element it = (Element) iter.next();
				 Iterator its=it.elementIterator("Font");//��ȡparagraph�ڵ��µ�Font�ڵ�
				 while(its.hasNext()){
					 Element item=(Element)its.next();
					 Attribute size=item.attribute("Size");//size
					 Attribute color=item.attribute("Color");
					 Attribute name=item.attribute("Name");
					 String Size=size.getValue();
					 String Color=color.getValue();
					 String Name=name.getValue();
					 label_font[i]=Name+Color+Size;
					 
					 label_text[i]=item.getText().toString().trim();
					 
					 System.out.println("label_font"+label_text[i]);
					 i++;
				 }
			 }
			 Iterator iters=root.elementIterator("Table");
			 while(iters.hasNext()){
				 Element it=(Element)iters.next();
				 Iterator its=it.elementIterator("Cell");
				 while(its.hasNext()){
					 Element item=(Element)its.next();
					 Attribute Cellwidth=item.attribute("Cellwidth");
					 Attribute Cellheight=item.attribute("Cellheight");
					 Attribute Cellcolor=item.attribute("Cellcolor");
					 String cellheight=Cellheight.getValue();
					 String cellwidth=Cellwidth.getValue();
					 String cellcolor=Cellcolor.getValue();
					 label_para[j]=cellheight+cellwidth+cellcolor;
					 System.out.println("label_para"+label_para[j]);
					 j++;
				 }
			 }
			 for(int m=0;m<i;m++){
				 for(int n=0;n<i1;n++){
					 if(label_text[m].equals(label_text1[n])){
						 if(label_font[m].equals(label_font1[n])){
							 count1++;
						 }
					 }
				 }
			 }
			
			 System.out.print("��������"+(count1+count2));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
