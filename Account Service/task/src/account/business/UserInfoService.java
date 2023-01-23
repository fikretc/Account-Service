package account.business;

import account.entity.UserInfo;
import account.persistence.UserInfoRepository;
import account.security.SecurityParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserInfo loadUserByEmail(String email) {
        return userInfoRepository.findUserInfoByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findUserInfoByEmail(username.toLowerCase());
        if (userInfo == null) {
            throw new UsernameNotFoundException(username);
        }
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), SecurityParams.ROLE_USER);
        return user; //new MyUserPrincipal(user);
    }


    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public UserInfo saveFirstTime(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return save(userInfo);
    }
}
