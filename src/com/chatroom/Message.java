package com.chatroom;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	private String name;// �û���
	private String content;// ��Ϣ
	private String type;// ��Ϣ���ͣ�login��say��

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
