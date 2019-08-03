package itg.ch6.data;

import itg.tools.b.Seriable;

public class User {

    @Seriable
    private String username;
    @Seriable
    private String password;

    private String three;
    private String four;
}
