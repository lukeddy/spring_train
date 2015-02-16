package com.test.email;

/**
 * Created with IntelliJ IDEA.
 * User: tzq
 * Date: 13-5-31
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class Alert {
    private String alertNumber;
    private String date;
    private String subject;
    private String distribution;

    private String alertText;
    private String alertCard;
    private String contact;

    public Alert() {
    }

    public String getAlertNumber() {
        return alertNumber;
    }

    public void setAlertNumber(String alertNumber) {
        this.alertNumber = alertNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getAlertText() {
        return alertText;
    }

    public void setAlertText(String alertText) {
        this.alertText = alertText;
    }

    public String getAlertCard() {
        return alertCard;
    }

    public void setAlertCard(String alertCard) {
        this.alertCard = alertCard;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
