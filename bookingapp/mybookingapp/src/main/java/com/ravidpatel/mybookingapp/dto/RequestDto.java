package com.ravidpatel.mybookingapp.dto;

public final class RequestDto<T> {
    private String status;
    private T data;

    public RequestDto() {
    }

    public RequestDto(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }


    public static class RequestDtoBuilder<T>{
        private String status;
        private T data;

        public RequestDtoBuilder<T> setStatus(String status){
            this.status = status;
            return this;
        }
        public RequestDtoBuilder<T> setData(T data){
            this.data = data;
            return this;
        }

        public RequestDto<T> build(){
            return new RequestDto<T>(this.status,this.data);
        }
    }
}
