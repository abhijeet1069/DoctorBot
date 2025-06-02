package com.servion.doctorBot.faq.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    private String text;
    private List<String> list;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

	@Override
	public String toString() {
		return "Answer [text=" + text + ", list=" + list + "]";
	}
}
