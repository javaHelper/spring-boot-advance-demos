package com.pluralsight.springdata.model;

import org.springframework.beans.factory.annotation.Value;

public class Projection {

    public interface UserSummary {
        String getUsername();

        @Value("#{target.username} #{target.email}")
        String getInfo();
    }

    public static class UsernameOnly {
        private String username;

        public UsernameOnly(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}
