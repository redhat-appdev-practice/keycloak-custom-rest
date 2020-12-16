package com.redhat.consulting.keycloak.rest.example;

import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Locale;
import java.util.stream.Collectors;

@Path("changeusername")
public class UsernameChangeRestResource implements RealmResourceProvider, RealmResourceProviderFactory {

	private static final Logger LOG = Logger.getLogger(UsernameChangeRestResource.class);

	public static final String INDEX_HTML = "/web/index.html";
	private final KeycloakSession session;

	public UsernameChangeRestResource(KeycloakSession session) {
		this.session = session;
	}

	/**
	 * Serves the GET requests for this extension
	 * @param response The {@link HttpServletResponse}
	 * @param filename The filename requested
	 * @return A {@link Response} with either static content or a 404 error
	 */
	@GET
	@Path("{filename: .*}")
	public Response get(@Context HttpServletResponse response, @PathParam("filename") String filename) {
		LOG.infof("Request for '%s' received.", filename);
		String classPath;
		String contentType;
		String extension = filename.substring(filename.lastIndexOf('.')+1);
		switch (extension.toLowerCase(Locale.ROOT)) {
			case "jpg":
				contentType = "image/jpeg";
				break;
			case "gif":
				contentType = "image/gif";
				break;
			case "png":
				contentType = "image/png";
				break;
			case "js":
				contentType = "text/javascript";
				break;
			case "html":
			case "htm":
				contentType = "text/html";
				break;
			case "css":
				contentType = "text/css";
				break;
			default:
				contentType = "application/octet-stream";
		}
		if (filename.length() == 0) {
			classPath = INDEX_HTML;
		} else {
			classPath = String.format("/web/%s", filename);
		}
		LOG.infof("Request for '%s' mapped to classpath of '%s'.", filename, classPath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(classPath)));
		try {
			return Response.ok(reader.lines().collect(Collectors.joining())).type(contentType).build();
		} catch (UncheckedIOException uioe) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("text/plain")
	@Consumes("multipart/form-data")
	public String post(@Context Request request, @FormParam("uniqueId") String id, @FormParam("existingUsername") String existingUsername, @FormParam("newUsername") String newUsername) {
		// Handle the POST from the form and process the form data to complete the password change
		return String.format("%s requested their username to be changed from '%s' to '%s'", id, existingUsername, newUsername);
	}


	// This becomes the basis of the URL path
	// http(s)://<keycloak server>/auth/realms/<realm>/<ID>
	public static final String ID = "changeusername";

	public Object getResource() {
		return new UsernameChangeRestResource(session);
	}

	public RealmResourceProvider create(KeycloakSession session) {
		LOG.info("Creating UserChangeRestResource");
		return this;
	}

	public void init(Config.Scope config) {
		LOG.info("Initializing UserChangeRestResource");
		// NO-OP
	}

	public void postInit(KeycloakSessionFactory factory) {
		LOG.info("Post-init UserChangeRestResource");
		// NO-OP
	}

	public void close() {
		LOG.info("Closing UserChangeRestResource");
		// NO-OP
	}

	public String getId() {
		return ID;
	}
}
