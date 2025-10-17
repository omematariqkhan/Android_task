package com.example.logintask;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth {

    // --- Hard-coded credentials (you can change these) ---
    public static final String DEFAULT_EMAIL = "test@example.com";
    public static final String DEFAULT_PASSWORD = "password123";

    // --- Simple local storage for registered / reset users ---
    private static final String PREFS = "auth_prefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private static SharedPreferences sp(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    /** ✅ Check login credentials */
    public static boolean isValid(Context ctx, String email, String pass) {
        String savedEmail = sp(ctx).getString(KEY_EMAIL, null);
        String savedPass = sp(ctx).getString(KEY_PASSWORD, null);

        if (savedEmail != null && savedPass != null) {
            return savedEmail.equals(email) && savedPass.equals(pass);
        }
        return DEFAULT_EMAIL.equals(email) && DEFAULT_PASSWORD.equals(pass);
    }

    /** ✅ Save user (used by Register and Reset) */
    public static void saveUser(Context ctx, String email, String pass) {
        sp(ctx).edit()
                .putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, pass)
                .apply();
    }

    /** ✅ Check if an email already exists */
    public static boolean emailExists(Context ctx, String email) {
        String savedEmail = sp(ctx).getString(KEY_EMAIL, null);
        return email.equals(DEFAULT_EMAIL) || (savedEmail != null && email.equals(savedEmail));
    }

    /** ✅ Reset password for an existing email */
    public static void resetPassword(Context ctx, String email, String newPass) {
        if (email.equals(DEFAULT_EMAIL)) {
            // override the default account with a new password
            saveUser(ctx, email, newPass);
        } else {
            String savedEmail = sp(ctx).getString(KEY_EMAIL, null);
            if (savedEmail != null && email.equals(savedEmail)) {
                sp(ctx).edit().putString(KEY_PASSWORD, newPass).apply();
            }
        }
    }

    /** (Optional) clear saved data — useful for testing */
    public static void clearStoredUser(Context ctx) {
        sp(ctx).edit().clear().apply();
    }
}
