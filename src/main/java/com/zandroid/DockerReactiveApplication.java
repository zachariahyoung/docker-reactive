package com.zandroid;

import com.zandroid.model.Volume;
import com.zandroid.repository.VolumeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DockerReactiveApplication {

	@Bean
	CommandLineRunner demoData(VolumeRepository volumeRepository) {
		return strings -> {
			volumeRepository.deleteAll().block();
			Stream.of("AEon Flux", "Flux Gordon")
					.map(title -> new Volume())
					.forEach(movie -> volumeRepository.save(movie).subscribe(System.out::println));
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(DockerReactiveApplication.class, args);
	}
}
