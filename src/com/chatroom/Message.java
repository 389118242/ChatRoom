package com.chatroom;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	private String name;// 用户名
	private String content;// 消息
	private String type;// 消息类型（login或say）

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
