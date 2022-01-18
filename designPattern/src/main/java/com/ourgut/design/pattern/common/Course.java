package com.ourgut.design.pattern.common;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description
 */
@Data
public class Course {
	private String name;
	private String ppt;
	private String video;

	private Course (Builder builder){
		this.name=builder.name;
		this.ppt=builder.ppt;
		this.video=builder.video;
		return ;
	}
	@Override
	public String toString() {
		return "Course{" +
				"name='" + name + '\'' +
				", ppt='" + ppt + '\'' +
				", video='" + video + '\'' +
				'}';
	}
	public static class Builder {
		private String name;
		private String ppt;
		private String video;

		public Builder addName(String name){
			this.name=name;
			return this;
		}

		public Builder addPpt(String ppt){
			this.ppt=ppt;
			return this;
		}

		public Builder addVideo(String video){
			this.video=video;
			return this;
		}

		public Course builder(){
			return new Course(this);
		}

	}

}
