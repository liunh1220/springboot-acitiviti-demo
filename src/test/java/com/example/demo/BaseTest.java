package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by liunanhua on 2018/3/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootAcitivitiDemoApplication.class)
@ActiveProfiles(profiles = "dev")
public class BaseTest {



}
