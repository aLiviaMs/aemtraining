package com.aemtraining.core.models;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {ComponentExporter.class},
        resourceType = AngularNavigatorModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class AngularNavigatorModel implements ComponentExporter {
    protected static final String RESOURCE_TYPE = "aemtraining/components/angular-navigator";

    @ValueMapValue(injectionStrategy=InjectionStrategy.OPTIONAL)
    protected String url;

    @SlingObject
    protected ResourceResolver resourceResolver;

    protected List<Page> interestingPages;

    @PostConstruct
    protected void init() {
        if(url != null) {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page parentPage =  pageManager.getPage(url);

            if(parentPage != null) {
                Iterator<Page> iterator = parentPage.listChildren();

                interestingPages = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                        .collect(Collectors.toList());
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public List<Page> getInterestingPages() {
        return interestingPages;
    }

    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}