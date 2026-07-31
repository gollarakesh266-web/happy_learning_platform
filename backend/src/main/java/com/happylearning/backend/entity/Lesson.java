package com.happylearning.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_title", nullable = false)
    private String lessonTitle;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public Lesson() {
    }

    public Lesson(String lessonTitle, Long courseId, String content) {
        this.lessonTitle = lessonTitle;
        this.courseId = courseId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}