package application;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import bookmarks.Account;
import bookmarks.AccountRepository;
import bookmarks.Bookmark;
import bookmarks.BookmarkRepository;

@SpringBootApplication(scanBasePackages={"hello", "bookmarks"})
//@ComponentScan(basePackageClasses = {GreetingController.class, BookmarkRestController.class})
//@ComponentScan(basePackages = {"hello", "bookmarks"})
@EntityScan("bookmarks")
@EnableJpaRepositories("bookmarks")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(AccountRepository accountRepository,
			BookmarkRepository bookmarkRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong"
						.split(",")).forEach(
				a -> {
					Account account = accountRepository.save(new Account(a,
							"password"));
					bookmarkRepository.save(new Bookmark(account,
							"http://bookmark.com/1/" + a, "A description"));
					bookmarkRepository.save(new Bookmark(account,
							"http://bookmark.com/2/" + a, "A description"));
				});
	}
}