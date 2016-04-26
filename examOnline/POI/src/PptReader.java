import java.io.IOException;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.*;
import org.apache.poi.hslf.usermodel.*;

public class PptReader {

    /**
     * @param args
     */
    public static String getTextFromPPT2003(String path) {

        StringBuffer content = new StringBuffer("");
        TextRun[]t;//��ȡ�õ�Ƭ����������
        Hyperlink h[];//��ȡ���ֵĳ�����
        try {
        	
            SlideShow ss = new SlideShow(new HSLFSlideShow(path));// pathΪ�ļ���ȫ·�����ƣ�����SlideShow
            PictureData[] pda=  ss.getPictureData();
            System.out.println(pda[0].getSize());
            Slide[] slides = ss.getSlides();// ���ÿһ�Żõ�Ƭ
            for (int i = 0; i < slides.length; i++) {
                t = slides[i].getTextRuns();// Ϊ��ȡ�ûõ�Ƭ���������ݣ�����TextRun
          //      Hyperlink h[]=t[0].getHyperlinks();//
           //     System.out.println("123"+h[0].getAddress());
                for (int j = 0; j < t.length; j++) {
                    content.append(t[j].getText());// ����Ὣ�������ݼӵ�content��ȥ
                }
            //    content.append(slides[i].getTitle());
            }
            System.out.println(slides[0].getBackground().getFill().getForegroundColor());//��ȡ������ɫ
            System.out.println(slides[1].getTitle());//��ȡÿ��PPT�ı���
            System.out.println();
            System.out.println(slides.length+"slide");//��ȡppt������
            Shape s[]=slides[0].getShapes();
            if(s[0] instanceof Picture){
            	  Picture p=(Picture)s[0];
            	  System.out.println("picture"+p.getPictureName());
            }
          
           
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return content.toString();

    }



    public static void main(String[] args) {
        System.out.println(PptReader.getTextFromPPT2003("c:\\ppt.ppt"));
     //   System.out.println(PptReader.getTextFromPPT2007("c:/test.pptx"));
    }

}