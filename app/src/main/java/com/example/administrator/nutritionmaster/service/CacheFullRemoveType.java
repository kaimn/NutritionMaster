package com.example.administrator.nutritionmaster.service;

import com.example.administrator.nutritionmaster.entity.CacheObject;

import java.io.Serializable;


/**
 * Remove type when cache is full.<br/>
 * when cache is full, compare object is cache with this class, delete the smallest one.<br/>
 * you can implements this interface.
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-12-26
 */
public interface CacheFullRemoveType<V> extends Serializable {

    /**
     * compare object <br/>
     * <ul>
     * <strong>About result</strong>
     * <li>if obj1 > obj2, return 1</li>
     * <li>if obj1 = obj2, return 0</li>
     * <li>if obj1 < obj2, return -1</li>
     * </ul>
     * 
     * @param obj1
     * @param obj2
     * @return
     */
    public int compare(CacheObject<V> obj1, CacheObject<V> obj2);
}
