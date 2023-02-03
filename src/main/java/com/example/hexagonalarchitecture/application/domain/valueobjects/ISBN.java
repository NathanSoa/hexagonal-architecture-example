package com.example.hexagonalarchitecture.application.domain.valueobjects;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class ISBN {
    private String isbn;

    public ISBN(String isbn) {
        setIsbn(isbn);
    }

    private boolean validate(String value) {
        Pattern pattern = Pattern.compile("([0-9]{3}-?[0-9]{1}-?[0-9]{5}-?[0-9]{3}-?[0-9]{1})|([0-9]{1}-?[0-9]{5}-?[0-9]{3}-?[0-9]{1})");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public void setIsbn(String isbn) {
        if(!validate(isbn))
            throw new IllegalArgumentException("ISBN should be in valid format!");
        this.isbn = isbn;
    }
}
