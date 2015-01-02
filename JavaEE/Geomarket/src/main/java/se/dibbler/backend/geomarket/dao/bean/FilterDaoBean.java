/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.geomarket.dao.FilterDao;
import se.dibbler.backend.geomarket.dto.FilterDto;
import se.dibbler.backend.geomarket.entity.Filter;
import se.dibbler.backend.geomarket.generics.BaseDaoBean;
import se.dibbler.backend.geomarket.generics.Response;
import se.dibbler.backend.geomarket.mapper.FilterMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class FilterDaoBean extends BaseDaoBean<Filter, FilterDto> implements FilterDao<Filter, FilterDto> {

    public FilterDaoBean() {
        super(Filter.class);
    }

    @Override
    public Response create(FilterDto dto) {
        Response<Filter> filter = FilterMapper.getInstance().mapFromDtoToEntity(dto);
        if (filter.hasErrors) {
            return Response.error(filter.getError());
        }
        return super.create(filter.getData());
    }

}
