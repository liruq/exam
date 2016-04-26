package com.word;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.*;
import org.dom4j.io.*;

import com.jacob.activeX.*;
import com.jacob.com.*;
import com.test.Character;
import com.test.Paragraph;

public class WordToXml {
	private static Paragraph paragraph[];
	private static int length;
	private int count_title;
	private int count_pf;
	private int count_main;
	private int count_table;
	private int count;// ��ȷ������
	private Element root;
	private Element Table;
	public int ReadTable(String filename) {
		SAXReader saxReader = new SAXReader();
		String text;// �ı�����
		String label;// �ı���ʽ
		String sum = "";// �ı����ݼ��ı���ʽ
		String sum1 = "";
		try {
			Document document = saxReader.read(new File(filename));
			Element root = document.getRootElement();
			Iterator iter = root.elementIterator("Table"); // ��ȡ���ڵ��µ��ӽڵ�table
			if(iter.hasNext()){
			Element it = (Element) iter.next();// ��ȡ��һ��table�ڵ�
			Attribute Paralabel = it.attribute("label");
			String paralabel = Paralabel.getValue();
			sum = paralabel;
			Iterator its = it.elementIterator("Cell");// ��ȡ��һ��table�ڵ��µ�cell�ڵ�
			while (its.hasNext()) {
				Element item = (Element) its.next();
				Attribute Fontlabel = item.attribute("label");
				text = item.getText().toString().trim();// ���font�ڵ��µ��ı�����
				label = Fontlabel.getValue();// ���font�ڵ��µ��ı���ʽ
				sum += label + text;// ��paragraph�µ������������ı�����ȫ������fontText��

			}
			}else{
				sum="0";
			}
			System.out.println(sum + "sum");

			Document document1 = saxReader.read(new File("d:/wordTest/paper.xml"));
			Element root1 = document1.getRootElement();
			Iterator iter1 = root1.elementIterator("Table"); // ��ȡ���ڵ��µ��ӽڵ�table
			if(iter1.hasNext()){
			Element it1 = (Element) iter1.next();// ��ȡ��һ��table�ڵ�
			Attribute Paralabel1 = it1.attribute("label");
			String paralabel1 = Paralabel1.getValue();
			sum1 = paralabel1;
			Iterator its1 = it1.elementIterator("Cell");// ��ȡ��һ��table�ڵ��µ�cell�ڵ�
			while (its1.hasNext()) {
				Element item = (Element) its1.next();
				Attribute Fontlabel = item.attribute("label");
				text = item.getText().toString().trim();// ���font�ڵ��µ��ı�����
				label = Fontlabel.getValue();// ���font�ڵ��µ��ı���ʽ
				sum1 += label + text;// ��paragraph�µ������������ı�����ȫ������fontText��

			}
			}else{
				sum1="0";
			}
			System.out.println(sum1 + "sum1");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sum1.equals(sum)) {
			count_table = 1;
		} else {
			count_table = 0;
		}
		System.out.println(count_table + "table");
		return count_table;

	}

	public int ReadMain(String filename) {
		SAXReader saxReader = new SAXReader();
		String text;// �ı�����
		String label;// �ı���ʽ
		String sum = "";// �ı����ݼ��ı���ʽ
		String sum1 = "";
		try {
			Document document = saxReader.read(new File(filename));
			Document document1 = saxReader.read(new File("d:/wordTest/paper.xml"));
			Element root = document.getRootElement();
			Iterator iter = root.elementIterator("Paragraph"); // ��ȡ���ڵ��µ��ӽڵ�paragraph
			Element it_1 = (Element) iter.next();// ��ȡ��һ��Paragraph�ڵ�
			Element it_2 = (Element) iter.next();// ��ȡ�ڶ���paragraph�ڵ�
			Element it=(Element)iter.next();//��ȡ������paragraph�ڵ�
			Iterator its = it.elementIterator("Font");// ��ȡ�ڶ���paragraph�ڵ��µ�font�ڵ�
			while (its.hasNext()) {
				Element item = (Element) its.next();
				Attribute Fontlabel = item.attribute("label");
				text = item.getText().toString().trim();// ���font�ڵ��µ��ı�����
				label = Fontlabel.getValue();// ���font�ڵ��µ��ı���ʽ
				sum += label + text;// ��paragraph�µ������������ı�����ȫ������fontText��

			}
			System.out.println(sum + "sum");

			Element root1 = document1.getRootElement();
			Iterator iter1 = root1.elementIterator("Paragraph"); // ��ȡ���ڵ��µ��ӽڵ�paragraph
			Element it_11 = (Element) iter1.next();// ��ȡ��һ��Paragraph�ڵ�
			Element it_12=(Element)iter1.next();//��ȡ�ڶ���paragraph�ڵ�
			Element it1 = (Element) iter1.next();// ��ȡ������paragraph�ڵ�
			
			Iterator its1 = it1.elementIterator("Font");// ��ȡ�ڶ���paragraph�ڵ��µ�font�ڵ�
			while (its1.hasNext()) {
				Element item = (Element) its1.next();
				Attribute Fontlabel = item.attribute("label");
				text = item.getText().toString().trim();// ���font�ڵ��µ��ı�����
				label = Fontlabel.getValue();// ���font�ڵ��µ��ı���ʽ
				sum1 += label + text;// ��paragraph�µ������������ı�����ȫ������fontText��

			}
			System.out.println(sum1 + "sum1");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sum1.equals(sum)) {
			count_main = 1;
		} else {
			count_main = 0;
		}
		System.out.println(count_main + "cm");
		return count_main;
	}

	public int ReadTitle(String filename) {
		SAXReader saxReader = new SAXReader();
		String text;// �ı�
		String label;// �ı���ʽ
		String sum = "";// �ı����ı���ʽ
		String sum1 = "";

		try {
			Document document = saxReader.read(new File(filename));
			Document document1 = saxReader.read(new File("d:/wordTest/paper.xml"));

			Element root = document.getRootElement();
			Iterator iter = root.elementIterator("Paragraph"); // ��ȡ���ڵ��µ��ӽڵ�paragraph
			Element it = (Element) iter.next();// ��ȡ��һ��Paragraph�ڵ�
			Attribute Paralabel = it.attribute("label");
			String paralabel = Paralabel.getValue();
			sum = paralabel;
			Iterator its = it.elementIterator("Font");// ��ȡ��һ��paragraph�ڵ��µ�font�ڵ�
			while (its.hasNext()) {
				Element item = (Element) its.next();
				Attribute Fontlabel = item.attribute("label");
				text = item.getText().toString().trim();// ���font�ڵ��µ��ı�����
				label = Fontlabel.getValue();// ���font�ڵ��µ��ı���ʽ
				sum += label + text;// ��paragraph�µ������������ı�����ȫ������fontText��

			}
			System.out.println(sum + "sum");

			Element root1 = document1.getRootElement();
			Iterator iter1 = root1.elementIterator("Paragraph"); // ��ȡ���ڵ��µ��ӽڵ�paragraph
			Element it1 = (Element) iter1.next();// ��ȡ��һ��Paragraph�ڵ�
			Attribute Paralabel1 = it1.attribute("label");
			String paralabel1 = Paralabel1.getValue();
			sum1 = paralabel1;
			Iterator its1 = it1.elementIterator("Font");// ��ȡ��һ��paragraph�ڵ��µ�font�ڵ�
			while (its1.hasNext()) {
				Element item = (Element) its1.next();
				Attribute Fontlabel = item.attribute("label");
				text = item.getText().toString().trim();// ���font�ڵ��µ��ı�����
				label = Fontlabel.getValue();// ���font�ڵ��µ��ı���ʽ
				sum1 += label + text;// ��paragraph�µ������������ı�����ȫ������fontText��

			}

			System.out.println("sum1" + sum1);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sum.equals(sum1)) {
			count_title = 1;
		} else {
			count_title = 0;
		}
		System.out.println(count_title);
		return count_title;
	}

	public int ReadParagraphFormat(String filename) {
		String sum = "";
		String sum1 = "";
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(filename));
			Element root = document.getRootElement();
			Iterator iter = root.elementIterator("Paragraph"); // ��ȡ���ڵ��µ��ӽڵ�paragraph
			Element it_1 = (Element) iter.next();// ��ȡ��һ��Paragraph�ڵ�
			Element it = (Element) iter.next();// ��ȡ�ڶ���paragraph�ڵ�
			Attribute Paralabel = it.attribute("label");
			String paralabel = Paralabel.getValue();
			sum = paralabel;
			System.out.println(sum + "sum");

			Document document1 = saxReader.read(new File(
					"d:/wordTest/paper.xml"));
			Element root1 = document1.getRootElement();
			Iterator iter1 = root1.elementIterator("Paragraph"); // ��ȡ���ڵ��µ��ӽڵ�paragraph
			Element it_11 = (Element) iter1.next();// ��ȡ��һ��Paragraph�ڵ�
			Element it1 = (Element) iter1.next();// ��ȡ�ڶ���paragraph�ڵ�
			Attribute Paralabel1 = it1.attribute("label");
			String paralabel1 = Paralabel1.getValue();
			sum1 = paralabel1;
			System.out.println(sum1 + "sum1");

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sum1.equals(sum)) {
			count_pf = 1;
		} else {
			count_pf = 0;
		}
		System.out.println(count_pf + "count_pf");
		return count_pf;
	}

	public static void main(String args[]) {
		WordToXml wtx = new WordToXml();
		//String inFile = "d:/wordTest/answer.doc"; // ָ��Ҫ�򿪵�word�ĵ�
		//paragraph=wtx.readWord(inFile);
	//	length=paragraph.length;
		 String filename="d:/wordTest/answer.xml";
		 wtx.ReadMain(filename);
		//wtx.createXmlDocument(inFile);

	}

	public Paragraph[] readWord(String inFile) {
		ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word
		app.setProperty("Visible", new Variant(false)); // ���ÿɼ�
		Dispatch docs = app.getProperty("Documents").toDispatch();// �򿪱༭��
		Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
				new Object[] { inFile, new Variant(false), new Variant(true) },
				new int[1]).toDispatch(); // ��word�ĵ�
		Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // ȡ��word�ļ�������
		Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs")
				.toDispatch(); // ���ж���
		int paragraphCount = Dispatch.get(paragraphs, "Count").toInt(); // һ���Ķ�����
		paragraph = new Paragraph[paragraphCount];
		for (int i = 0; i < paragraphCount; i++) {
			Dispatch Paragraph = Dispatch.call(paragraphs, "Item",
					new Variant(i + 1)).toDispatch(); // ��øö�����
			Dispatch ParagraphRange = Dispatch.get(Paragraph, "Range")
					.toDispatch();
			Dispatch ParagraphFormat = Dispatch.get(ParagraphRange,
					"ParagraphFormat").toDispatch();
			// System.out.println("basealign"+paragraph[i].alignment);
			Dispatch chars = Dispatch.get(ParagraphRange, "Characters")
					.toDispatch(); // ��ȡ�ö��ַ�
			int charCount = Dispatch.get(chars, "Count").toInt(); // ȡ���ַ�����
			paragraph[i] = new Paragraph(charCount);
			paragraph[i].linespacingrule = Dispatch.get(ParagraphFormat,
					"LineSpacingRule").toString();
			// ��ö�����м�� �����оൽ�౶�о�Ϊ0~5
			// System.out.println("align"+linespacingrule);
			paragraph[i].characterunitleftindent = Dispatch.get(
					ParagraphFormat, "CharacterUnitLeftIndent").toString();
			// ��ö������������
			// System.out.println("char"+characterunitleftindent);
			paragraph[i].characterunitrightindent = Dispatch.get(
					ParagraphFormat, "CharacterUnitRightIndent").toString();
			// ��ö������������
			paragraph[i].lineunitafter = Dispatch.get(ParagraphFormat,
					"LineUnitAfter").toString();
			// ��ö���Ķκ���
			paragraph[i].lineunitbefore = Dispatch.get(ParagraphFormat,
					"LineUnitBefore").toString();
			// ��ö���Ķ�ǰ���
			paragraph[i].baselinealignment = Dispatch.get(ParagraphFormat,
					"BaseLineAlignment").toString();
			// ��ö�����ı����뷽ʽ ���е��Զ���ֵΪ0~4
			paragraph[i].firstlineindent = Dispatch.get(ParagraphFormat,
					"FirstLineIndent").toString();
			// ��ö�������������������������ַ�
			paragraph[i].alignment = Dispatch.get(ParagraphFormat, "Alignment")
					.toString();
			// ��ö���Ķ��뷽ʽ

			paragraph[i].label = paragraph[i].linespacingrule
					+ paragraph[i].characterunitleftindent
					+ paragraph[i].characterunitrightindent
					+ paragraph[i].lineunitafter + paragraph[i].lineunitbefore
					+ paragraph[i].baselinealignment
					+ paragraph[i].firstlineindent + paragraph[i].alignment;
			// ��ö���ĸ�ʽ
			for (int j = 0; j < paragraph[i].getCount(); j++) {
				paragraph[i].characters[j] = new Character();
				Dispatch chs = Dispatch.call(chars, "Item", new Variant(j + 1))
						.toDispatch();
				Dispatch font = Dispatch.get(chs, "Font").toDispatch();
				/*
				 * Dispatch shading=Dispatch.get(chs,"Shading").toDispatch();
				 * paragraph
				 * [i].characters[j].BackgroundPatternColor=Dispatch.get
				 * (shading, "BackgroundPatternColor").toString();
				 * System.out.println
				 * ("backcolor"+paragraph[i].characters[j].BackgroundPatternColor
				 * );
				 */
				paragraph[i].characters[j].size = Dispatch.get(font, "Size")
						.toString();
				// ��ȡ�ַ���С
				paragraph[i].characters[j].bold = Dispatch.get(font, "Bold")
						.toString();
				// ��ȡ�ַ���ϸ

				paragraph[i].characters[j].color = Dispatch.get(font, "Color")
						.toString();
				// ��ȡ�ַ���ɫ
				paragraph[i].characters[j].name = Dispatch.get(font, "Name")
						.toString();
				// ��ȡ�ַ�����
				paragraph[i].characters[j].text = Dispatch.get(chs, "Text")
						.toString();
				// ��ȡ�ַ�����
				paragraph[i].characters[j].italic = Dispatch
						.get(font, "Italic").toString();
				// ��ȡ�ַ���б
				paragraph[i].characters[j].underline = Dispatch.get(font,
						"Underline").toString();
				// ��ȡ�ַ��»���
				paragraph[i].characters[j].lable = paragraph[i].characters[j].size
						+ paragraph[i].characters[j].color
						+ paragraph[i].characters[j].name
						+ paragraph[i].characters[j].bold
						+ paragraph[i].characters[j].italic
						+ paragraph[i].characters[j].underline;
				// ��ȡ�ַ�������
				// System.out.print(paragraph[i].characters[j].text+" ");
			}
			// System.out.println("eee"+charCount);
		}
		return paragraph;
	}

	public Document createXmlDocument(String inFile) {
		Document doc = null;
		doc = DocumentHelper.createDocument();
		
		ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word
		doc.setXMLEncoding("gbk");
		app.setProperty("Visible", new Variant(false)); // ���ÿɼ�
		Dispatch docs = app.getProperty("Documents").toDispatch();// �򿪱༭��
		Dispatch doc1 = Dispatch.invoke(docs, "Open", Dispatch.Method,
				new Object[] { inFile, new Variant(false), new Variant(true) },
				new int[1]).toDispatch(); // ��word�ĵ�
		root = doc.addElement("Article");// �������ڵ�
		Dispatch tables = Dispatch.get(doc1, "Tables").toDispatch();
		System.out.println("mmmmm"+Dispatch.get(tables,"Count").toInt());
		int table_sum=Dispatch.get(tables,"Count").toInt();
		
		if(table_sum!=0){
		Dispatch table = Dispatch.call(tables, "Item", new Variant(1))
				.toDispatch();
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();// ��ȡword�б��������
		Dispatch rows = Dispatch.call(table, "Rows").toDispatch();// ��ȡword�б��������
		Dispatch tableRange = Dispatch.get(table, "Range").toDispatch();
		Dispatch borders = Dispatch.get(tableRange, "Borders").toDispatch();
		String insidelinewidth = Dispatch.get(borders, "InsideLineWidth")
				.toString();// �ڲ��߿���ߵĿ��
		String outsidelinewidth = Dispatch.get(borders, "OutsideLineWidth")
				.toString();
		Dispatch tableFormat = Dispatch.get(tableRange, "ParagraphFormat")
				.toDispatch();
		String align = Dispatch.get(tableFormat, "Alignment").toString();
		System.out.println("align" + align);// �������λ��

		int rowCount = Dispatch.get(rows, "Count").toInt();
		int colCount = Dispatch.get(cols, "Count").toInt();// ��������
		String Rowcount = "" + rowCount;
		String Colcount = "" + colCount;
		String label = insidelinewidth + outsidelinewidth + align + Rowcount
				+ Colcount;
		 
		Table = root.addElement("Table");// ����Table�ڵ�
		Table.addAttribute("label", label);

		for (int p = 1; p <= colCount; p++) {
			Dispatch col = Dispatch.call(cols, "Item", new Variant(p))
					.toDispatch(); // ѭ����ÿһ��
			Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// ��ǰ���е�Ԫ��
			int cellCount = Dispatch.get(cells, "Count").toInt();// ��ǰ���е�Ԫ����
			// ʵ�������������row
			for (int q = 1; q <= cellCount; q++) {
				Dispatch cell = Dispatch.call(cells, "Item", new Variant(q))
						.toDispatch(); // ��ǰ��Ԫ��
				Dispatch range = Dispatch.get(cell, "Range").toDispatch();
				Dispatch cellShading = Dispatch.get(range, "Shading")
						.toDispatch();
				String cellColor = Dispatch.get(cellShading,
						"BackgroundPatternColor").toString();
				System.out.print(cellColor + "Color");// ��ȡ��Ԫ��ı�����ɫ
				String cellText = Dispatch.get(range, "Text").toString();

				cellText = cellText.substring(0, cellText.length() - 1);
				String cellWidth = Dispatch.get(cell, "Width").toString();// ��ȡ��Ԫ��Ŀ��
				String cellHeight = Dispatch.get(cell, "Height").toString();// ��ȡ��Ԫ��ĸ߶�
				String cellLabel = cellColor + cellWidth + cellHeight;
				Element Cell = Table.addElement("Cell");
				Cell.setText(cellText);
				Cell.addAttribute("label", cellLabel);

			}
		}
		}
		for (int i = 0; i < length; i++) {
			Element Paragraph = root.addElement("Paragraph");
			Paragraph.addAttribute("label", paragraph[i].label);
			Character[] C = new Character[paragraph[i].characters.length];
			C = paragraph[i].characters;
			Character[] S = new Character[paragraph[i].characters.length];
			S = C;
			int k = 0, count = 0;
			for (int j = 1; j < C.length; j++) {
				if (S[k].compare(C[j]) == true) {
					S[count].text += C[j].text.trim();
					S[count].color = C[j].color;
					S[count].lable = C[j].lable;
					S[count].name = C[j].name;
					S[count].size = C[j].size;
					S[count].bold = C[j].bold;
					S[count].italic = C[j].italic;
					S[count].underline = C[j].underline;
					System.out.println(S[j - 1].lable + " " + C[j].lable);

				} else {
					Element Font = Paragraph.addElement("Font");
					Font.setText(S[count].text.trim());
					Font.addAttribute("label", S[count].lable);

					k = j;
					j--;
					count++;
					S[count].text = "";
					continue;
				}

			}
			Element Font = Paragraph.addElement("Font");
			Font.setText(S[count].text.trim());
			Font.addAttribute("label", S[count].lable);
			// k=j;
			// j--;
			// count++;
			// S[count].text="";
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gbk");
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(
					"d:/wordTest/answer.xml")), format);
			// XMLWriter writer = new XMLWriter( new FileWriter( "output.xml"
			// ));
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

}
