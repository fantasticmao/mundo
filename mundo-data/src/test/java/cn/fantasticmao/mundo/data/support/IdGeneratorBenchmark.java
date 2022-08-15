package cn.fantasticmao.mundo.data.support;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * IdGeneratorBenchmark
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-15
 */
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 5)
@Threads(4)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class IdGeneratorBenchmark {
    final IdGenerator idGenerator = IdGenerator.Snowflake.newInstance(777);

    @Benchmark
    public void next() {
        idGenerator.next();
    }

    @Disabled
    @Test
    public void runBenchmark() throws RunnerException {
        Options options = new OptionsBuilder()
            .include(IdGeneratorBenchmark.class.getSimpleName())
            .forks(1)
            .build();
        new Runner(options).run();
    }
}
