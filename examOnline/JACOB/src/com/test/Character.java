package com.test;

public class Character{ //����ַ���
	public String size; //�����С
	public String color; //������ɫ
	public String name; //��������
	public String text; //�ַ�����
	public String lable; //�����С
	public String bold;//�����ϸ �Ӵ�-1 ���Ӵ�0
	public String italic;//������б ��б-1 ����б0
	public String underline;//������»���Ϊ-1������Ϊ0
	public String BackgroundPatternColor;//���������ɫ
	public boolean compare(Character ch){ //�ַ��Ƚ�
	if(this.lable.equals(ch.lable))
		return true;
	else
		return false;
	}
	}