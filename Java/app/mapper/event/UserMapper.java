package mapper.event;

import mapper.GenericMapper;
import models.User;
import dto.UserDto;

public class UserMapper extends GenericMapper<User, UserDto>{
	
	private static final UserMapper instance = new UserMapper();

	private UserMapper() {

	}

	public static UserMapper getInstance() {
		return instance;
	}

	@Override
	public User mapToEntity(UserDto dto) {
		User user =  new User();
		user.setAge(dto.getAge());
		user.setEmail(dto.getEmail());
		user.setGender(dto.getGender());
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername());
		user.setSalt(dto.getSalt());
		return user;
	}

	@Override
	public UserDto mapToDto(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
