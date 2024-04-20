package tn.enicarthage.EniConnect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.Entities.Admin;
import tn.enicarthage.EniConnect.Entities.Post;
import tn.enicarthage.EniConnect.Repositories.AdminRepo;
import tn.enicarthage.EniConnect.Repositories.PostRepo;
import tn.enicarthage.EniConnect.Services.PostService;
import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostTest {
        @Mock
        private PostRepo postRepository;

        @Mock
        private AdminRepo adminRepository;

        @InjectMocks
        private PostService postService;

        @Test
        public void testAddPost() throws IOException {
            // Mock admin
            Admin admin = new Admin();
            admin.setEmail("admin@example.com");

            // Mock PDF file
            byte[] pdfContent = "PDF content".getBytes();
            MultipartFile pdfFile = new MockMultipartFile("pdfFile", "example.pdf", "application/pdf", pdfContent);

            // Mock admin repository
            when(adminRepository.findByEmail("admin@gmail.com")).thenReturn(Optional.of(admin));

            // Call the method under test
          //  postService.savePost(pdfFile);

            // Verify that save method is called with correct arguments
            verify(postRepository, times(1)).save(any(Post.class));
        }
    }
