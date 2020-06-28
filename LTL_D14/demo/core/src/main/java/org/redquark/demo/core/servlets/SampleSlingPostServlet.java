package org.redquark.demo.core.servlets;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { 
		Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST,
		"sling.servlet.paths=" + "/bin/submitdata" })
public class SampleSlingPostServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = -159625176093879129L;
	
	private static final Logger log = LoggerFactory.getLogger(SampleSlingPostServlet.class);
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		try {
			
			ResourceResolver resourceResolver = request.getResourceResolver();
			
			/**
			 * Getting the resource object via path
			 */
			Resource resource = resourceResolver.getResource("/content/demo/en");

			log.info("Resource is at path {}", resource.getPath());

			/**
			 * Adapt the resource to javax.jcr.Node type
			 */
			Node node = resource.adaptTo(Node.class);

			/**
			 * Create a new node with name and primary type and add it below the path specified by the resource
			 */
			Node newNode = node.addNode("demoNode", "nt:unstructured");
			
			/**
			 * Setting a name property for this node
			 */
			newNode.setProperty("name", "Demo Node");
			
			/**
			 * Commit the changes to JCR
			 */
			resourceResolver.commit();
			
		} catch (RepositoryException e) {
			
			log.error(e.getMessage(), e);
			
			e.printStackTrace();
			
		} catch (PersistenceException e) {
			
			log.error(e.getMessage(), e);
			
			e.printStackTrace();		
		}
	}
}