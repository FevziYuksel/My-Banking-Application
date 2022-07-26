//package org.banking.mybankingapplication.repository;
//
//
//import org.banking.mybankingapplication.model.entity.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface RoleRepository extends JpaRepository<Role, Integer> {
//
//    boolean existsByName(final String name);
//
//    Optional<Role> findByName(final String name);
//}