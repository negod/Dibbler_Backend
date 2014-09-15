/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import javax.ejb.EJB;
import se.geomarket.backend.geomarket.dao.NameDao;
import se.geomarket.backend.geomarket.dto.NameDto;
import se.geomarket.backend.geomarket.entity.Name;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.NameMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NameService extends BaseWs<NameDto, Name, NameDao> {

    @EJB
    NameDao nameDao;

    @Override
    public NameDao getDao() {
        return nameDao;
    }

    @Override
    public BaseMapper<NameDto, Name> getMapper() {
        return NameMapper.getInstance();
    }

}
