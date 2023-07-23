package com.pluralsight.springdata;

import com.pluralsight.springdata.model.User;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamableTest extends SpringdataApplicationTests {

    @Test
    void testStreamable() {
       try(Stream<User> result = userRepository.findByEmailContaining("someother")
               .and(userRepository.findByLevel(2)).stream().distinct()) {
           assertEquals(7, result.count());
       }
    }
}
