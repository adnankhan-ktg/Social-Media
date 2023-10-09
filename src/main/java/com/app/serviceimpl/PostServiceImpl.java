package com.app.serviceimpl;

import com.app.controller.UserController;
import com.app.helper.CommonResHelper;
import com.app.model.entity.*;
import com.app.model.payload.PostCommentRequest;
import com.app.model.response.CommonResponse;
import com.app.repository.*;
import com.app.service.PostService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostCategoryMstRepository categoryMstRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);


    @Override
    public CommonResponse createPost(String postDesc, MultipartFile post) {

        CommonResponse res = new CommonResponse();


        try {
            JSONObject jsonDes = new JSONObject(postDesc);
            String dir = "src/main/resources/static/posts/";

            Optional<User> user = this.userRepository.findByUserId(Integer.parseInt(jsonDes.get("userId").toString()));
            CategoryMst postCategoryMst = categoryMstRepository.findByName(jsonDes.get("categoryName").toString());


            Post newPost = new Post();
            newPost.setCaption(jsonDes.get("caption").toString());
            newPost.setContentType(jsonDes.get("contentType").toString());
            newPost.setCategory(postCategoryMst);
            newPost.setUser(user.get());

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

    @Override
    public CommonResponse doLike(int userId, int postId) {
        log.info("PostServiceImpl :: doLike === START");
        CommonResponse res = new CommonResponse();

        try {
            Optional<User> fetchedUser = this.userRepository.findByUserId(userId);

            if (fetchedUser.isPresent()) {

                Optional<Post> fetchedPost = this.postRepository.findByPostId(postId);

                if (fetchedPost.isPresent()) {

                    PostLike postLike = new PostLike();
                    postLike.setPost(fetchedPost.get());
                    postLike.setUser(fetchedUser.get());

                    if (Objects.nonNull(this.postLikeRepository.save(postLike))) {
                        res.setStatusCode(200);
                        res.setMsg("Liked successfully");
                    } else {
                        res = CommonResHelper.interServerError();
                    }
                } else {
                    res.setMsg("Post does not exists!");
                    res.setStatusCode(404);
                }

            } else {
                res.setMsg("User does not exists!");
                res.setStatusCode(404);
            }

        } catch (Exception ex) {
            log.error("PostServiceImpl :: doLike :: Exception = {}", ex.getMessage());
            res = CommonResHelper.interServerError();
        }

        log.info("PostServiceImpl :: doLike === END");
        return res;
    }

    @Override
    public CommonResponse doComment(PostCommentRequest request) {
        log.info("PostServiceImpl :: doComment === START");
        CommonResponse res = new CommonResponse();
        try {
            Optional<User> fetchedUser = this.userRepository.findByUserId(request.getUserId());

            if (fetchedUser.isPresent()) {

                Optional<Post> fetchedPost = this.postRepository.findByPostId(request.getPostId());

                if (fetchedPost.isPresent()) {

                    PostComment postComment = new PostComment();
                    postComment.setUser(fetchedUser.get());
                    postComment.setPost(fetchedPost.get());
                    postComment.setComment(request.getComment());

                    if (Objects.nonNull(this.postCommentRepository.save(postComment))) {
                        res.setStatusCode(200);
                        res.setMsg("Commented successfully");
                    } else {
                        res = CommonResHelper.interServerError();
                    }
                } else {
                    res.setMsg("Post does not exists!");
                    res.setStatusCode(404);
                }

            } else {
                res.setMsg("User does not exists!");
                res.setStatusCode(404);
            }
        } catch (Exception ex) {
            log.error("PostServiceImpl :: doComment :: Exception = {}", ex.getMessage());
            res = CommonResHelper.interServerError();
        }
        log.info("PostServiceImpl :: doComment === END");
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

}