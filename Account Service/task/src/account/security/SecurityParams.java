package account.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collections;
import java.util.List;

public class SecurityParams {

    public static final List<GrantedAuthority> ROLE_USER = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("USER"));

    public static final List<GrantedAuthority> ROLE_ANONIMOUS = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("ANONIMOUS"));

    public static final List<GrantedAuthority> ROLE_MERCHANT = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("MERCHANT"));

    public static final List<GrantedAuthority> ROLE_ADMINISTRATOR = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("ADMINISTRATOR"));

    public static final List<GrantedAuthority> ROLE_SUPPORT = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("SUPPORT"));

}
