package com.app.serviceimpl;

import com.app.model.entity.Post;
import com.app.model.entity.User;
import com.app.model.response.CommonResponse;
import com.app.repository.PostRepository;
import com.app.repository.UserRepository;
import com.app.service.PostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse createPost(String postDesc, MultipartFile post) {

        CommonResponse res = new CommonResponse();


        try {
            JSONObject jsonDes = new JSONObject(postDesc);
            String dir = "src/main/resources/static/posts/";

            User user = this.userRepository.findByUserId(Integer.parseInt(jsonDes.get("userId").toString()));

            Post newPost = new Post();
            newPost.setCaption(jsonDes.get("caption").toString());
            newPost.setContentType(jsonDes.get("contentType").toString());
            newPost.setUser(user);

            Post savedPost = postRepository.save(newPost);

            if (Objects.isNull(savedPost)) {
                res.setMsg("Internal Server Error!");
                res.setStatusCode(500);
                return res;
            } else {

                dir = dir + savedPost.getPostId() + "." + fileExtension(post);

                try (OutputStream outputStream = new FileOutputStream(new File(dir))) {
                    outputStream.write(post.getBytes());
                }

                this.postRepository.updatePostUrl(dir, savedPost.getPostId());
                res.setData("Success");
                res.setStatusCode(200);
                res.setMsg("Post is successfully uploaded");

            }


        } catch (Exception ex) {
            res.setStatusCode(500);
            res.setMsg("Internal Server Error!");
        }

        return res;
    }

    public String fileExtension(MultipartFile file) {
        String extension = "";
        Integer i = null;
        Integer p = null;

        i = file.getOriginalFilename().lastIndexOf('.');
        p = Math.max(file.getOriginalFilename().lastIndexOf('/'), file.getOriginalFilename().lastIndexOf('\\'));

        if (i > p) {
            extension = file.getOriginalFilename().substring(i + 1);
        }
        return extension;
    }

    // Implement other post-related methods
}