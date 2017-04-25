package com.zandroid.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
//@Builder
public class Volume {




	@Id
	private String id;
	
	private String volumeType;
	
	private String status;
	
	private Integer size;
	
	private String name;

}
