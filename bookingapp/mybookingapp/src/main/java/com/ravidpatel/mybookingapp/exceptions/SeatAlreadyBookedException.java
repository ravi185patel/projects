package com.ravidpatel.mybookingapp.exceptions;

public class SeatAlreadyBookedException extends  RuntimeException{
    public SeatAlreadyBookedException(String message){
        super(message);
    }
}
