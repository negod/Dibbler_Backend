/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyMapper extends BaseMapper<CompanyDto, Company> {

    private static final CompanyMapper INSTANCE = new CompanyMapper();

    public static CompanyMapper getInstance() {
        return INSTANCE;
    }

    public CompanyMapper() {
        super(CompanyDto.class, Company.class);
    }

}
