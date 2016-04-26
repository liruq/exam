import java.io.IOException;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.*;
import org.apache.poi.hslf.usermodel.*;

public class PptRead {
	private String title[];
	private String title1[];//��ȡ�ı���
	private String back[];
	private String back1[];//��ȡ�ı�����ɫ
	private String imgName;//��ȡͼƬ������
	private String Format;//
	private String Format1;//ͼƬ�ĸ�ʽ
	
	private int height;//ͼƬ�ĸ߶�
	private int width;//ͼƬ�Ŀ��
	private int count_title;
	private int count_back;
	private int count_img;
	private int count_main;
	private int count;
	private int x;//ͼƬ��λ�õĺ�����
	private int y;//ͼƬ��λ�õ�������
	private String address;//���ĵĳ����ӵĵ�ַ
	private String address1;
	private TextRun t[];//��ȡ�õ�Ƭ����������
	private Hyperlink h[];//��ȡ�õ����ݵĳ�����
	public static void main(String args[]){
		PptRead pr=new PptRead();
		String filepath="d:/pptTest/answer.ppt";
		pr.mainRead(filepath);
		System.out.println(pr.titleRead(filepath)+pr.mainRead(filepath)+
				pr.imgRead(filepath)+pr.backRead(filepath));
		
	}
	
	public int titleRead(String filepath){
		try {
			
			SlideShow ss = new SlideShow(new HSLFSlideShow(filepath));
			Slide[] slides = ss.getSlides();// ���ÿһ�Żõ�Ƭ
			title =new String[slides.length];
			SlideShow ss1 = new SlideShow(new HSLFSlideShow("d:/pptTest/paper.ppt"));
			Slide[] slides1 = ss1.getSlides();// ���ÿһ�Żõ�Ƭ
			
			title1=new String[slides.length];
			
			for(int i=0;i<slides.length;i++){
				title[i]=slides[i].getTitle();//��ȡÿ��PPT�ı���
			}
			for(int j=0;j<slides1.length;j++){
				title1[j]=slides1[j].getTitle();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(title.length!=title1.length){
			count_title=0;
		}else{
			for(int i=0;i<title.length;i++){
				if(!title[i].equals(title1[i])){
					count_title=0;
					break;
				}else{
					count_title=1;
				}
			}
		}
		return count_title;
	}
	public int mainRead(String filepath){
		 try {
			
			SlideShow ss = new SlideShow(new HSLFSlideShow(filepath));
			Slide slide = ss.getSlides()[0];// ��õ�һ�Żõ�Ƭ
			
			SlideShow ss1 = new SlideShow(new HSLFSlideShow("d:/pptTest/paper.ppt"));
			Slide slide1 = ss1.getSlides()[0];// ��õ�һ�Żõ�Ƭ
			
			t=slide.getTextRuns();
			h=t[1].getHyperlinks();//��ȡ�����еĳ�����
			address="";
			if(h==null){//������h.length���жϣ���Ϊ��ʱ��hΪ�գ���������ͻ��������
				address="";
			}else{
			for(int i=0;i<h.length;i++){
				address+=h[i].getAddress();
			}
			}
			System.out.println("123"+address);
			
			t=slide1.getTextRuns();
			h=t[1].getHyperlinks();//��ȡ�����еĳ�����
			address1="";
			if(h==null){//������h.length���жϣ���Ϊ��ʱ��hΪ�գ���������ͻ��������
				address1="";
			}else{
			for(int i=0;i<h.length;i++){
				address1+=h[i].getAddress();
			}
			}
			System.out.println("321"+address1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(address.equals(address1)){
			count_main=1;
		}else{
			count_main=0;
		}
		return count_main;
	}
	public int backRead(String filepath){//��ȡͼƬ�ı�����ɫ
		try {
			
			SlideShow	ss = new SlideShow(new HSLFSlideShow(filepath));
			Slide[] slides = ss.getSlides();// ���ÿһ�Żõ�Ƭ
			back=new String[slides.length];
			for(int i=0;i<slides.length;i++){
				System.out.println(slides[i].getBackground().getFill().getForegroundColor().toString());
				back[i]=slides[i].getBackground().getFill().getForegroundColor().toString();
			}

			SlideShow	ss1 = new SlideShow(new HSLFSlideShow("d:/pptTest/paper.ppt"));
			Slide[] slides1 = ss1.getSlides();// ���ÿһ�Żõ�Ƭ
			back1=new String[slides.length];
			for(int j=0;j<slides1.length;j++){
				back1[j]=slides1[j].getBackground().getFill().getForegroundColor().toString();
				System.out.println(slides1[j].getBackground().getFill().getForegroundColor().toString());
			}
			if(back.length!=back1.length){
				count_back=0;
			}else{
				for(int i=0;i<back.length;i++){
					if(!back[i].equals(back1[i])){
						count_back=0;
						break;
					}else{
						count_back=1;
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count_back;
		
	}
	public int imgRead(String filepath){
		try {
			SlideShow	ss = new SlideShow(new HSLFSlideShow(filepath));
			Slide[] slide=ss.getSlides();
			 for (int i = 0; i < slide.length; i++) {
		            Shape[] shape = slide[i].getShapes();
		            for (int j = 0; j < shape.length; j++) {
		                if (shape[j] instanceof Picture) {
		                    Picture p = (Picture) shape[j];
		                    PictureData data = p.getPictureData();
		                    String imgName = p.getPictureName();//��ȡͼƬ������
		                    height=p.getOutline().getBounds().height;//��ȡͼƬ�ĸ߶�
		                    width=p.getOutline().getBounds().width;//��ȡͼƬ�Ŀ��
		                    y=p.getOutline().getBounds().y;
		                    x=p.getOutline().getBounds().x;
		                    Format=imgName+height+width+x+y;
		                    System.out.println(Format);
		                    
		                }
		            }
			 }
			 SlideShow	ss1 = new SlideShow(new HSLFSlideShow("d:/pptTest/paper.ppt"));
				Slide[] slide1=ss1.getSlides();
				 for (int i = 0; i < slide1.length; i++) {
			            Shape[] shape = slide1[i].getShapes();
			            for (int j = 0; j < shape.length; j++) {
			                if (shape[j] instanceof Picture) {
			                    Picture p = (Picture) shape[j];
			                    PictureData data = p.getPictureData();
			                    String imgName = p.getPictureName();//��ȡͼƬ������
			                    height=p.getOutline().getBounds().height;//��ȡͼƬ�ĸ߶�
			                    width=p.getOutline().getBounds().width;//��ȡͼƬ�Ŀ��
			                    y=p.getOutline().getBounds().y;
			                    x=p.getOutline().getBounds().x;
			                    Format1=imgName+height+width+x+y;
			                    System.out.println(Format1);
			                    
			                }
			            }
				 
				 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Format.equals(Format1)){
			count_img=1;
		}else{
			count_img=0;
		}
		return count_img;
	}

}
