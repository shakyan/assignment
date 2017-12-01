package com.nagesh.api_assignment.service;

import com.nagesh.api_assignment.exception.StorageException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

public class FileSystemStorageServiceTest {

    private StorageProperties properties = new StorageProperties();
    private FileSystemStorageService service;

    @Before
    public void init() {
        properties.setLocation("target/files/test.txt");
        service = new FileSystemStorageService(properties);
        service.init();
    }

    @Test(expected = StorageException.class)
    public void saveNotPermitted() {
        service.store(new MockMultipartFile("test", "/test.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
    }

    @Test
    public void savePermitted() {
        service.store(new MockMultipartFile("test", "test.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
    }
}
