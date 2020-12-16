# Adding a custom REST resource for serving HTML, CSS, JavaScript, and images in KeyCloak

## Background

Most of the documentation needed to accomplish this is in the [KeyCloak documentation](https://www.keycloak.org/docs/latest/server_development/index.html#_extensions_rest), but I found that the docs spread things around in a confusing manner. The point of this example project is to show the simplest possible path to achieve the goal. Many thanks to my client (whom I will mention if they give me permission) who helped me see a simpler implementation was possible.

## Process

In order for KeyCloak to detect, load, and use your module; there are 4 requirements.

1. You *MUST* create a class which implements `org.keycloak.services.resource.RealmResourceProvider`
1. You *MUST* create a class which implements `org.keycloak.services.resource.RealmResourceProviderFactory`
1. You *MUST* create a class which has some JAX-RS annotated methods for handling HTTP requests
1. You *MUST* create a `META-INF/services/org.keycloak.services.resource.RealmResourceProviderFactory` file which references the class implementing `org.keycloak.services.resource.RealmResourceProviderFactory`

## The Simplification

Three of those four requirements can be satisfied with a single class which implements both of the required interfaces as well as the JAX-RS annotated methods. An [example class](src/main/java/com/redhat/consulting/keycloak/rest/example/UsernameChangeRestResource.java) is in this project.

You can then add the fully-qualified package and class name to the `src/main/resources/META-INF/services/org.keycloak.services.resource.RealmResourceProviderFactory` on a single line with nothing else in the file.

Finally, package your custom resource implementation as a JAR file and copy it to the `<keycloak server>/standalone/deployments/` directory and JBoss/KeyCloak will detect and deploy it for you.

It really is that simple!