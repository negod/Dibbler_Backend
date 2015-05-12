/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service.validator;

import se.dibbler.backend.constants.RegExp;
import se.dibbler.backend.dto.summary.PublishedEventSummaryDto;
import se.dibbler.backend.error.ValidationError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class PublishedEventValidator {

    public static Response validatePublishedEventSummaryDto(PublishedEventSummaryDto dto) {
        try {

            if (dto.getCategoryId() != null) {
                if (!dto.getCategoryId().matches(RegExp.GUID)) {
                    return Response.error(ValidationError.ID_NOT_CORRECT, "CategoryId");
                }
            }

            if (dto.getCompanyId() != null) {
                if (!dto.getCompanyId().matches(RegExp.GUID)) {
                    return Response.error(ValidationError.ID_NOT_CORRECT, "CompanyId");
                }
            }

            if (dto.getEventId() != null) {
                if (!dto.getEventId().matches(RegExp.GUID)) {
                    return Response.error(ValidationError.ID_NOT_CORRECT, "EventId");
                }
            }

            if (dto.getLanguageId() != null) {
                if (!dto.getLanguageId().matches(RegExp.GUID)) {
                    return Response.error(ValidationError.ID_NOT_CORRECT, "LanguageId");
                }
            }

            if (dto.getEventTypeId() != null) {
                if (!dto.getEventTypeId().matches(RegExp.GUID)) {
                    return Response.error(ValidationError.ID_NOT_CORRECT, "EventTypeId");
                }
            }

            return Response.success(dto);

        } catch (Exception e) {
            return Response.error(ValidationError.GENERIC_VALIDATION_ERROR, "PublishedEventSummary");
        }

    }

}
