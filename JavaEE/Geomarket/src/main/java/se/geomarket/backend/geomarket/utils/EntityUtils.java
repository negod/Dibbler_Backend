/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.utils;

import java.util.Date;
import java.util.UUID;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
public class EntityUtils {

    public static BaseEntity setEntityCreateData(BaseEntity entity) {
        entity.setCreatedDate(new Date());
        entity.setUpdatedDate(new Date());
        entity.setExtId(UUID.randomUUID().toString());
        return entity;
    }

    public static BaseEntity setUpdatedData(BaseEntity entity) {
        entity.setUpdatedDate(new Date());
        return entity;
    }

}
