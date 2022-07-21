package org.banking.mybankingapplication.repository;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import org.banking.mybankingapplication.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
