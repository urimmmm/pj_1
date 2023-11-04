package controller;

import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import service.PostService;

@Controller
public class PostController {


    @Autowired
    private PostService postService;

    //post 작성 페이지
    @GetMapping("/post/write") //localhost:8090/post/write
    public String postWriteForm() {

        return "postwrite";
    }

    //post 작성하고 '글 작성이 완료되었습니다' -> 게시글 목록으로 이동
    @PostMapping("/post/writepro")
    public String postWritePro(Post post, Model model, MultipartFile file) throws Exception{
        postService.write(post, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/post/list");
        return "message";
    }

    //게시글 목록 보여주는 페이지
    @GetMapping("/post/list")
    public String postList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Post> list = null;

        if(searchKeyword == null) {
            list = postService.postList(pageable);
        }else {
            list = postService.postSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "postlist";
    }

    @GetMapping("/post/view") // localhost:8080/post/view?id=1
    public String postView(Model model, Integer id) {
        model.addAttribute("post", postService.postView(id));
        return "postview";
    }

    @GetMapping("/post/modify/{id}")
    public String postModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("post", postService.postView(id));
        return "postmodify";
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, Post post, MultipartFile file) throws Exception{
        Post postTemp = postService.postView(id);
        postTemp.setTitle(post.getTitle());
        postTemp.setContent(post.getContent());
        postService.write(postTemp, file);
        return "redirect:/post/list";
    }
}
