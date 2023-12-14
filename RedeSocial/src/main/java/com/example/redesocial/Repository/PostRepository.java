package com.example.redesocial.Repository;

import com.example.redesocial.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Override
    List<Post> findAll(Sort sort);
}
