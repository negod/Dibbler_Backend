/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarketdb.entity.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarketdb.entity.dao.CompanyDao;
import se.geomarket.backend.geomarketdb.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Stateless
public class CompanyDaoBean extends BaseDaoBean implements CompanyDao{
    
    public CompanyDaoBean() {
        super(Company.class);
    }
    
}
