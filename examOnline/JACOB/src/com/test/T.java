package com.test;

import java.io.*;

import org.dom4j.*;
import org.dom4j.io.*;

import com.jacob.activeX.*;
import com.jacob.com.*;


public class T {   
	public T(){
		
	}
	
	Paragraph  []paragraph;
	ActiveXComponent app = new ActiveXComponent( "Word.Application"); //����word
	  
	public void saveXMLto(String str){
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gbk");
		try {
			XMLWriter writer =new XMLWriter(new FileWriter(new File("12.xml")),format);
		//	 XMLWriter writer = new XMLWriter( new FileWriter( "output.xml" ));
			writer.write(this.createXMLDocument());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public Document createXMLDocument(){
		Document doc=null;
		doc=DocumentHelper.createDocument();
		doc.setXMLEncoding("gb2312");
		T t=new T();
		t.readWord();
		Element root=doc.addElement("Article");//�������ڵ�
		for(int i=0;i<t.readWord().length;i++){
			Element Paragraph=root.addElement("Paragraph");
			Character []C=new Character[t.readWord()[i].characters.length];
			C=t.readWord()[i].characters;
			Character []S=new Character[t.readWord()[i].characters.length];
			S=C;
			int k=0,count=0;
			for(int j=1;j<C.length;j++){
				if(S[k].compare(C[j])==true){
					S[count].text+=C[j].text;
					S[count].color=C[j].color;
					S[count].lable=C[j].lable;
					S[count].name=C[j].name;
					S[count].size=C[j].size;
					System.out.println(S[j-1].lable+" "+C[j].lable);
				
				}else{
					Element Font=Paragraph.addElement("Font");
					Font.setText(S[count].text);
					Font.addAttribute("Size", S[count].size);
					Font.addAttribute("Color", S[count].color); 
					Font.addAttribute("Name", S[count].name);
					k=j;
					j--;
					count++;
					S[count].text="";
				}
			
		}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gb2312");
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
	public static void main(String args[]){
		T t=new T();
	//	t.readWord();
		t.createXMLDocument();
	}
	
	
		public Paragraph [] readWord(){
		   try {
			 
		       String inFile = "c:\\Word.doc"; //ָ��Ҫ���_��word�ļ�
		       app.setProperty("Visible", new Variant(true)); //��false�r�O��word����Ҋ����true�r�ǿ�ҊҪ��Ȼ������Word���_�ļ����^��
		       Dispatch docs = app.getProperty("Documents").toDispatch();//���_��݋��
		       Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, 
		    		   new Object[] {
		    		   inFile, new Variant(false), new Variant(true)
		    		   } , new int[1]).toDispatch(); //���_word�ęn 
		      // int paragraphsCount=Dispatch.get(doc, "Count").toInt(); 
		   
		       Dispatch tables=Dispatch.get(doc, "Tables").toDispatch();
		       Dispatch table=Dispatch.call(tables,"Item",new Variant(1)).toDispatch();
		       Dispatch cols=Dispatch.get(table,"Columns").toDispatch();
		      
		       Dispatch rows = Dispatch.call(table, "Rows").toDispatch();
		       Dispatch tableRange=Dispatch.get(table,"Range").toDispatch();
		       Dispatch borders=Dispatch.get(tableRange,"Borders").toDispatch();
		       String borderStyle=Dispatch.get(borders,"InsideLineWidth").toString();
		       System.out.println(borderStyle+"Style");
		       
		       
		       Dispatch tableFormat=Dispatch.get(tableRange,"ParagraphFormat").toDispatch();
		       String align=Dispatch.get(tableFormat,"Alignment").toString();
		       System.out.println("align"+align);//�������λ��
		       
		       
		       int colCount = Dispatch.get(cols, "Count").toInt();
		       System.out.println("����colsΪ"+colCount);
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
		    		   String height=Dispatch.get(cell,"Width").toString();
		   
		    		//   Dispatch shading=Dispatch.get(cell,"Shading").toDispatch();
		    		   
		    		   
		    		   
		    		//   System.out.println("��ǰ���е�ֵΪ��");
		    		   
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
		    //	   String Align=Dispatch.get(ParagraphFormat,"Shading").toString();
		    	//   System.out.println(Align);
		    	   Dispatch chars=Dispatch.get (ParagraphRange, "Characters").toDispatch(); // ��ȡ�ö��ַ�
		    	 
		    	   int charCount =Dispatch.get (chars, "Count").toInt(); // ȡ���ַ�����
		    	   paragraph[i] =new Paragraph(charCount);
		    	   for(int j =0; j <paragraph[i].getCount(); j++){
		    		   paragraph[i].characters[j]=new Character();
		    		   
		    		   Dispatch chs=Dispatch.call(chars, "Item", new Variant(j +1)).toDispatch();
		    		   Dispatch font =Dispatch.get (chs, "Font").toDispatch();
		    
		    		//   Dispatch shading=Dispatch.get(chs,"Shading").toDispatch();
		    		//   paragraph[i].characters[j].BackgroundPatternColor=Dispatch.get(shading, "BackgroundPatternColor").toString();
		    		   
		    		   
		    		   
		    		   paragraph[i].characters[j].size=Dispatch.get(font, "Size").toString();
		    		   //��ȡ�ַ���С
		    		   
		    		   paragraph[i].characters[j].color=Dispatch.get(font,"Color").toString();
		    		   //��ȡ�ַ���ɫ
		    		   
		    		   paragraph[i].characters[j].name=Dispatch.get(font, "Name").toString();
		    		   //��ȡ�ַ�����
		    		   
		    		   paragraph[i].characters[j].text =Dispatch.get(chs, "Text").toString();
		    		   //��ȡ�ַ�����
		    		   
		    		   paragraph[i].characters[j].lable =  paragraph[i].characters[j].size+paragraph[i].characters[j].color+paragraph[i].characters[j].name;
		    		   //��ȡ�ַ�������
		    		   
		    		   
		    		//   System.out.print(paragraph[i].characters[j].BackgroundPatternColor+" ");
		    	   }
		    	   
		    	   
		    	 //  System.out.println("eee"+charCount);
		       }
		       
		       
		       
		    //   System.out.println(paragraphCount);
		   }catch (Exception e) {
		         e.printStackTrace();
		   }
		   finally {
			//   System.out.println();
		//	   app.invoke("Quit", new Variant[]{}); 
			 
		   }
		   return this.paragraph;
	
}
}