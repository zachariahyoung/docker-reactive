package com.zandroid.controller;

import com.zandroid.model.Volume;
import com.zandroid.repository.VolumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("/volumes")
@Slf4j
public class VolumeController {

    private final VolumeRepository volumeRepository;

    public VolumeController(VolumeRepository volumeRepository) {
        this.volumeRepository = volumeRepository;
    }

    @GetMapping
    public Flux<Volume> all() {
        return this.volumeRepository.findAll();
    }

    @GetMapping(value = "/volume", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Volume> getAllPersons() {
        Flux<Volume> eventFlux = Flux.fromStream(Stream.generate(() ->
                volumeRepository.findByName("AEon Flux").block()));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);
    }

    @PostMapping
    public Mono<Void> create(@RequestBody Publisher<Volume> volumeStream) {


        return this.volumeRepository.save(volumeStream).then().log();
    }
}
