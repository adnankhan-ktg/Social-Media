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
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private LikedPostRepository postLikeRepository;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);


    @Override
    public CommonResponse createPost(String postDesc, MultipartFile post) {
        log.info("PostServiceImpl :: createPost === START");
        CommonResponse res = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyymmhhss");

        try {

            JSONObject jsonDes = new JSONObject(postDesc);
            String dir = "src/main/resources/static/posts/";

            int categoryId = Integer.parseInt(jsonDes.get("categoryId").toString());
            int userId = Integer.parseInt(jsonDes.get("userId").toString());

            Optional<User> fetchedUser = this.userRepository.findById(userId);

            if (fetchedUser.isPresent()) {

                Optional<CategoryMst> postCategoryMst = categoryMstRepository.findById(categoryId);

                if (postCategoryMst.isPresent()) {

                    Post newPost = new Post();
                    newPost.setCaption(jsonDes.get("caption").toString());
                    newPost.setContentType(jsonDes.get("contentType").toString());
                    newPost.setCategory(postCategoryMst.get());
                    newPost.setUser(fetchedUser.get());

                    dir = dir + userId + "_" + sdf.format(new Date()) + "." + fileExtension(post);

                    try (OutputStream outputStream = new FileOutputStream(new File(dir))) {
                        outputStream.write(post.getBytes());
                    }

                    newPost.setPostUrl(dir);
                    if (Objects.isNull(this.postRepository.save(newPost))) {
                        res = CommonResHelper.interServerError();
                    } else {
                        res.setStatusCode(200);
                        res.setMsg("Post is successfully uploaded");
                    }
                } else {
                    res.setStatusCode(404);
                    res.setMsg("Category does not exists");
                }
            } else {
                res.setMsg("User does not exists!");
                res.setStatusCode(404);
            }
        } catch (Exception ex) {
            res = CommonResHelper.interServerError();
        }
        log.info("PostServiceImpl :: createPost === END");
        return res;
    }

    @Override
    public CommonResponse doLikeOrUnlike(int userId, int postId, String useCase) {
        log.info("PostServiceImpl :: doLikeOrUnlike === START");
        CommonResponse res = new CommonResponse();

        try {

            Optional<User> fetchedUser = this.userRepository.findById(userId);

            if (fetchedUser.isPresent()) {

                Optional<Post> fetchedPost = this.postRepository.findById(postId);

                if (fetchedPost.isPresent()) {

                    switch (useCase.toLowerCase()) {

                        case "like":

                            if (Objects.isNull(this.postLikeRepository.findByPostIdAndUserId(postId, userId))) {
                                LikedPost postLike = new LikedPost();
                                postLike.setPost(fetchedPost.get());
                                postLike.setUser(fetchedUser.get());

                                if (Objects.nonNull(this.postLikeRepository.save(postLike))) {
                                    res.setStatusCode(200);
                                    res.setMsg("Liked successfully");
                                } else {
                                    res = CommonResHelper.interServerError();
                                }
                            } else {
                                res.setStatusCode(-1020);
                                res.setMsg("Already Liked");
                            }
                            break;

                        case "unlike":
                            this.postLikeRepository.unlikePost(postId, userId);
                            res.setMsg("UnLiked post successfully!");
                            res.setStatusCode(200);
                            break;
                        default:
                            break;
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
            log.error("PostServiceImpl :: doLikeOrUnlike :: Exception = {}", ex.getMessage());
            res = CommonResHelper.interServerError();
        }

        log.info("PostServiceImpl :: doLikeOrUnlike === END");
        return res;
    }

    @Override
    public CommonResponse doComment(PostCommentRequest request) {
        log.info("PostServiceImpl :: doComment === START");
        CommonResponse res = new CommonResponse();
        try {
            Optional<User> fetchedUser = this.userRepository.findById(request.getUserId());

            if (fetchedUser.isPresent()) {

                Optional<Post> fetchedPost = this.postRepository.findById(request.getPostId());

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