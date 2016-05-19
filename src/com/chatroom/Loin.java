package com.chatroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Loin {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("�������������");// ����Ҫ���ӵ�ServerSocket��IP��ַ�����ص�ServerSocket��������localhost��127.0.0.1��
		String server = input.next();
		System.out.print("�������û�����");
		String userName = input.next();
		Socket socket = null;
		try {
			socket = new Socket(server, 3927);// ����ip��ַ����Socket
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// ��Socket���ֽ�������������������
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());// ��Socket���ֽ���������������������
			Message mess = new Message();
			mess.setName(userName);
			mess.setType("login");
			oos.writeObject(mess);// �������û�����Message�����͸�ServerSocket������֤�û����Ƿ��Ѵ���
			mess = (Message) ois.readObject();// ����ServerSocket�ķ���
			if ("faile".equals(mess.getContent())) {// �û���������
				do {
					System.out.println("�û����Ѵ���,���������룡");
					System.out.print("�û�����");
					userName = input.next();
					mess.setName(userName);
					oos.writeObject(mess);
					mess = (Message) ois.readObject();
				} while ("faile".equals(mess.getContent()));
			}
			new MyClient(userName, ois, oos);// �����û������Ͷ�Ӧ�Ķ����������Ͷ������������MyClient���󣬿�ʼ����
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
