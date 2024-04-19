package com.mjc.school.controller.app;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.MenuOption;
import com.mjc.school.controller.menu.MenuText;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class App {
    private final BaseController<AuthorDTO, Long> authorController;
    private final BaseController<NewsDTO, Long> newsController;
    Scanner scanner = new Scanner(System.in);


    public void startApp() {
        while (true) {
            for (MenuOption options: MenuOption.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }

            System.out.print(MenuText.ENTER_NUMBER_OF_OPERATION.getText());
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
        NewsDTO newsModel = new NewsDTO();

        System.out.print(MenuText.ENTER_NEWS_TITLE.getText());
        newsModel.setTitle(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_CONTENT.getText());
        newsModel.setContent(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        newsModel.setAuthorId(scanner.nextLong());

        scanner.nextLine();
        newsController.create(newsModel);
    }

    private void addAuthor() {
        AuthorDTO authorModel = new AuthorDTO();

        System.out.print(MenuText.ENTER_AUTHOR_NAME.getText());
        authorModel.setName(scanner.next());

        scanner.nextLine();
        authorController.create(authorModel);
    }

    private void editNews() {
        NewsDTO newsModel = new NewsDTO();

        System.out.print(MenuText.ENTER_NEWS_TITLE.getText());
        newsModel.setTitle(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_CONTENT.getText());
        newsModel.setContent(scanner.nextLine());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        newsModel.setAuthorId(scanner.nextLong());
        System.out.println(MenuText.ENTER_NEWS_ID.getText());
        Long id = scanner.nextLong();

        scanner.nextLine();
        newsController.update(newsModel, id);
    }

    private void editAuthor() {
        AuthorDTO authorModel = new AuthorDTO();

        System.out.print(MenuText.ENTER_AUTHOR_NAME.getText());
        authorModel.setName(scanner.next());
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        Long id = scanner.nextLong();

        scanner.nextLine();
        authorController.update(authorModel, id);
    }

    private void deleteNews() {
        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        newsController.deleteById(id);
    }

    private void deleteAuthor() {
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        authorController.deleteById(id);
    }


    private void viewAllNews() {
        newsController.readAll();
    }

    private void viewsNewsById() {
        System.out.print(MenuText.ENTER_NEWS_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        newsController.readById(id);
    }

    private void viewAllAuthor() {
        authorController.readAll();
    }

    private void viewsAuthorById() {
        System.out.print(MenuText.ENTER_NEWS_AUTHOR_ID.getText());
        long id = scanner.nextLong();
        scanner.nextLine();
        authorController.readById(id);
    }
}
