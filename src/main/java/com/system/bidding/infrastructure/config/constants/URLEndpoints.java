package com.system.bidding.infrastructure.config.constants;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */

public abstract class URLEndpoints {

    private static final String API_VERSION                     = "v1";
    public static final String BASE_API_URL                     = "/api/" + API_VERSION;
    public static final String AUTH_API_URL                     = BASE_API_URL + "/auth";
    public static final String OAUTH2_API_URL                   = BASE_API_URL + "/oauth2";
    public static final String API_SIGN_UP_URL                  = "/sign-up";
    public static final String API_SIGN_IN_URL                  = "/sign-in";
    public static final String API_SIGN_OUT_URL                 = "/sign-out";
    public static final String VIEW_LOGIN_URL                   = "/login";
    public static final String VIEW_LOGOUT_URL                  = "/logout";
    public static final String BASE_VIEW_LOGIN_URL              = URLEndpoints.BASE_VIEW_URL + "/login";
    public static final String AUTH_API_SIGN_UP_URL             = URLEndpoints.AUTH_API_URL+"/sign-up";
    public static final String AUTH_API_SIGN_IN_URL             = URLEndpoints.AUTH_API_URL + "/sign-in";
    public static final String OAUTH2_API_SIGN_UP_URL           = URLEndpoints.OAUTH2_API_URL + "/sign-up";
    public static final String OAUTH2_API_SIGN_IN_URL           = URLEndpoints.OAUTH2_API_URL + "/sign-in";

    public static final String BASE_VIEW_URL                    = "/view/" + API_VERSION;
    public static final String DASHBOARD_URL                    = "/dashboard";
    public static final String BASE_API_USER_URL                = BASE_API_URL + "/user";
    public static final String BASE_API_ADMIN_URL               = BASE_API_URL + "/admin";

    public static final String BASE_USER_VIEW_URL               = "/view/" + API_VERSION + "/user";
    public static final String BASE_ADMIN_VIEW_URL              = "/view/" + API_VERSION + "/admin";
    public static final String BASE_ITEM_URL                    = "/items";

    public static final String BIDDING_URL                      = "/bidding";

    public static final String BASE_BIDDING_VIEW_URL            = "/view/" + API_VERSION + "/bidding";
    public static final String BIDDING_REQUEST_VIEW_URL         = "/request";
    public static final String BIDDING_POST_VIEW_URL            = "/bid";
    public static final String BIDDING_ANNOUNCEMENT_URL         = "/announcement";

    public static final String[] EXCLUDE_URI = {
            "/configuration/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger*/**",
            "/api/auth/**",
            "/webjars/**",
            "/css/**",
            "/img/**"
    };
}
