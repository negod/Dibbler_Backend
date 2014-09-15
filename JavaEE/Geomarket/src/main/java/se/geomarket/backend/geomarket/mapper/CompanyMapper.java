/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyMapper extends BaseMapper<CompanyDto, Company> {

    private static final CompanyMapper INSTANCE = new CompanyMapper();

    private CompanyMapper() {
    }

    public static CompanyMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Company mapFromDtoToEntity(CompanyDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Company destObject = mapper.map(dto, Company.class);
        return destObject;
    }

    @Override
    public CompanyDto mapFromEntityToDto(Company entity) {
        Mapper mapper = new DozerBeanMapper();
        CompanyDto destObject = mapper.map(entity, CompanyDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Company entity, CompanyDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
