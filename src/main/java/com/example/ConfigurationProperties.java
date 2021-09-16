package com.example;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "minio")
public interface ConfigurationProperties {
    String endpoint();
    String username();
    String password();
}