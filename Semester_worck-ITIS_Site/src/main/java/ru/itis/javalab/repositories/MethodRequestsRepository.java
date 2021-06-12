package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.models.MethodsRequests;

public interface MethodRequestsRepository extends JpaRepository<MethodsRequests, Long> {

//    @Query("select MethodsRequests from MethodsRequests where method = :method and className = :className")
//    MethodsRequests findByMethodAndUrl(@Param("method") String method, @Param("className") String className);

    MethodsRequests findByMethodAndAndClassName(String method, String className);
}
