package com.zandroid.repository;

import com.zandroid.model.Volume;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Created by zpyou on 4/22/2017.
 */
public interface VolumeRepository extends ReactiveMongoRepository<Volume, String> {

    Mono<Volume> findByName(String string);
}
