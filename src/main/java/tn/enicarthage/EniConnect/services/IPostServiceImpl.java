package tn.enicarthage.EniConnect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;
import tn.enicarthage.EniConnect.repositories.AdminRepository;
import tn.enicarthage.EniConnect.repositories.AncienEtudiantRepository;
import tn.enicarthage.EniConnect.repositories.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class IPostServiceImpl implements IPostService{

    @Autowired
    private static PostRepository postRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AncienEtudiantRepository ancienEtudiantRepository;

    @Override
    public List<Post> getPostsByAncienEtudiant(String emailPersonnelle) {
        List<Post> posts=new ArrayList<Post>();
        for(Post article:postRepository.findByAncienEtudiantEmailPersonnelle(emailPersonnelle)) {
            posts.add(article);
        }

        return posts;
    }

    @Override
    public Post getPostByID(Long id) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts=new ArrayList<Post>();
        for(Post article:postRepository.findAll()) {
            posts.add(article);
        }
        return posts;    }

    @Override
    public Post savePost(Post post) {

        return postRepository.save(post);

    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePost(Post post, Long id) {
        post.setID(id);

        return postRepository.save(post);
    }


    @Override
    public Post uploadPost(MultipartFile pdfFile, Long ownerID, String title, String content, Status status) throws IOException {
        Post post = new Post();
        post.setOwnerID(ownerID);
        post.setTitle(title);
        post.setContent(content);
        post.setStatus(status);

        post.setContent(Arrays.toString(pdfFile.getBytes()));
        return postRepository.save(post);
    }


}
