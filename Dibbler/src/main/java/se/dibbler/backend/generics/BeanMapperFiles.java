/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum BeanMapperFiles {

    one("baseEntityMapper"),
    two("baseTypeMapper"),
    three("categoryMapper"),
    four("companyMapper"),
    five("companySummaryMapper"),
    six("eventMapper"),
    seven("eventTypeMapper"),
    eight("languageMapper"),
    nine("languageTextMapper"),
    ten("publishedEventsMapper"),
    eleven("publishedEventsSummaryMapper"),
    twelve("roleMapper"),
    thirteen("usersMapper"),
    fourteen("eventTextMapper");

    private final String value;

    private BeanMapperFiles(String vale) {
        this.value = vale;
    }

    public String getFileName() {
        return "/mapper/" + value + ".xml";
    }

}
