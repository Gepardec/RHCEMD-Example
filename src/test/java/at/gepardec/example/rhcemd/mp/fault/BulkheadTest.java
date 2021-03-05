package at.gepardec.example.rhcemd.mp.fault;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

class BulkheadTest {

    private static ExecutorService executorService;

    @BeforeAll
    static void staticInit() {
        executorService = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Test
    void semaphore_isolation() throws Exception {
        List<HttpClientCallable> callables = HttpClientCallable.repeatedForPath("/bulkhead/semaphore", 10);

        List<Future<String>> futures = executorService.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }
}
