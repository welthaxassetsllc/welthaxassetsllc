package health.medicalsolutions.app.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public boolean addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

}
