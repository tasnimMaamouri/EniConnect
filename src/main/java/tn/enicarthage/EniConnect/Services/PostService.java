package tn.enicarthage.EniConnect.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.Entities.Admin;
import tn.enicarthage.EniConnect.Entities.AncienEtudiant;
import tn.enicarthage.EniConnect.Entities.Post;
import tn.enicarthage.EniConnect.Exceptions.ForbiddenOperationException;
import tn.enicarthage.EniConnect.Exceptions.ResourceNotFoundException;
import tn.enicarthage.EniConnect.Repositories.AdminRepo;
import tn.enicarthage.EniConnect.Repositories.AncienEtudiantRepo;
import tn.enicarthage.EniConnect.Repositories.PostRepo;

import java.io.IOException;
import java.util.List;

import static tn.enicarthage.EniConnect.EniConnectLogger.logger;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepository;
    @Autowired
    private AdminRepo adminRepository;
    @Autowired
    private AncienEtudiantRepo ancienEtudiantRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostByID(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
    }

    public Post savePost(Post post) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if (admin == null) {
            AncienEtudiant ancienEtudiant = AncienEtudiantRepo.findByEmailPersonnel(email).orElseThrow(() -> new ForbiddenOperationException("Only admins can save posts"));
            post.setEtd(ancienEtudiant);
        } else {
            post.setAdmin(admin);
        }

        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
        if (!post.getEtd().getEmailPersonnel().equals(userDetails.getUsername())) {
            throw new ForbiddenOperationException("You are not authorized to delete this post");
        }
        postRepository.deleteById(id);
    }

    public Post updatePost(Long id, Post newPost, String Title) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
        if (!post.getEtd().getEmailPersonnel().equals(userDetails.getUsername())) {
            throw new ForbiddenOperationException("You are not authorized to update this post");
        }
        post.setTitle(newPost.getTitle());
        return postRepository.save(post);
    }


    public Post addpdfPost(MultipartFile pdfFile, String currentUserEmail) throws IOException {
        try {
        // Check if the current user is an Admin
        Admin admin = adminRepository.findByEmail(currentUserEmail).orElse(null);

        // If the current user is not an Admin, throw an exception or handle accordingly
        if (admin == null) {
            // Handle the case where the current user is not an admin
            return null;
        }
            // Create a new post
            Post post = new Post();
            post.setTitle("PDF Post");
            post.setAdmin(admin);

            // Set the PDF content
            byte[] pdfBytes = pdfFile.getBytes();
            post.setContent(pdfBytes);

            // Save the post
            return postRepository.save(post);

              //  logger.info("Post added successfully");
              //  return postRepository.save(post);

            }
        catch (IOException e) {
                logger.error("Error adding post", e);
                throw e;
            }
        }
    }

