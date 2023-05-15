package com.petrotec.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	//aapan jevha pagination karto tevha aaplya la user la sarva details pathavyachya aahet tya saathi ha class banvatoy aapan
	//postresponse-->(1) pagenumber (2)pageSize (3) totalElements (4)totalPages  (5) lastpage (6)content
	
	private List<PostDto> content; 
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;
	
	//ha data waproon user la table format madhe data dakhvuu shakto user interfae vartii.
	//jitha tables madhe he sarva kahi mhanje page size ,content kitti etc srv dakhvuu shakato.
	
	
	

}
