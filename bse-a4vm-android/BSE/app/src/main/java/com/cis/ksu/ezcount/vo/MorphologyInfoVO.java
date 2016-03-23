package com.cis.ksu.ezcount.vo;

import java.util.Set;

/**
 * Created by Joy on 2/21/16.
 */
public class MorphologyInfoVO extends InfoVO
{
    private Set<String> labels;
    private Set<String> limits;

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public Set<String> getLimits() {
        return limits;
    }

    public void setLimits(Set<String> limits) {
        this.limits = limits;
    }


}
