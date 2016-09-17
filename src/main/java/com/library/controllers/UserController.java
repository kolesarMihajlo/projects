package com.library.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.DTO.UserDTO;
import com.library.model.Comment;
import com.library.model.Role;
import com.library.model.User;
import com.library.service.CommentService;
import com.library.service.RoleService;
import com.library.service.UserService;
import com.library.support.UserDTOToUser;
import com.library.support.UserToUserDTO;



@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toDto;
	
	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private CommentService commService;
	
	@Autowired
	private RoleService roleService;
	
/////////////////////////////////////////////////////////////////////////////////	
	//Try not hard codeing into controller it's self the path to
	//folder where files will be saved but put it in application.properties
	//let's say under name "uploaded.images";
//	@Autowired
//	private Environment env;
///////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<UserDTO> findOne(@PathVariable Long id){
		User user=null;
		user=userService.findOne(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(toDto.convert(user),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser(@RequestParam(value="username",required=false) String username,
										  @RequestParam(value="page",defaultValue="0")int page ) {
		List<UserDTO> users=new ArrayList<>();
		Page<User> pageUsers=null;
		//username is for getting data about current loged in user, I really don't like
		//this way of retreiving data but I didn't make my User class a subclass of UserDetails 
		//so if I want image of user and similar stuff this is quick fix
		//KEEP IN MIND MAKE YOUR "USERS" A "UserDetails" TYPE, then you will have more options
		//of sending all this info to front via first call
		if(username!=null){
			User user=userService.findOne(username);
			if(user!=null){
				users.add(toDto.convert(user));
				return new ResponseEntity<List<UserDTO>>(users,HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else{
			pageUsers=userService.findAll(page);
			users=toDto.convert(pageUsers.getContent());
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.add("total-pages", pageUsers.getTotalPages()+"");
			httpHeaders.add("total-elements", pageUsers.getTotalElements()+"");
			return new ResponseEntity<List<UserDTO>>(users,httpHeaders, HttpStatus.OK);
			
		}
		
		
		
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		
		UserDTO userdto=userService.deleteLogical(id);
		System.out.println(userdto.getUsername());
		System.out.println(userdto.getId());
		
		return new ResponseEntity<UserDTO>(userdto,HttpStatus.OK);
	}
	
	
	
	//Should work this way but this part must work from front end, re-test when you construct front end 
//	public ResponseEntity<UserDTO> add(@RequestPart("image") MultipartFile image,@RequestPart("newUser") UserRegistrationDTO newUser) {
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> add(@RequestPart("image") MultipartFile image,
									   @RequestPart("username") String username,
									   @RequestPart("email") String email,
									   @RequestPart("password") String password,
									   @RequestPart("passwordConfirm") String passwordConfirm) {
		if(username!=null){
			System.out.println(username);
		}
		if(image!=null){
			System.out.println(image.getOriginalFilename());
		}
		if (password == null||passwordConfirm==null || !password.equals(passwordConfirm)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		//Let set for now all new users to USER role
		Role r1=roleService.findByRole("USER");
		user.addRole(r1);
		String sp=System.getProperty("file.separator");

		///////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////					//////////////////////////////////////////////
		////////////////////   NOW THIS IS BAD VARY BAD SINCE IT'S HARDCODED  ////////////////////////////
		////////////////////	TO SPECIFIC MACHINE HOWEVER PROBLEM WITH     /////////////////////////////
		///////////////////     GETING SOME RELATIVE PATH AND IN THAT WAY   //////////////////////////////
		///////////////////      SO THE DATA PERSIST IN LET"S SAY PROJECT FOLDER /////////////////////////
		///////////////////		TURNS OUT IT'S NOT TRIVIAL. OFC I COULD HAVE   ///////////////////////////
		//////////////////      SAVE BLOB TO DB BUT AS FAR AS I KNOW THAT NOT ///////////////////////////
		///////////////////     GENERAL PRACTICE, OFC THERE IS THE FOLLOWING ////////////////////////////
		///////////////////     PROBLEM OF FORWARDING THIS "LINK" TO FRONT-END  /////////////////////////
		////////////////////    IN SUCH A WAY IT'S READABLE, WHICH MEANS POSIBLLY //////////////////////
		///////////////////     SOME WEBAPP FOLDER, OR SMTG /////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////
//		File abs=new File("D:"+sp+"savePic");
//		File newDest=new File(abs+sp+username+"_"+image.getOriginalFilename());
//		if(!newDest.exists()){
//			try {
//				newDest.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(newDest.getAbsolutePath());
//		System.out.println(newDest);
//		try {
//			image.transferTo(newDest);
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////      OK SAME STORY BUT USING ENVIORMENT ///////////////////////////////////
		///////////////////////////////////		AND APPLICATION.PROPERTIES         ////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		File f=new File(env.getProperty("uploaded.images"));
//		File newDest=new File(f+sp+username+"_"+image.getOriginalFilename());
//		if(!newDest.exists()){
//			try {
//				newDest.createNewFile();
//				image.transferTo(newDest);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}catch(IllegalStateException i){
//				i.printStackTrace();
//			}
//		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////          AFTER SEVERAL TRYOUTS WITH THIS     ///////////////////////////////////////
		////////////////////////		AND THAT I DECIDED THAT SIMPLEST AND  ////////////////////////////////////////
		///////////////////////			MOST PORTABLE SOLUTION WOULD BE TO    ////////////////////////////////////////
		///////////////////////			PUT blob or byte[] in USER class     /////////////////////////////////////////
		///////////////////////			GRANTED IT'S NOT BEST SOLUTION IN    /////////////////////////////////////////
		///////////////////////			REAL LIFE BUT SINCE I CAN'T SET UP    //////////////////////////////////////////
		///////////////////////			RELATIVE PATH WHEN SERVER STARTS    //////////////////////////////////////////
		///////////////////////			I CHOOSE THIS WAY				   ///////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		try {
			user.setImage(image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User savedUser = userService.save(user);

		return new ResponseEntity<>(toDto.convert(savedUser), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody UserDTO user,
			@PathVariable Long id) {

		if (id != user.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User persisted = userService.save(toUser.convert(user));

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET,value="/{id}/comments")
	public ResponseEntity<List<Comment>> getCommentsForUser(@PathVariable Long id){
//		if(user.getId()!=id){
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		List<Comment> comments=null;
		comments=commService.findAll(id);
		return new ResponseEntity<>(comments,HttpStatus.OK);
		
		
	}

}
