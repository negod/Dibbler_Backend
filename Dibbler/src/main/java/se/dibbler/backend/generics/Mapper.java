/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

import java.util.List;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class Mapper {
    
    private static final Logger LOG = LoggerFactory.getLogger(DibblerImageUtil.class);
    
    private static final DozerBeanMapper mapper = new DozerBeanMapper();
    private static final FileReader fileReader = new FileReader();
    private static final Mapper INSTANCE = new Mapper();
    
    private static final String FOLDER = "/mapper";
    
    public static Mapper getInstance() {
        return INSTANCE;
    }
    
    private Mapper() {
        Response<List<String>> fileList = fileReader.getFilesWithExtensionInFolder(FOLDER, FileType.XML);
        if (fileList.hasNoErrors) {
            mapper.setMappingFiles(fileList.getData());
        } else {
            LOG.error(fileList.getError().getErrorText());
        }
        
    }
    
    public DozerBeanMapper getMapper() {
        return mapper;
    }
    
}
