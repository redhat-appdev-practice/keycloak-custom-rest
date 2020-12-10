package com.redhat.consulting.keycloak.rest.example;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

public class UsernameChangeFormProvider implements RealmResourceProvider {

	private KeycloakSession session;

	public UsernameChangeFormProvider(KeycloakSession session) {
		this.session = session;
	}

	public Object getResource() {
		return this;
	}

	@GET
	@Produces("text/html")
	public String get() {
		// Return HTML form for changing password
		return null;
	}

	@POST
	@Produces("text/html")
	public String post() {
		// Handle the POST from the form and process the form data to complete the password change
		return null;
	}

	public void close() {
		// NO-OP
	}
}
