package com.petrotec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petrotec.entity.User;
import com.petrotec.exceptions.ResourceNotFoundException;
import com.petrotec.payload.UserDto;
import com.petrotec.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		//userRepository.save(null)//aata problem yeil aaplya la userdto la naahi user la pass karaayach aahe tya 
		//saathi aapan model mapper waapru shakata tya saathi aapan 2 method banvuu khalli bagha 
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
	User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepository.save(user);
		UserDto userToDto1 = this.userToDto(updatedUser);
		return userToDto1;
	}

	@Override
	public UserDto getUserByID(Integer userId) {
		User user = this.userRepository.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> usersFindAll = this.userRepository.findAll();
		List<UserDto> userDtoListOfAllUsers = usersFindAll.stream().
				map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtoListOfAllUsers;
	}

	@Override
	public void deleteUser(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> 
		                      new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
		
	}

	
	
	
	
	//conversion
	//2 mtd banvuu
	//entiy la dto madhe change karuu
	//aani dto la entity madhe change karuu
	//converting userDto object into User(entity) object manually.
	
	
//	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
//	}
	
	//Step--(4)) -->create method to convert userDto Object To USer(entity) Object USing modelMApper
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		//                               userDto Converting Into User(Entity) OBject
		return user;

		
	}
	
	
	
	
	
	//taking user as a parameter and we are manually converting it into userDto
	//converting 1 object into another object manually.
//	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//		return userDto;
//}
	//Converting User(entity) Object Into UserDto Object By USing ModelMapper
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
		//aapan hai sarva manually kartoy 
		//aata aapan fakata 1ch object sobat kaam kartoy mhanoon kahi viashy nahi
		//pn jasa jasa aaplya fields waadhat jaatil tasa tasa hai kaam khoop hectic houn jaail
		//tya saathi aaplya kade library ahe-->model mapper library
		//hei kaam conversion ch automatically houn jaail
		//procedure to use model mapper library
		
		//(1) Go to Google chrome -->search-->model mapper maven Dependency-->org.mapper-->select any lateversion
		//(2) Add it In Pom.xml file
		//(3) Declare Bean As Modell Mapper Aani Tyaalach Autowired Karoon Aaplya lA Use Karawa Lagel.
		//(4) Go In Main Class And Declare A Bean --=> Create Method
		//    @Bean
		//    public ModelMapper modelMapper(){
		//    return new ModelMapper();
		//Aata Aapan yaala Aapya Project MAdhe Use KAroo Shakato
		
		//(5) Go Into UserServiceImpl Class 
		//    @AutoWired
		//    private ModelMapper modelMapper
		
		//aata aapan yala ek object la dusrya object madhe conver KArnya saati Wapruu Shakato
		//mhanje AAta Aaplya LA manuaaly User(entity) jo ki aapla entity Class mahnoon aaapan Wapartoy
		//tyaala Aaplya la manually USerDto Object Madhe Conver KArnyachi KAhi Garaj Nahi Padnaar.
		
	
	
}