package com.test.auditService.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.handler.file.FileAuditHandler;
import org.audit4j.core.layout.Layout;
import org.audit4j.core.layout.SimpleLayout;
import org.audit4j.handler.db.DatabaseAuditHandler;
import org.audit4j.integration.spring.AuditAspect;
import org.audit4j.integration.spring.SpringAudit4jConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class Audit4jConfiguration {

    private final Audit4jMetaData audit4jMetaData;

    private final Environment env;

    @Value("${audit.path}")
    private String auditPath;

    @Value("${audit.file}")
    private boolean auditFile;

    @Bean
    public Layout layout() {
        return new SimpleLayout();
    }

    @Bean
    public DatabaseAuditHandler databaseHandler() {
        DatabaseAuditHandler databaseHandler = new DatabaseAuditHandler();
        databaseHandler.setEmbedded("false");
        databaseHandler.setDb_user(env.getRequiredProperty("spring.datasource.username"));
        databaseHandler.setDb_password(env.getRequiredProperty("spring.datasource.password"));
        databaseHandler.setDb_url(env.getRequiredProperty("spring.datasource.url"));
        databaseHandler.setDb_driver(env.getRequiredProperty("spring.datasource.driver-class-name"));
        return databaseHandler;
    }

    @Bean
    public FileAuditHandler fileAuditHandler() {
        return new FileAuditHandler();

    }

    @Bean
    public ConsoleAuditHandler consoleAuditHandler() {
        return new ConsoleAuditHandler();
    }

    public List<Handler> handlers() {
        List<Handler> handlers = new ArrayList<>();

        if (auditFile) {
            handlers.add(fileAuditHandler());
        }

        handlers.add(databaseHandler());
        return handlers;
    }

    public Map<String, String> props() {
        Map<String, String> props = new HashMap<>();
        if (auditFile) {
            props.put("log.file.location", auditPath);
        }

        return props;
    }

    @Bean
    public SpringAudit4jConfig springAudit4jConfig(Layout layout) {
        SpringAudit4jConfig audit4jConfig = new SpringAudit4jConfig();

        audit4jConfig.setHandlers(handlers());
        audit4jConfig.setLayout(layout);
        audit4jConfig.setMetaData(audit4jMetaData);
        audit4jConfig.setProperties(props());
        return audit4jConfig;

    }

    @Bean
    public AuditAspect auditAspect() {
        return new AuditAspect();
    }
}
