package be.hasan.libraSphere.copies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BarcodeAlreadyExistsException extends RuntimeException{
    public BarcodeAlreadyExistsException(){
        super("Barcode already in use. Please provide a unique barcode.");
    }
}
