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
    COMPANY("companyMapper.xml"),
    LANGUAGETEXT("languageTextMapper.xml"),
    COMPANY_SUMMARY("companySummaryMapper.xml"),
    EVENT_TYPE("eventTypeMapper.xml"),
    LANGUAGE("languageMapper.xml"),
    USER("usersMapper.xml"),
    BASE_TYPE("baseTypeMapper.xml"),
    ROLE("roleMapper.xml");

    private final String fileName;

    BeanMapperFiles(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
