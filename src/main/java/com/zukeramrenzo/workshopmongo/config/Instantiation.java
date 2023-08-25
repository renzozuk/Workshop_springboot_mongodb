package com.zukeramrenzo.workshopmongo.config;

import com.zukeramrenzo.workshopmongo.domains.Post;
import com.zukeramrenzo.workshopmongo.domains.User;
import com.zukeramrenzo.workshopmongo.dto.AuthorDTO;
import com.zukeramrenzo.workshopmongo.dto.CommentDTO;
import com.zukeramrenzo.workshopmongo.repositories.PostRepository;
import com.zukeramrenzo.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User first = new User(null, "Yoshio Takahashi", "yoshio.taka@gmail.com");
        User second = new User(null, "Hirohito", "hiro@gmail.com");
        userRepository.save(first);
        userRepository.save(second);
        userRepository.save(new User(null, "Kasumi Yamaguchi", "hiro@gmail.com"));

        Post post1 = new Post(null, sdf.parse("24/08/2023"), "Partiu viagem", "Vou viajar para Goianinha. Abraços!", new AuthorDTO(first));
        Post post2 = new Post(null, sdf.parse("10/11/2008"), "4 souls", "Eu nunca conseguirei a joia de 4 almas. É uma lástima.", new AuthorDTO(first));

        CommentDTO c1 = new CommentDTO("It was an amazing trip!", sdf.parse("11/11/2023"), new AuthorDTO(first));
        CommentDTO c2 = new CommentDTO("Para ser merecedor da joia de 4 almas, você deve provar o seu valor.", sdf.parse("11/11/2008"), new AuthorDTO(second));
        post1.getComments().add(c1);
        post2.getComments().add(c2);

        postRepository.save(post1);
        postRepository.save(post2);

        first.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(first);
    }
}
