package com.latihan.java.springdasar.service.async;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserAsyncServiceImpl implements UserAsyncService {


    @Override
    public List<String> getUsers() {

        ExecutorService userThread = Executors.newSingleThreadExecutor();


        return List.of();
    }
}
