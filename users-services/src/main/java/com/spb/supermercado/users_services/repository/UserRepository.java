package com.spb.supermercado.users_services.repository;

import com.spb.supermercado.users_services.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNomeAndSenha(String nome, String senha);
    User findByNome(String nome);

}
