package test.func;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import tools.HttpIOUtils;
import tools.pool.GlobalThreadPool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput})
@State(value = Scope.Thread)
@Timeout(time = 2,timeUnit = TimeUnit.MINUTES)
@Warmup(iterations = 3, time = 30,timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1,timeUnit = TimeUnit.MINUTES)
@OutputTimeUnit(TimeUnit.SECONDS)
@Threads(8)
public class FileNIO {
    private static final String dir = "C:\\Users\\86134\\Desktop\\book";



    public static void main(String[] args) throws RunnerException {
        System.gc();
        Options opt = new OptionsBuilder()
                .include(FileNIO.class.getSimpleName())
                .result("result.json")
                .threads(8)
                .forks(3)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
        System.gc();

    }
    
    

    @Benchmark
    public static void directAlloc(){
        File in = new File(dir + "\\产研后期工作节奏.jpg");
        InputStream is = HttpIOUtils.getStreamFromFile(in);
        File f = HttpIOUtils.downloadInputStreamVer2(is,"jpg");
        if(null != f && f.exists()) {
            f.delete();
        }
    }


    @Benchmark
    public static void alloc(Blackhole blackhole){
        File in = new File(dir + "\\\\产研后期工作节奏.jpg");
        InputStream is = HttpIOUtils.getStreamFromFile(in);
        File f = HttpIOUtils.downloadInputStreamVer3(is,".jpg");
        if(null != f && f.exists()) {
            f.delete();
        }
    }

    @Benchmark
    public static void stream(Blackhole blackhole){
        File in = new File(dir + "\\产研后期工作节奏.jpg");
        InputStream is = HttpIOUtils.getStreamFromFile(in);
        File f = HttpIOUtils.downloadInputStream(is,"jpg");
        if(null != f && f.exists()) {
            f.delete();
        }
    }

    @Benchmark
    public static void asyncChannel(Blackhole blackhole) throws IOException {
        Path path = Paths.get(dir + "\\产研后期工作节奏.jpg");
        AsynchronousFileChannel fileChannel =AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        File f = HttpIOUtils.downloadInputStreamVer5(fileChannel,"jpg");
        if(null != f && f.exists()){
            f.delete();
        }

    }


/*//    public static void testNIOFileTranslate(){
//        StringBuffer[] sb = new StringBuffer[];
//    }*/
}
