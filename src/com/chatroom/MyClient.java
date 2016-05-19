package com.chatroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MyClient {
	private String name;// �û���
	private ObjectInputStream ois;// ����������
	private ObjectOutputStream oos;// ���������

	public MyClient(String name, ObjectInputStream ois, ObjectOutputStream oos) {
		this.name = name;
		this.ois = ois;
		this.oos = oos;
		new SendMess().start();
		new GetMess().start();
	}

	class SendMess extends Thread {// ������Ϣ
		@Override
		public void run() {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			while (true) {
				String content = input.next();
				Message mess = new Message();
				mess.setName(name);
				mess.setType("say");
				mess.setContent(content);
				try {
					oos.writeObject(mess);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class GetMess extends Thread {// ��ȡ��Ϣ
		@Override
		public void run() {
			while (true) {
				try {
					Message mess = (Message) ois.readObject();
					System.out.println(mess.getName() + "��" + mess.getContent());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}
}
