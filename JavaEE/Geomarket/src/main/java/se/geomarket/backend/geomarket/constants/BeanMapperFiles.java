/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.constants;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum BeanMapperFiles {

    BASE_ENTITY("baseEntityMapper.xml"),
    BASE_NAME("baseNameMapper.xml"),
    COMPANY("companyMapper.xml"),
    COMPANY_SUMMARY("companySummaryMapper.xml"),
    EVENT_TYPE("eventTypeMapper.xml"),
    LANGUAGE("languageMapper.xml"),
    NAME("nameMapper.xml"),
    USER("usersMapper.xml"),
    USER_SUMMAY("userSummaryMapper.xml"),
    ROLE("roleMapper.xml");

    private final String fileName;

    BeanMapperFiles(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
