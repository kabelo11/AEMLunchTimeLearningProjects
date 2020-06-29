/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package tech.innisfree.assignments.servlet.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Simple Beverage Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes=" + "ServletAssignment/servlet/beverage"
        })
public class BeverageServlet extends SlingAllMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(BeverageServlet.class);
    private static final long serialVersionUid = 1L;

    @Override
    protected void doPost(final SlingHttpServletRequest req,
                          final SlingHttpServletResponse resp) throws ServletException, IOException {

        log.info("Beverage servlet invoked. Method = POST");
        int servletResponseCode = 0;
        try {

            String beverageName = null;
            try {
                beverageName = req.getRequestParameter("beverageName").toString();

                if (beverageName == null) {
                    throw new ServletException();
                }
            } catch (ServletException e) {
                servletResponseCode = HttpServletResponse.SC_BAD_REQUEST;
                log.error("Incorrect input message. You must supply a valid HTTP form with a parameter called beverageName");
            }

            ResourceResolver resourceResolver = req.getResourceResolver();
            Resource resources = resourceResolver.getResource("/content/ServletAssignment/beverages");

            log.info("Resource is at path {}", resources.getPath());

            Node node = resources.adaptTo(Node.class);
            Node newNode = null;
            try {
                if (node.hasNode(beverageName)) {
                    log.error("“Uniqueness constraint violation. The beverage already exists in the JCR");
                    servletResponseCode = 409;
                } else {
                    newNode = node.addNode(beverageName, "nt:unstructured");
                    newNode.setProperty("name", beverageName);
                    servletResponseCode = 200;

                    if (beverageName.equals("Coffee")) {
                        servletResponseCode = 418;
                        log.warn("Coffee is for the weak and timid - Prepare to be annihilated");
                    }
                }

            } catch (RepositoryException e) {
                servletResponseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                log.error(e.getStackTrace().toString());
            }
            resourceResolver.commit();
        } catch (RuntimeException e) {
            servletResponseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            log.error(e.getStackTrace().toString());
        }

        resp.getWriter().println(servletResponseCode);
    }


}
