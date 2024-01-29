package com.latihan.java.spring.apache.ignite.config;

import com.latihan.java.spring.apache.ignite.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class IgniteConfig {

    @Value("${ignite.address}")
    private String igniteAddress;

    @Bean(name = "igniteInstance")
    public Ignite igniteInstance(Ignite ignite) {
        return ignite;
    }

    @Bean
    public IgniteConfigurer configurer() {
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList(igniteAddress));

        CacheConfiguration<Integer, Employee> cache = new CacheConfiguration<>("employeeCache");
        cache.setIndexedTypes(Integer.class, Employee.class);

        return igniteConfiguration -> {
            igniteConfiguration.setCacheConfiguration(cache);
            igniteConfiguration.setClientMode(true);
            igniteConfiguration.setPeerClassLoadingEnabled(true);
            igniteConfiguration.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));
        };
    }

}
