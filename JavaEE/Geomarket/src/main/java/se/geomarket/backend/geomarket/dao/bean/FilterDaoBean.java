/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.FilterDao;
import se.geomarket.backend.geomarket.entity.Filter;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Stateless
public class FilterDaoBean extends BaseDaoBean implements FilterDao{

    public FilterDaoBean() {
        super(Filter.class);
    }
    
}
