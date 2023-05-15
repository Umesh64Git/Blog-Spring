package com.petrotec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petrotec.entity.Category;
import com.petrotec.entity.Post;
import com.petrotec.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	//Creting custom FindBy method To get User All Post
	List<Post> findByUser(User user);
	
	
	//Creting custom FindBy method To get User All post by category
	List<Post> findByCategory(Category category);
	
	
	//Searching Method
	//Explaination-->
	//jar aapan po search kela Tar title madhe Kuthe Pn Po Asel Tar to aaplya la te Title Search Karoon Aanoon deil.
	List<Post> findByTitleContaining(String title);
	
	
	
	//Jar content Wise search Karayacha Asel Data Db Madhoon tar
	//mhanje aapan aaplya hishobaane search parameter takoon data search karoo shakato findBy methods Waproon
	//List<Post> findByContentContains(String content);

}
