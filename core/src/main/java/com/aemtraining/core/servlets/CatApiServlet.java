package com.aemtraining.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.drew.lang.annotations.NotNull;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(service = Servlet.class,
           property = {
               Constants.SERVICE_DESCRIPTION + "=Cat API Servlet",
               "sling.servlet.methods=" + "GET",
               "sling.servlet.paths=" + "/bin/catapi"
           })
public class CatApiServlet extends SlingSafeMethodsServlet {

  private static final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";

    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request,
                         @NotNull SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get the 'count' parameter from the request, default to 1 if not provided
        String countParam = request.getParameter("count");
        int count = (countParam != null) ? Integer.parseInt(countParam) : 1;

        // Construct the URL with the count parameter
        String apiUrlWithCount = CAT_API_URL + "?limit=" + count + "&api_key=live_gzQxpWFEnGO8POsVec2wYFwmZZr6eFR8TYAa8lA3Sv9OftcPXZWJjtdOwNp63yIp";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiUrlWithCount);

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode catData = mapper.readTree(httpResponse.getEntity().getContent());
                response.getWriter().write(catData.toString());
            }
        }
    }
}