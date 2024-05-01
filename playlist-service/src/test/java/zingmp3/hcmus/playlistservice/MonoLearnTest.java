package zingmp3.hcmus.playlistservice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

//@SpringBootTest
class MonoLearnTest {

	@Test
	void workingWithMono() throws InterruptedException {

		// Mono --> publisher that have 0...1 item
		// create mono
//		Mono<String> errorMono = Mono.error(new Exception("error"));
//
//		Mono<String> m1 = Mono
//				.just("Learn Code with me")
//				.log()
//				.then(errorMono);
//
////		consume the mono by subscribing
//		m1.subscribe(System.out::println);
//		errorMono.subscribe(System.out::println);

		Mono<String> m1 = Mono.just("Lean code with me");
		Mono<String> m2 = Mono.just("Learn Reactor");
		Mono<Integer> m3 = Mono.just(2123145);

//		Mono<String> resultMapMono = m1.map(String::toUpperCase);
//		resultMapMono.subscribe(System.out::println);
//
//		Mono<String[]> resultFlatExample = m1.flatMap(valueM1 -> Mono.just(valueM1.split(" ")));
//		resultFlatExample.subscribe(data -> {
//			for (String s : data) {
//				System.out.println(s);
//			}
//		});
//		System.out.println("=====================================");
//
//		Flux<String> stringFlux = m1.flatMapMany(valueM1 -> Flux.just(valueM1.split(" "))).log();
//		stringFlux.subscribe(System.out::println);

//
//		Mono<Tuple3<String, String, Integer>> compiledMono = Mono.zip(m1, m2, m3);
//
//		compiledMono.subscribe(data -> {
//			System.out.println(data.getT1());
//			System.out.println(data.getT2());
//			System.out.println(data.getT3());
//		});
//
//		Mono<Tuple2<String, String>> zipWithMono = m1.zipWith(m2);
//		zipWithMono.subscribe(data -> {
//			System.out.println(data.getT1());
//			System.out.println(data.getT2());
//		});

//		System.out.println(Thread.currentThread().getName());
//		Flux<String> stringFlux = m1.concatWith(m2)
//				.log()
//				.delayElements(Duration.ofMillis(2000));
//		stringFlux.subscribe((data) -> {
//			System.out.println(Thread.currentThread().getName());
//			System.out.println(data);
//		});
//
//		Thread.sleep(4000);
//		System.out.println("terminated main thread");

		m1.delayElement(Duration.ofSeconds(2));
		m1.subscribe(data -> {
			System.out.println(data);
			System.out.println(Thread.currentThread().getName());
		});

		Thread.sleep(3000);

	}

}
