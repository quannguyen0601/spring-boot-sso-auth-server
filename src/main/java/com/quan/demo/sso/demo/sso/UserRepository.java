package com.quan.demo.sso.demo.sso;

import com.quan.demo.sso.demo.sso.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users, String> {
}
