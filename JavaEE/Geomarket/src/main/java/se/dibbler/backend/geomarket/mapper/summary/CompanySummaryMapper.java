/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.mapper.summary;

import se.dibbler.backend.geomarket.dto.summary.CompanySummaryDto;
import se.dibbler.backend.geomarket.entity.Company;
import se.dibbler.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CompanySummaryMapper extends BaseMapper<CompanySummaryDto, Company> {

    private static final CompanySummaryMapper INSTANCE = new CompanySummaryMapper();

    public static CompanySummaryMapper getInstance() {
        return INSTANCE;
    }

    public CompanySummaryMapper() {
        super(CompanySummaryDto.class, Company.class);
    }

}
