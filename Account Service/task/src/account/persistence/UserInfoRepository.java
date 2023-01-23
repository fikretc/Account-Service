package account.persistence;


import account.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    UserInfo findUserInfoByUserId(Long userId);

    UserInfo findUserInfoByEmail(String username);

    List<UserInfo> findAll();
    UserInfo save(UserInfo toSave);

}


