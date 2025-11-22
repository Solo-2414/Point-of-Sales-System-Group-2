package helpers;

import model.user;

public class session {
	 private static user currentUser = null;

	    public static void setUser(user user) {
	        currentUser = user;
	    }

	    public static user getUser() {
	        return currentUser;
	    }

	    public static boolean isLoggedIn() {
	        return currentUser != null;
	    }

	    public static void logout() {
	        currentUser = null;
	    }
}
