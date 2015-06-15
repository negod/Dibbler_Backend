/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.dao.EventTextDao;
import se.dibbler.backend.dto.languagesupport.EventTextDto;
import se.dibbler.backend.dto.summary.EventTextSummaryDto;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class EventTextDaoBean extends BaseDaoBean<EventText, EventTextDto> implements EventTextDao<EventText, EventTextDto> {

    public EventTextDaoBean() {
        super(EventText.class, EventTextDto.class);
    }

    @Override
    public Response<String> create(EventTextDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response<EventTextSummaryDto> getEventTextSummaryByLanguage(String languageId) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("languageExtId", languageId);
            Response<List<EventText>> categoryTexts = super.getListByNamedQuery(DibblerNamedQueries.EVENTTEXT_FINDBY_LANGUAGE_EXTID, params);

            if (categoryTexts.hasErrors) {
                return Response.error(categoryTexts.getError());
            }

            if (categoryTexts.getData().size() > 2) {
                return Response.error(DaoError.EVENT_MORE_EVENTTEXTS_THAN_ALOWED);
            }

            EventTextSummaryDto dto = new EventTextSummaryDto();

            for (EventText text : categoryTexts.getData()) {
                dto.setHeading(text.getHeader());
                dto.setBody(text.getDescription());
            }

            return Response.success(dto);

        } catch (Exception e) {
            getLogger().error(DaoError.CATEGORY_GET_BY_LANGUAGE.getErrorText(), e.getMessage());
            return Response.error(DaoError.CATEGORY_GET_BY_LANGUAGE);
        }

    }

    @Override
    public Response<String> update(EventTextDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }
}
