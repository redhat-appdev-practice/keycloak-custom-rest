package com.redhat.consulting.keycloak.rest.example;

import org.jboss.logging.Logger;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

public class UsernameChangeFormProvider implements RealmResourceProvider {

	private final KeycloakSession session;

	private static final Logger LOG = Logger.getLogger(UsernameChangeFormProvider.class);

	public UsernameChangeFormProvider(KeycloakSession session) {
		LOG.info("UsernameChangeFormProvider was called");
		this.session = session;
	}

	public Object getResource() {
		return new UsernameChangeRestResource(session);
	}

	public void close() {
		LOG.info("UsernameChangeFormProvider was closed");
		// NO-OP
	}
}
