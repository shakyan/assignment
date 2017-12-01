package com.nagesh.api_assignment.controller;

import com.nagesh.api_assignment.exception.StorageFileNotFoundException;
import com.nagesh.api_assignment.model.MetaData;
import com.nagesh.api_assignment.service.MetaDataService;
import com.nagesh.api_assignment.service.StorageProperties;
import com.nagesh.api_assignment.service.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private MetaDataService metaDataService;

    @MockBean
    private StorageProperties storageProperties;


    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());
        given(this.storageProperties.getLocation()).willReturn("target");
        this.mvc.perform(fileUpload("/api/v1/files/upload").file(multipartFile))
                .andExpect(status().isOk());

        then(this.storageService).should().store(multipartFile);
        then(this.metaDataService).should().saveMetaData(any(MetaData.class));
    }

    private MetaData buildMetaDataMock() {
        return new MetaData("test.txt", "target/test.txt", "text/plain");
    }
}
