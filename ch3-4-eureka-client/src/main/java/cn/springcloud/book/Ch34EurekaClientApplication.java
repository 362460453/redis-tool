package cn.springcloud.book;

//import com.redislock.RedisLock;

import com.config.BloomFilterHelper;
import com.google.common.hash.Funnels;
import com.redislock.RedisBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.annotaion", "cn.springcloud", "com.config", "com.redislock"})

public class Ch34EurekaClientApplication implements ApplicationRunner {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static final String uuid = UUID.randomUUID().toString();
    private static BloomFilterHelper<CharSequence> bloomFilterHelper;
    //    @Autowired
//    RedisLock redisLock;
    //    @Autowired
//    RedissonLock redissonLock;
    @Autowired
    RedisBloomFilter redisBloomFilter;

    public static void main(String[] args) {
        SpringApplication.run(Ch34EurekaClientApplication.class, args);

    }

    @PostConstruct
    public void init() {
        bloomFilterHelper = new BloomFilterHelper<>(Funnels.stringFunnel(Charset.defaultCharset()), 1000, 0.1);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//******* Redis单机测试方法*********
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
//                        cyclicBarrier.await();
//                        System.out.println(Thread.currentThread().getName() + "线程就位，即将同时执行");
//                        boolean result = redisLock.tryGetDistributedLock("lock", uuid, 1);
//                        if (result) {
//                            System.out.println(Thread.currentThread().getName() + "获取成功，并开始执行业务逻辑");
//                            result = redisLock.releaseDistributedLock("lock", uuid);
//                            if (result) {
//                                System.out.println(Thread.currentThread().getName() + "释放成功");
//                            }
//                        } else {
//                            System.out.println(Thread.currentThread().getName() + "获取失败");
//                        }
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        }
//        executorService.shutdown();

//******* Redis集群测试方法*********

//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
//                        cyclicBarrier.await();
//                        System.out.println(Thread.currentThread().getName() + "线程就位，即将同时执行");
//                        String key = "test123";
//                        redissonLock.lock(key);
//                        Thread.sleep(1000); //获得锁之后可以进行相应的处理
//                        System.out.println(Thread.currentThread().getName() + "获取成功，并开始执行业务逻辑");
//                        redissonLock.unLock(key);
//                        System.out.println(Thread.currentThread().getName() + "释放成功");
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        }
//        executorService.shutdown();
//        Long result = RedissonManager.nextID();
//        System.out.print("获取redis中的原子ID" + result);


//******* Redis集群测试布隆方法*********
//        int j = 0;
//        for (int i = 0; i < 100; i++) {
//            redisBloomFilter.addByBloomFilter(bloomFilterHelper, "bloom", i+"");
//        }
//        for (int i = 0; i < 1000; i++) {
//            boolean result = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, "bloom", i+"");
//            if (!result) {
//                j++;
//            }
//        }
//        System.out.println("漏掉了" + j + "个");
    }
}
