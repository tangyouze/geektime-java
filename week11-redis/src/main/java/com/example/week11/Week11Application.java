package com.example.week11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sound.midi.Soundbank;
import java.time.Duration;

@SpringBootApplication
public class Week11Application implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(Week11Application.class, args);
    }

    @Autowired
    private RedisTemplate<String, String> template;


    @Override
    public void run(String... args) throws Exception {
        Runnable myRunnable = new Runnable() {
            public void run() {
                incrementWithoutLock();
            }
        };

        Runnable myRunnableLock = new Runnable() {
            public void run() {
                incrementWithLock();
            }
        };
        // 先测试单线程 是正常的
        template.opsForValue().set("increment", "0");
        incrementWithoutLock();

        template.opsForValue().set("increment", "0");
        new Thread(myRunnable).start();
        new Thread(myRunnable).start();

        Thread.sleep(3 * 1000);
        System.out.println("try to lock");

        template.opsForValue().set("increment", "0");
        new Thread(myRunnableLock).start();
        new Thread(myRunnableLock).start();

        Thread.sleep(3 * 1000);


        Runnable removeStorage = new Runnable() {
            public void run() {
                removeStorageLock();
            }
        };
        template.opsForValue().set("storage", "100");
        new Thread(removeStorage).start();
        new Thread(removeStorage).start();

        Thread.sleep(3 * 1000);
    }

    public void incrementWithoutLock() {
        System.out.println("incrementWithoutLock");
        for (int i = 0; i < 10000; i++) {
            String p = template.opsForValue().get("increment");
            int v = Integer.parseInt(p);
            v += 1;
            template.opsForValue().set("increment", String.valueOf(v));
        }
        String increment = template.opsForValue().get("increment");
        System.out.println(increment);
    }

    public void incrementWithLock() {
        System.out.println("increment with lock");
        for (int i = 0; i < 10000; ) {
            Boolean t = template.opsForValue().setIfAbsent("key", "value", Duration.ofMinutes(1));
            if (Boolean.TRUE.equals(t)) {
                i++;
                String p = template.opsForValue().get("increment");
                int v = Integer.parseInt(p);
                v += 1;
                template.opsForValue().set("increment", String.valueOf(v));
                template.delete("key");
            }
        }
        String increment = template.opsForValue().get("increment");
        System.out.println(increment);

    }
    public void removeStorageLock() {
        System.out.println("remove with lock");
        for (int i = 0; i < 10000; ) {
            Boolean t = template.opsForValue().setIfAbsent("key", "value", Duration.ofMinutes(1));
            if (Boolean.TRUE.equals(t)) {
                String p = template.opsForValue().get("storage");
                int v = Integer.parseInt(p);
                v -= 1;
                template.opsForValue().set("storage", String.valueOf(v));
                template.delete("key");
            }
        }
        String increment = template.opsForValue().get("storage");
        System.out.println(increment);

    }

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }
}
