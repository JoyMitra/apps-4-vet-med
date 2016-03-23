package com.cis.ksu.ezcount.vo;

import java.util.Set;

/**
 * Created by Joy on 2/21/16.
 */
public class GroupInfoVO extends InfoVO
{
    private Set<String> ranchInfo;

    public Set<String> getRanchInfo() {
        return ranchInfo;
    }

    public void setRanchInfo(Set<String> ranchInfo) {
        this.ranchInfo = ranchInfo;
    }


}
