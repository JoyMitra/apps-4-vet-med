package com.cis.ksu.ezcount.vo;

import java.util.Set;

/**
 * Created by Joy on 2/21/16.
 */
public class VetInfoVO extends InfoVO
{
    private Set<String> vetInfos;

    public Set<String> getVetInfos() {
        return vetInfos;
    }

    public void setVetInfos(Set<String> vetInfos) {
        this.vetInfos = vetInfos;
    }
}
