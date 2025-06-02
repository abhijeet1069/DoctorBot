package com.servion.doctorBot.faq.pojo;

public class FAQEntry {
    private String topic;
    private Answer answer; // Can be String or List<String>

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

	@Override
	public String toString() {
		return "FAQEntry [topic=" + topic + ", answer=" + answer + "]";
	}
}
