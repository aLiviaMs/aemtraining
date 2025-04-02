package com.aemtraining.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class CatCardModel {

    @ValueMapValue
    @Default(values = "Default Title")
    private String title;

    @ValueMapValue
    @Default(values = "1")
    private String count;

    public String getCount() {
        return count;
    }

    public String getTitle() {
        return title;
    }
}