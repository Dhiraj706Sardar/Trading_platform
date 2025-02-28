package com.dtrade.trading.repository;

import com.dtrade.trading.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository <UserEntity, Long> {
}
