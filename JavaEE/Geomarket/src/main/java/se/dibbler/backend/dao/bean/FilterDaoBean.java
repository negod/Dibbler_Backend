/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.FilterDao;
import se.dibbler.backend.dto.FilterDto;
import se.dibbler.backend.entity.Filter;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class FilterDaoBean extends BaseDaoBean<Filter, FilterDto> implements FilterDao<Filter, FilterDto> {

    public FilterDaoBean() {
        super(Filter.class, FilterDto.class);
    }

    @Override
    public Response create(FilterDto dto) {
        Response<Filter> filter = super.mapFromDtoToEntity(dto);
        if (filter.hasErrors) {
            return Response.error(filter.getError());
        }
        return super.create(filter.getData());
    }

    @Override
    public Response<String> update(FilterDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
