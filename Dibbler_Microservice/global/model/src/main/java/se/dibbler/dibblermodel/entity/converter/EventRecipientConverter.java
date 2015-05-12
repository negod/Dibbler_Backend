/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.dibblermodel.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import se.dibbler.dibblermodel.constant.EventRecipientType;
import static se.dibbler.dibblermodel.constant.EventRecipientType.PUBLIC;
import static se.dibbler.dibblermodel.constant.EventRecipientType.SUBSCRIBER;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Converter(autoApply = true)
public class EventRecipientConverter implements AttributeConverter<EventRecipientType, String> {

    @Override
    public String convertToDatabaseColumn(EventRecipientType attribute) {
        if (attribute != null) {
            switch (attribute) {
                case PUBLIC:
                    return EventRecipientType.PUBLIC.name();
                case SUBSCRIBER:
                    return EventRecipientType.SUBSCRIBER.name();
                default:
                    throw new IllegalArgumentException("Unknown" + attribute);
            }
        } else {
            return EventRecipientType.NONE.name();
        }
    }

    @Override
    public EventRecipientType convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return EventRecipientType.valueOf(dbData);
        } else {
            return EventRecipientType.NONE;
        }
    }

}
