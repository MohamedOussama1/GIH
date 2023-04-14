package ma.uiass.eia.pds.controller;//package ma.uiass.eia.pds.controller;



import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import java.io.IOException;

//@Provider

public class SecurityFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

    }
//
//    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
//    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
//    private static final String REALM = "My Main Realm";
//    private static final String USERS_FILE = "C:\\Users\\Fujitsu LifeBook u\\Desktop\\pds\\Send\\GIH\\src\\main\\resources\\realm.properties"; // Change this to the path of your realm.properties file
//
//    @Context
//    private ResourceInfo resourceInfo;
//
//
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        // Get the Authorization header from the request
//        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER_KEY);
//
//        RolesAllowed rolesAnnotation = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
////        String[] roles = rolesAnnotation.value();
////        System.out.println(roles);
//
//        // Check if the header is present and starts with "Basic "
//        if (authHeader != null && authHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
//            // Get the Base64-encoded username and password from the header
//            String base64Credentials = authHeader.substring(AUTHORIZATION_HEADER_PREFIX.length()).trim();
//            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
//
//            // Split the username and password
//            final String[] values = credentials.split(":", 2);
//
//
//
//            // Authenticate the user
//            boolean[] test=isValidUser(values[0], values[1]);
//            if (test[0]) {
//                // If authentication succeeds, check if the user has the required role to access the resource
//
//                if(rolesAnnotation==null){
//                    return;
//                }
//
//                if (test[1]) {
//                    // If the user has the required role, allow the request to proceed
//                    return;
//                } else {
//                    // If the user doesn't have the required role, return a 403 Forbidden response
//                    Response forbidden = Response.status(Response.Status.FORBIDDEN).build();
//                    requestContext.abortWith(forbidden);
//                }
//                return;
//            }
//
//
//            }
//
//            // If authentication fails, return a 401 Unauthorized response
//            Response unauthorized = Response.status(Response.Status.UNAUTHORIZED)
//                    .header(HttpHeaders.WWW_AUTHENTICATE, AUTHORIZATION_HEADER_PREFIX)
//                    .build();
//            requestContext.abortWith(unauthorized);
//        }
//
//
//
//        private boolean[] isValidUser(String username, String password) throws IOException {
//        System.out.println("username: " + username + " password: " + password);
//
//        boolean[] test=new boolean[2];
//        test[0]=false;
//        test[1]=false;
//
//        // Read the realm.properties file
//        String content = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
//        System.out.println("realm.properties contents: " + content);
//
//        // Split the content into lines
//        String[] lines = content.split("\\r?\\n");
//
//        // Search for a matching user and password
//            RolesAllowed rolesAnnotation = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
//
//                for (String line : lines) {
//                    String[] tokens = line.split(",");
//                    System.out.println("realm.properties username: " + tokens[0] + " password: " + tokens[1]);
//                    if (username.equals(tokens[0]) && password.equals(tokens[1])) {
//
//
//                        String[] token2 = tokens[2].split("/");
//                        if(rolesAnnotation !=null) {
//
//                            String[] roles = rolesAnnotation.value();
//                            for (String str : token2) {
//                               boolean boll=false;
//                                for(String r:roles) {
//                                    if (str.contains(r)) {
//                                        test[1] = true;
//                                        boll=true;
//                                        break;
//                                    }
//                                }
//                                if(boll){break;}
//
//
//
//
//                            }
//                        }
//
//
//                        test[0] = true;
//                        break;
//                    }
//
//
//                }
//
//
//        // If no matching user and password are found, return false
//
//        return test;
//    }
//
//
}








