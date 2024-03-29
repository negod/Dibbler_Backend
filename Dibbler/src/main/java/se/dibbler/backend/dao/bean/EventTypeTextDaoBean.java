/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.EventTypeTextDao;
import se.dibbler.backend.dto.languagesupport.EventTypeTextDto;
import se.dibbler.backend.entity.EventTypeText;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class EventTypeTextDaoBean extends BaseDaoBean<EventTypeText, EventTypeTextDto> implements EventTypeTextDao<EventTypeText, EventTypeTextDto> {

    public EventTypeTextDaoBean() {
        super(EventTypeText.class, EventTypeTextDto.class);
    }

    @Override
    public Response<String> create(EventTypeTextDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response<String> updateEventTypeNameByEventTextId(String name, String eventTypeTextId) {

        Response<EventTypeText> evetTypeText = super.getByExtId(eventTypeTextId);

        if (evetTypeText.hasErrors) {
            return Response.error(evetTypeText.getError());
        }

        evetTypeText.getData().setValue(name);

        return Response.success(evetTypeText.getData().getExtId());

    }

    @Override
    public Response<String> update(EventTypeTextDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
