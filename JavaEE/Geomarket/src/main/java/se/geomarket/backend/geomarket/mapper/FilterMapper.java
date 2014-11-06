/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.FilterDto;
import se.geomarket.backend.geomarket.entity.Filter;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class FilterMapper extends BaseMapper<FilterDto, Filter> {
    
    private static final FilterMapper INSTANCE = new FilterMapper();
    
    public static FilterMapper getInstance() {
        return INSTANCE;
    }

    public FilterMapper() {
        super(FilterDto.class, Filter.class);
    }
    
    @Override
    public Filter mapFromDtoToEntity(FilterDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Filter destObject = mapper.map(dto, Filter.class);
        return destObject;
    }
    
    @Override
    public FilterDto mapFromEntityToDto(Filter entity) {
        Mapper mapper = new DozerBeanMapper();
        FilterDto destObject = mapper.map(entity, FilterDto.class);
        return destObject;
    }
    
    @Override
    public void updateEntityFromDto(Filter entity, FilterDto dto) {
        entity.setActive(dto.isActive());
        entity.setUser(UsersMapper.getInstance().mapFromDtoToEntity(dto.getUser()));
        //entity.setCategory(NameMapper.getInstance().mapFromDtoToEntity(dto.getCategory()));
        entity.setCompany(CompanyMapper.getInstance().mapFromDtoToEntity(dto.getCompany()));
        //entity.setEventType(NameMapper.getInstance().mapFromDtoToEntity(dto.getEventType()));
        entity.setUpdatedDate(new Date());
    }
    
}
