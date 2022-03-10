package jm_social_project.media_storage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jm_social_project.media_storage.model.PhotoContent;
import jm_social_project.media_storage.repository.PhotoContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class StorageRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PhotoContentRepository photoContentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllContent() throws Exception {
        List<PhotoContent> allContent = photoContentRepository.findAll();
        String expected = objectMapper.writeValueAsString(allContent);

        this.mockMvc
                .perform(get("/api")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void uploadPhoto() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                "<<png data>>" .getBytes()
        );

        this.mockMvc
                .perform(fileUpload("/api/upload_photo/").file(file).header("user_id", "1"))
                .andDo(print())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    void uploadVideo() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "video.mp4",
                "video/mp4",
                "<<video data>>".getBytes()
        );

        this.mockMvc
                .perform(fileUpload("/api/upload_video/").file(file).header("user_id", "2"))
                .andDo(print())
                .andDo(document("{methodName}", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }
}
