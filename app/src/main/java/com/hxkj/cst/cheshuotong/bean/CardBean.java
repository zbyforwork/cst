package com.hxkj.cst.cheshuotong.bean;

/**
 * Created by luoyang on 2016/10/13. 测试数据 添加车辆
 */

public class CardBean {
    private String cardNick;
    private String cardModel;
    private String cardNumber;

    public CardBean() {
    }

    public CardBean(String cardNick, String cardModel, String cardNumber) {
        this.cardNick = cardNick;
        this.cardModel = cardModel;
        this.cardNumber = cardNumber;
    }

    public String getCardNick() {
        return cardNick;
    }

    public void setCardNick(String cardNick) {
        this.cardNick = cardNick;
    }

    public String getCardModel() {
        return cardModel;
    }

    public void setCardModel(String cardModel) {
        this.cardModel = cardModel;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
