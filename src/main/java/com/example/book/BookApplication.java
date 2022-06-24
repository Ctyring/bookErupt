package com.example.book;

import com.example.book.proxy.MinioProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import xyz.erupt.core.annotation.EruptAttachmentUpload;
import xyz.erupt.core.annotation.EruptScan;

@EruptAttachmentUpload(MinioProxy.class)
@SpringBootApplication
@EntityScan
@EruptScan
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
