package com.ravidpatel.mybookingapp.dto;

public final class ResponseDto<T> {
    private String status;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public static class ResponseDtoBuilder<T>{
        private String status;
        private T data;

        public ResponseDtoBuilder<T> setStatus(String status) {
            this.status = status;
            return this;
        }

        public ResponseDtoBuilder<T> setData(T data) {
            this.data = data;
            return this;
        }

        public ResponseDto<T> build(){
            return  new ResponseDto<>(this.status,this.data);
        }
    }
}
