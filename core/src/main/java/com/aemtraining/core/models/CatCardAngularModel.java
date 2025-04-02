package com.aemtraining.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {ComponentExporter.class},
        resourceType = CatCardAngularModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CatCardAngularModel implements ComponentExporter {
    protected static final String RESOURCE_TYPE = "aemtraining/components/cat-card-angular";

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected String title;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(intValues = 1)
    protected int count;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(booleanValues = false)
    protected Boolean disabled;
    
    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }
    
    public Boolean isDisabled() {
        return disabled;
    }

    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
