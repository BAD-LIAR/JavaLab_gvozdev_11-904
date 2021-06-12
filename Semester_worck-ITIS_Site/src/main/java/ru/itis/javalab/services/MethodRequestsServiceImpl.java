package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.MethodsRequests;
import ru.itis.javalab.repositories.MethodRequestsRepository;

@Service
public class MethodRequestsServiceImpl implements MethodRequestsService {

    @Autowired
    MethodRequestsRepository methodRequestsRepository;

    @Override
    public void addCount(String method, String url) {
        MethodsRequests methodsRequests = methodRequestsRepository.findByMethodAndAndClassName(method, url);
        if (methodsRequests == null) {
            methodsRequests = MethodsRequests.builder()
                    .count(0)
                    .method(method)
                    .className(url)
                    .build();
        }
        methodsRequests.setCount(methodsRequests.getCount() + 1);
        methodRequestsRepository.save(methodsRequests);

    }
}
