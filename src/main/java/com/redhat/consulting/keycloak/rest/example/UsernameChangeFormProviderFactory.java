package com.redhat.consulting.keycloak.rest.example;

import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class UsernameChangeFormProviderFactory implements RealmResourceProviderFactory {

	// This becomes the basis of the URL path
	// http(s)://<keycloak server>/auth/realms/<realm>/<ID>
	public static final String ID = "changeusername";

	private static final Logger LOG = Logger.getLogger(UsernameChangeFormProviderFactory.class);

	public RealmResourceProvider create(KeycloakSession session) {
		LOG.info("Creating UserChangeRestResource");
		return new UsernameChangeFormProvider(session);
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
