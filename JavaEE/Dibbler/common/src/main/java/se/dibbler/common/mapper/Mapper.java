/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.common.mapper;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;

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
    }

    public void setMapperFiles(MapperFile[] mapperFiles) {
        List<String> files = new ArrayList<>();
        for (MapperFile file : mapperFiles) {
            files.add(file.mapperName());
        }
        getMapper().setMappingFiles(files);
    }

    public DozerBeanMapper getMapper() {
        return mapper;
    }

}
