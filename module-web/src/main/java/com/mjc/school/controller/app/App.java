package com.mjc.school.controller.app;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.menu.MenuOption;
import com.mjc.school.controller.menu.MenuText;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class App implements BaseApp {
    private final AuthorController authorController;
    private final NewsController newsController;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void startApp() {
        while (true) {
            System.out.println(MenuText.ENTER_NUMBER_OF_OPERATION);
            for (MenuOption options: MenuOption.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }

            switch (scanner.nextLine()) {
                case "1" -> viewAllNews();
                case "2" -> viewsNewsById();
                case "3" -> addNews();
                case "4" -> editNews();
                case "5" -> deleteNews();
                case "6" -> viewAllAuthor();
                case "7" -> viewsAuthorById();
                case "8" -> addAuthor();
                case "9" -> editAuthor();
                case "10" -> deleteAuthor();
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid Operation");
            }
        }
    }

    private void addNews() {
        NewsModel newsModel = new NewsModel();

        System.out.print(MenuText.ENTER_NEWS_TITLE);
        scanner.nextLine();
        newsModel.setTitle(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_CONTENT);
        newsModel.setContent(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID);
        newsModel.setAuthorId(scanner.nextLong());

        newsController.create(newsModel);
    }

    private void addAuthor() {
        AuthorModel authorModel = new AuthorModel();

        System.out.print(MenuText.ENTER_AUTHOR_NAME);
        authorModel.setName(scanner.next());

        authorController.create(authorModel);
    }

    private void editNews() {
        NewsModel newsModel = new NewsModel();

        System.out.print(MenuText.ENTER_NEWS_TITLE);
        scanner.nextLine();
        newsModel.setTitle(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_CONTENT);
        newsModel.setContent(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID);
        newsModel.setAuthorId(scanner.nextLong());

        newsController.update(newsModel);
    }

    private void editAuthor() {
        AuthorModel authorModel = new AuthorModel();

        System.out.print(MenuText.ENTER_AUTHOR_NAME);
        authorModel.setName(scanner.next());

        authorController.update(authorModel);
    }

    private void deleteNews() {
        System.out.print(MenuText.ENTER_NEWS_ID);
        long id = scanner.nextLong();
        newsController.deleteById(id);
    }

    private void deleteAuthor() {
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID);
        long id = scanner.nextLong();
        authorController.deleteById(id);
    }


    private void viewAllNews() {
        newsController.readAll();
    }

    private void viewsNewsById() {
        System.out.print(MenuText.ENTER_NEWS_ID);
        long id = scanner.nextLong();
        newsController.readById(id);
    }

    private void viewAllAuthor() {
        authorController.readAll();
    }

    private void viewsAuthorById() {
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID);
        long id = scanner.nextLong();
        authorController.readById(id);
    }
}
