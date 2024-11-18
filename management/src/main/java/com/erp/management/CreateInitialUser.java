package com.erp.management;

import com.erp.management.domain.model.User;
import com.erp.management.domain.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class CreateInitialUser implements CommandLineRunner {
        @Autowired
		private PasswordEncoder encoder;
		@Autowired
		private UserRespository userRespository;

		@Override
		public void run(String... args) throws Exception {
			Optional<User> userDb = userRespository.findByEmail("herbhutheadshop@gmail.com");

			if (userDb.isEmpty()){
				User user = User.builder()
						.username("herbhut")
						.email("herbhutheadshop@gmail.com")
						.password(encoder.encode("8778s5y144"))
						.role("admin")
						.build();

				userRespository.save(user);

				System.out.println("Usuario criado!");
			}
		}
}
