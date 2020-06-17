package com.sbxxxia.web.repository;

import com.sbxxxia.web.entity.RandNumMessage;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandNumRepository {
    static Random rand = new Random();

    public RandNumMessage getRandom(){
        return new RandNumMessage();
    }
}
