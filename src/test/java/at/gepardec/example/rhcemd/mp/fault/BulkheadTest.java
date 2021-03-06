package at.gepardec.example.rhcemd.mp.fault;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

class BulkheadTest {

    private static ExecutorService executorService;

    @BeforeAll
    static void staticInit() {
        System.setProperty("baseURI", "http://localhost:8080/rhcemd/api");
        executorService = new ThreadPoolExecutor(20, 20, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Test
    void semaphore_isolation_with_all_successful() throws Exception {
        List<HttpClientCallable> callables = HttpClientCallable.repeatedForPath("/bulkhead/semaphore", 5);

        List<Future<String>> futures = executorService.invokeAll(callables);
        int errorCount = 0;

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                errorCount++;
            }
        }

        Assertions.assertEquals(0, errorCount);
    }

    @Test
    void semaphore_isolation_with_5_failed() throws Exception {
        List<HttpClientCallable> callables = HttpClientCallable.repeatedForPath("/bulkhead/semaphore", 10);

        List<Future<String>> futures = executorService.invokeAll(callables);
        int errorCount = 0;

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                errorCount++;
            }
        }

        Assertions.assertEquals(5, errorCount);
    }

    /**
     * 5 are working, 5 are waiting equals 10 parallel requests
     */
    @Test
    void threadpool_isolation_with_all_successful() throws Exception {
        List<HttpClientCallable> callables = HttpClientCallable.repeatedForPath("/bulkhead/threadpool", 10);

        List<Future<String>> futures = executorService.invokeAll(callables);
        int errorCount = 0;

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                errorCount++;
            }
        }

        Assertions.assertEquals(0, errorCount);
    }

    /**
     * 5 are working, 10 are waiting and 5 fail equals 20 parallel requests
     */
    @Test
    void threadpool_isolation_with_5_failed() throws Exception {
        List<HttpClientCallable> callables = HttpClientCallable.repeatedForPath("/bulkhead/threadpool", 20);

        List<Future<String>> futures = executorService.invokeAll(callables);
        int errorCount = 0;

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                errorCount++;
            }
        }

        Assertions.assertEquals(5, errorCount);
    }
}
