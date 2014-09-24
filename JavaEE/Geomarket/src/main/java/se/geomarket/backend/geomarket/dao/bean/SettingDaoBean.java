/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.SettingDao;
import se.geomarket.backend.geomarket.entity.Setting;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class SettingDaoBean extends BaseDaoBean implements SettingDao {
    
     public SettingDaoBean() {
        super(Setting.class);
    }
    
}