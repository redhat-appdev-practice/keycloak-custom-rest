package com.redhat.consulting.keycloak.rest.example;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class UsernameChangeFormProviderFactory implements RealmResourceProviderFactory {

	public static final String ID = "changeusername";

	public RealmResourceProvider create(KeycloakSession session) {
		return new UsernameChangeFormProvider(session);
	}

	public void init(Config.Scope config) {

	}

	public void postInit(KeycloakSessionFactory factory) {

	}

	public void close() {

	}

	public String getId() {
		return ID;
	}
}
