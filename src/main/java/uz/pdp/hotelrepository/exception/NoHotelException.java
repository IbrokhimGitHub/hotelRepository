package uz.pdp.hotelrepository.exception;

import java.io.IOException;

public class NoHotelException extends Exception {
    public NoHotelException() {

        super("there is no hotel like this id");
    }
}
