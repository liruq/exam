package com.test;

public class Paragraph { // ��ƶ�����
	public Character[] characters; // �ַ�������
	private int count = 0; // ÿ�ε��ַ�����
	public String linespacingrule;// ������м��
	public String characterunitleftindent;// �������������
	public String characterunitrightindent;// �������������
	public String lineunitafter;// �κ���
	public String lineunitbefore;// ��ǰ���
	public String baselinealignment;// �ı����뷽ʽ
	public String firstlineindent;// �����������ַ�
	public String alignment;// ����Ķ��뷽ʽ
	public String label;
	public Paragraph(){
		
	}
	public Paragraph(int count) {
		this.count = count;
		characters = new Character[this.count];
		
	}

	public int getCount() {
		return this.count;
	}
}