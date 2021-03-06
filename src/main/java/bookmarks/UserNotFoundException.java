package bookmarks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2284081933804178392L;

	public UserNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
}
