package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Класс реализующий репозиторий
 */
public class PostRepository {

    private AtomicLong numId = new AtomicLong(0);
    private List<Post> listPost = new CopyOnWriteArrayList<>();

    /**
     * Метод для вывода всех элементов Post
     */
    public List<Post> all() {
        return listPost;
    }

    /**
     * Метод для получения Post по id
     */
    public Optional<Post> getById(long id) {
        Post post = findPostForId(id);
        if (post != null) {
            return Optional.of(post);
        }
        printException(id);
        return Optional.empty();
    }

    /**
     * Метод для сохранения нового Post или перезапись существующего
     */
    public Post save(Post post) {
        long id = post.getId();
        if (id == 0) {
            post = new Post(numId.incrementAndGet(), post.getContent());
            listPost.add(post);
        } else {
            if (findPostId(id, post)) {
                return post;
            } else {
                printException(id);
            }
        }
        return post;
    }

    /**
     * Метод для удаления Post по id
     */
    public void removeById(long id) {
        Post post = findPostForId(id);
        if (post != null) {
            listPost.remove(post);
        } else {
            printException(id);
        }
    }

    /**
     * Перезапись Post с существующим айди
     */
    private boolean findPostId(long id, Post post) {
        Post postForList = findPostForId(id);
        if (postForList != null) {
            listPost.remove(postForList);
            listPost.add(post);
            return true;
        }
        return false;
    }

    /**
     * Помск Post по id среди существующих
     */
    private Post findPostForId(long id) {
        for (Post post : listPost) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    /**
     * Печать ошибки о не найденной записи
     */
    private void printException(long id) {
        throw new NotFoundException("Не найдена запись с id = " + id);
    }
}
