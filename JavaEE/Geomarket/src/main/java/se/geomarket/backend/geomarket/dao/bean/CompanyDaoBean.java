/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseDaoImpl;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Stateless
public class CompanyDaoBean extends BaseDaoImpl implements CompanyDao{
    
    public CompanyDaoBean() {
        super(Company.class);
    }
    
}
