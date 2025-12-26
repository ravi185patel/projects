package com.ravidpatel.mybookingapp.dto;

public class ConsumerDto {
    private String consumerId;
    private String consumerName;
    private String consumerPassword;
    private String consumerIdentityNo;

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerPassword() {
        return consumerPassword;
    }

    public void setConsumerPassword(String consumerPassword) {
        this.consumerPassword = consumerPassword;
    }

    public String getConsumerIdentityNo() {
        return consumerIdentityNo;
    }

    public void setConsumerIdentityNo(String consumerIdentityNo) {
        this.consumerIdentityNo = consumerIdentityNo;
    }
}
