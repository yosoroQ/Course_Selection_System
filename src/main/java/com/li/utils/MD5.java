package com.li.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5 {

	/*
	 * 1.һ�����û������ʵ�� MessageDigest ����ʼ����ʼ�����ö���ͨ��ʹ�� update �����������ݡ� �κ�ʱ�򶼿��Ե��� reset
	 * ��������ժҪ�� һ��������Ҫ���µ����ݶ��Ѿ��������ˣ�Ӧ�õ��� digest ����֮һ��ɹ�ϣ���㡣 ���ڸ��������ĸ������ݣ�digest
	 * ����ֻ�ܱ�����һ�Ρ� �ڵ��� digest ֮��MessageDigest �����������ó����ʼ״̬��
	 */
	public static String encrypByMd5(String context) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(context.getBytes());// update����
			byte[] encryContext = md.digest();// ���ø÷�����ɼ���

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < encryContext.length; offset++) {// ����Ӧ��ת����ʮ�����ƣ�
				i = encryContext[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
			//System.out.println("32result: " + buf.toString());// 32λ�ļ���
			//System.out.println("16result: " + buf.toString().substring(8, 24));// 16λ�ļ���
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(MD5.encrypByMd5("e10adc3949ba59abbe56e057f20f883e"));
	}
}