package com.example.projektportfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronSimpleTask {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProjektportfolioRepository projektportfolioRepository; //dodając to mamy dostep do bazy danych w ponizszej metodzie

    @Scheduled(fixedRate = 1000*60) //musimy dodać w klasie startującej aplikacje adnotacje @EnableScheduling
    public void printSth(){
        log.info("OK");
    }

    // s m g d m d - ostatnie d to dzien tygodnia, pierwsze d to dzien miesiąca
    // * - co każdą
    // / - co iles
    // */10 * * * * - co 10 sekund
    // 0 0 20,21,22 * * - o godzinie 20, 21 i 22
    // 0 */30 20,21,22 * * - o 20; 20:30; 21 itd
    // 0 0 17-21 * * MON-FRI - o pelnej godz miedzy 17-21 od pon do pt
    // 0 0 0 24 12
    @Scheduled(cron = "0 0 * * * ?") //co pelna godzine
    public void printSth2(){
        log.info("OK");
    }
}
