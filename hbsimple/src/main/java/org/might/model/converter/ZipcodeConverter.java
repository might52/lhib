package org.might.model.converter;

import javax.persistence.AttributeConverter;

public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {

    @Override
    public String convertToDatabaseColumn(Zipcode zipcode) {
        return zipcode.getValue();
    }

    @Override
    public Zipcode convertToEntityAttribute(String s) {
        if (s.length() == 5) {
            return new GermanZipcode(s);
        } else if (s.length() == 4) {
            return new SwissZipcode(s);
        }
        throw new IllegalArgumentException("Invalid zipcode in database: " + s);
    }
}
