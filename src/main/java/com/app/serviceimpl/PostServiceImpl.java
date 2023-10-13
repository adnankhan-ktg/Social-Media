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
import java.util.List;
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
                        res = CommonResHelper.internalServerError();
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
            res = CommonResHelper.internalServerError();
        }
        log.info("PostServiceImpl :: createPost === END");
        return res;
    }

    @Override
    public CommonResponse likeOrUnlikePost(int userId, int postId, String useCase) {
        log.info("PostServiceImpl :: likeOrUnlikePost === START");
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
                                    res = CommonResHelper.internalServerError();
                                }
                            } else {
                                res.setStatusCode(-1020);
                                res.setMsg("Already Liked");
                            }
                            break;

                        case "unlike":
                            this.postLikeRepository.deleteByPostIdAndUserId(postId, userId);
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
            log.error("PostServiceImpl :: likeOrUnlikePost :: Exception = {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }

        log.info("PostServiceImpl :: likeOrUnlikePost === END");
        return res;
    }

    @Override
    public CommonResponse addComment(PostCommentRequest request) {
        log.info("PostServiceImpl :: addComment === START");
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
                        res = CommonResHelper.internalServerError();
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
            log.error("PostServiceImpl :: addComment :: Exception = {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostServiceImpl :: addComment === END");
        return res;
    }

    @Override
    public CommonResponse deleteCommentById(int id) {
        log.info("PostServiceImpl :: deleteCommentById === START");
        CommonResponse res = new CommonResponse();
        try {
            this.postCommentRepository.deleteById(id);
            res.setMsg("Comment deleted!");
            res.setStatusCode(200);
        } catch (Exception ex) {
            log.error("PostServiceImpl :: deleteCommentById :: Exception = {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostServiceImpl :: deleteCommentById === END");
        return res;
    }

    @Override
    public CommonResponse getCommentsForPost(int postId) {
        log.info("PostServiceImpl :: getCommentsForPost - START");
        CommonResponse res = new CommonResponse();

        try {

            log.info("PostServiceImpl :: getCommentsForPost :: postCommentRepository.findByPostId() - postId = {}", postId);
            List<PostComment> postCommentList = this.postCommentRepository.findByPostId(postId);

            if (postCommentList.isEmpty()) {
                log.info("PostServiceImpl :: getCommentsForPost - No comments found for this post.");
                res.setStatusCode(404);
                res.setMsg("No comments found for this post.");
            } else {
                log.info("PostServiceImpl :: getCommentsForPost - Comments have been successfully loaded.");
                res.setMsg("Comments have been successfully loaded.");
                res.setData(postCommentList);
                res.setStatusCode(200);
            }
        } catch (Exception ex) {
            log.error("PostServiceImpl :: getCommentsForPost - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }

        log.info("PostServiceImpl :: getCommentsForPost - END");
        return res;
    }

    @Override
    public CommonResponse getLikesForPost(int postId) {
        log.info("PostServiceImpl :: getLikesForPost - START");
        CommonResponse res = new CommonResponse();

        try {

            List<LikedPost> likedPosts = this.postLikeRepository.findByPostId(postId);

            if (likedPosts.isEmpty()) {
                log.info("PostServiceImpl :: getLikesForPost - No likes found for this post.");
                res.setMsg("No likes found for this post");
                res.setStatusCode(404);
            } else {
                log.info("PostServiceImpl :: getLikesForPost - Likes have been successfully loaded.");
                res.setMsg("Likes have been successfully loaded");
                res.setStatusCode(200);
                res.setData(likedPosts);
            }
        } catch (Exception ex) {
            log.error("PostServiceImpl :: getLikesForPost - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }

        log.info("PostServiceImpl :: getLikesForPost - END");
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


//    public String fileExtension(MultipartFile file) {
//        String originalFilename = file.getOriginalFilename();
//        int dotIndex = originalFilename.lastIndexOf('.');
//
//        if (dotIndex > 0) {
//            return originalFilename.substring(dotIndex + 1);
//        }
//
//        return "";
//    }

}