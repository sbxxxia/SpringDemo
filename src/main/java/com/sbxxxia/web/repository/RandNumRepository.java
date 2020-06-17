package com.sbxxxia.web.repository;

import com.sbxxxia.web.entity.RandNumMessage;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandNumRepository {

    public RandNumMessage getRandom(){

        return new RandNumMessage();
    }
}
