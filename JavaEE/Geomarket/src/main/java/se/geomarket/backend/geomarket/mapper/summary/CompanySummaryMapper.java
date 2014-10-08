/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper.summary;

import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.dto.summary.CompanySummaryDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import static se.geomarket.backend.geomarket.generics.BaseMapper.logger;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CompanySummaryMapper extends BaseMapper<CompanySummaryDto, Company> {

    private static final CompanySummaryMapper INSTANCE = new CompanySummaryMapper();

    private CompanySummaryMapper() {
    }

    public static CompanySummaryMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Company mapFromDtoToEntity(CompanySummaryDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanySummaryDto mapFromEntityToDto(Company entity) {
        try {
            CompanySummaryDto dto = new CompanySummaryDto();
            dto.setId(entity.getExtId());
            dto.setName(entity.getName());
            dto.setStreet(entity.getStreet());
            dto.setStreetNr(entity.getStreetNr());
            dto.setCity(entity.getCity());
            dto.setState(entity.getState());
            dto.setCountry(entity.getCity());
            dto.setPostalCode(entity.getPostalCode());
            dto.setWww(entity.getWww());
            dto.setPhone(entity.getPhone());
            return dto;
        } catch (Exception e) {
            logger.debug("[ Failed to map from entity {} to dto {} [ ETITY_ID: {} ] Error : {}", entity.getClass().getName(), CompanyDto.class.getName(), entity.getId(), e);
            return null;
        }
    }

    @Override
    public void updateEntityFromDto(Company entity, CompanySummaryDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
