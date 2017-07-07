package dev.sol.catalog.jaxresponses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author solo on 6/16/2017.
 */
public class AccessTokenData {

    /***
     *
     {
     "access_token": "ya29.GltsBMDi584uTGpQW8DfxKR6uZjgd1seea1C-20HnARSexUKWKEwgYHl2KGHJpkMjX0WCAkysVp2dYrPph790Rb04_B2-lLH7DjtViOkRF98j_eArIyGoRTUWnX0",
     "token_type": "Bearer",
     "expires_in": 3600,
     "refresh_token": "1/j2nfE1uMhGX7O6wZCPHgDeSUKMjn2358hWhX-VZAooU",
     "id_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjUyM2Y1OWMyOTY3ZGQyZTI4Mjk3YTU3NGM0ZmEwZjRiZTdlNDdlMDYifQ.eyJhenAiOiI5MzY3MjIxNTM4NTItN29mZHIyZjg0aXY3dWF0YjI2dnNsaTk1aHVkajJjYWouYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI5MzY3MjIxNTM4NTItN29mZHIyZjg0aXY3dWF0YjI2dnNsaTk1aHVkajJjYWouYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQyNzg3MzgyNzYyMzUzMDU2MDIiLCJhdF9oYXNoIjoiNEhZMXkwWGVHQmhVMFlwMVlJS29uZyIsImlzcyI6Imh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbSIsImlhdCI6MTQ5NzcyODE3MCwiZXhwIjoxNDk3NzMxNzcwLCJuYW1lIjoiU29sb21vbiBMYXdyZW5jZSIsInBpY3R1cmUiOiJodHRwczovL2xoNC5nb29nbGV1c2VyY29udGVudC5jb20vLWo1LWRKazZQRThJL0FBQUFBQUFBQUFJL0FBQUFBQUFBQUFBL0FBeVlCRjRmZUZYSXNtRmRQc1ZhbURZdzlkNVlCbVFSSXcvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6IlNvbG9tb24iLCJmYW1pbHlfbmFtZSI6Ikxhd3JlbmNlIiwibG9jYWxlIjoiZW4ifQ.c36jBEBcmE5FURPpGjr_Dh6F7ayueZoOCJPQdffbCHNKjzE57O3sg-E-mOWfPLtALY1qi2SvTwShuR6jXrLUS5Ly7KfB1QlcGYD9GxDW4QeqsJ0ol_19TPtfArW5lK2Uxu6bj8tqBTnwIXJUSrGYyXuh4aBlSS3N4I0LSlDE8Joa_z197m8DxdFDBuUkuvA77XBUBp1pIUDlRUWWLnRJeGzEHaDBxwtrTjeooyOocHiCZnkwbV51a49-BqHyk0LqQOVZpoOW1lY__8YphfR4ARimoxXzTcyDDEEswPs2-t0ZMucciZ98wo_dfIJRmG5aXBWhnKQNJG5D6zAG0VHOyQ"
     }


    {
        "issued_to": "936722153852-7ofdr2f84iv7uatb26vsli95hudj2caj.apps.googleusercontent.com",
            "audience": "936722153852-7ofdr2f84iv7uatb26vsli95hudj2caj.apps.googleusercontent.com",
            "user_id": "104278738276235305602",
            "scope": "https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/userinfo.profile",
            "expires_in": 3483,
            "access_type": "offline"
    }
     *
     */

    @JsonProperty("issued_to")
    private String issuedTo;

    @JsonProperty
    private String audience;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty
    private String scope;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("access_type")
    private String accessType;

    public AccessTokenData(){

    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
