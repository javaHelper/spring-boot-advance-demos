package com.pluralsight.springdata;

import com.pluralsight.springdata.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomQueriesTest extends SpringdataApplicationTests {

    @Test
    void testNumberOfUsersByActivity() {
        int active = userRepository.findNumberOfUsersByActivity(true);
        int inactive = userRepository.findNumberOfUsersByActivity(false);

        assertAll(
                () -> assertEquals(8, active),
                () -> assertEquals(2, inactive)
        );
    }

    @Test
    void testUsersLevelAndActivity() {
        List<User> userList1 = userRepository.findUsersLevelAndActivity(1, true);
        List<User> userList2 = userRepository.findUsersLevelAndActivity(2, true);
        List<User> userList3 = userRepository.findUsersLevelAndActivity(2, false);

        assertAll(
                () -> assertEquals(2, userList1.size()),
                () -> assertEquals(2, userList2.size()),
                () -> assertEquals(1, userList3.size())
        );
    }

    @Test
    void testNumberOfUsersByActivityNative() {
        int active = userRepository.findNumberOfUsersByActivityNative(true);
        int inactive = userRepository.findNumberOfUsersByActivityNative(false);

        assertAll(
                () -> assertEquals(8, active),
                () -> assertEquals(2, inactive)
        );
    }

    @Test
    void testFindByAsArrayAndSort() {
        List<Object[]> usersList1 = userRepository.findByAsArrayAndSort("ar", Sort.by("username"));
        List<Object[]> usersList2 = userRepository.findByAsArrayAndSort("ar", Sort.by("email_length").descending());
        List<Object[]> usersList3 = userRepository.findByAsArrayAndSort("ar", JpaSort.unsafe("LENGTH(u.email)"));
        assertAll(
                () -> assertEquals(2, usersList1.size()),
                () -> assertEquals("darren", usersList1.get(0)[0]),
                () -> assertEquals(21, usersList1.get(0)[1]),
                () -> assertEquals(2, usersList2.size()),
                () -> assertEquals("marion", usersList2.get(0)[0]),
                () -> assertEquals(26, usersList2.get(0)[1]),
                () -> assertEquals(2, usersList3.size()),
                () -> assertEquals("darren", usersList3.get(0)[0]),
                () -> assertEquals(21, usersList3.get(0)[1])
        );
    }
}
