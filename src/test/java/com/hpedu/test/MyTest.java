package com.hpedu.test;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MyTest {

    @Test
    public void main() {
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        System.out.println("now= " + LocalDateTime.ofEpochSecond(1558011443, 0, ZoneOffset.UTC));
    }


}
