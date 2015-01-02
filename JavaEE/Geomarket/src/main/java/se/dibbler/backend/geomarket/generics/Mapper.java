/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.generics;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.netbeans.rest.application.config.BeanMapperFiles;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class Mapper {

    private static final DozerBeanMapper mapper = new DozerBeanMapper();
    private static final Mapper INSTANCE = new Mapper();

    public static Mapper getInstance() {
        return INSTANCE;
    }

    private Mapper() {
        mapper.setMappingFiles(getMapperFiles());
    }

    private List<String> getMapperFiles() {
        List<String> files = new ArrayList<>();
        for (BeanMapperFiles file : BeanMapperFiles.values()) {
            files.add(file.getFileName());
        }
        return files;
    }

    public DozerBeanMapper getMapper() {
        return mapper;
    }

}
