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
		System.out.print("请输入服务器：");// 输入要连接的ServerSocket的IP地址（本地的ServerSocket可以输入localhost或127.0.0.1）
		String server = input.next();
		System.out.print("请输入用户名：");
		String userName = input.next();
		Socket socket = null;
		try {
			socket = new Socket(server, 3927);// 根据ip地址创建Socket
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// 用Socket的字节输出流创建对象输出流
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());// 用Socket的字节输入流创建对象输入流
			Message mess = new Message();
			mess.setName(userName);
			mess.setType("login");
			oos.writeObject(mess);// 将带有用户名的Message对象发送给ServerSocket用于验证用户名是否已存在
			mess = (Message) ois.readObject();// 接受ServerSocket的反馈
			if ("faile".equals(mess.getContent())) {// 用户名不可用
				do {
					System.out.println("用户名已存在,请重新输入！");
					System.out.print("用户名：");
					userName = input.next();
					mess.setName(userName);
					oos.writeObject(mess);
					mess = (Message) ois.readObject();
				} while ("faile".equals(mess.getContent()));
			}
			new MyClient(userName, ois, oos);// 根据用户名，和对应的对象输入流和对象输出流创建MyClient对象，开始聊天
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
