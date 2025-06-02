package com.servion.doctorBot.faq.pojo;

import java.util.List;

public class FAQConfig {
    private List<FAQEntry> faq;

    public List<FAQEntry> getFaq() {
        return faq;
    }

    public void setFaq(List<FAQEntry> faq) {
        this.faq = faq;
    }

	@Override
	public String toString() {
		return "FAQConfig [faq=" + faq + "]";
	}
}

