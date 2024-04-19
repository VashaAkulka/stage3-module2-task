package com.mjc.school.controller.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuText {
    ENTER_NUMBER_OF_OPERATION("Enter operation: "),
    ENTER_NEWS_ID("Enter id: "),
    ENTER_NEWS_TITLE("Enter title: "),
    ENTER_NEWS_CONTENT("Enter content: "),
    ENTER_NEWS_AUTHOR_ID("Enter author id: "),
    ENTER_AUTHOR_NAME("Enter name: "),
    OPERATION("Operation: ");

    private final String text;
}
