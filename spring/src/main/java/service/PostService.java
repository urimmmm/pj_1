package service;

import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.PostRepository;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 글 작성 처리
    public void write(Post post, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        post.setFilename(fileName);
        post.setFilepath("/files/" + fileName);
        postRepository.save(post);
    }

    // 게시글 리스트 처리
    public Page<Post> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> postSearchList(String searchKeyword, Pageable pageable) {
        return postRepository.findByTitleContaining(searchKeyword, pageable);
    }


    // 특정 게시글 불러오기
    public Post postView(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시물이 존재하지 않습니다."));
    }
}

