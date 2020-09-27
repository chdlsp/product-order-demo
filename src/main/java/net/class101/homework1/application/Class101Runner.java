package net.class101.homework1.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Class101Runner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.info("==========================");
        log.info("Class101Runner Started ...");
        log.info("==========================");
    }
}
