package com.redhat.consulting.keycloak.rest.example;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.*;

public class UsernameChangeFormProvider implements RealmResourceProvider {

	private KeycloakSession session;

	public UsernameChangeFormProvider(KeycloakSession session) {
		this.session = session;
	}

	public Object getResource() {
		return this;
	}

	/**
	 *
	 * @return
	 */
	@GET
	@Produces("text/html")
	public String get() {
		// Return HTML form for changing username
		return null;
	}

	@POST
	@Produces("text/html")
	@Consumes("multipart/form-data")
	public String post(@FormParam("uniqueId") String id, @FormParam("existingUsername") String existingUsername, @FormParam("newUsername") String newUsername) {
		// Handle the POST from the form and process the form data to complete the password change
		return null;
	}

	public void close() {
		// NO-OP
	}
}
