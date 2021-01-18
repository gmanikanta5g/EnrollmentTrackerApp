package com.health.enroll.enrollmenttracker.config;
/**
 * Following method provides decrypt function for the string which are encrypted using Jasypt, 
 * in this case for example the DB Password from application.properties file.
 */
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig  {
  @Bean
  @ConfigurationProperties(prefix = "jasypt.encryptor")
  public SimpleStringPBEConfig jasypConfig() {
      SimpleStringPBEConfig config = new SimpleStringPBEConfig();
      return config;
  }

  @Bean("jasyptEncryptor")
  public StringEncryptor jasyptEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
      encryptor.setConfig(jasypConfig());
      return encryptor;   
    }
  
  

}
