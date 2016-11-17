package com.codependent.googlesignin.security

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

public class UserInfo {
	final String id
	final String name
	final String givenName
	final String familyName
	final String gender
	final String picture
	final String link

	@JsonCreator
	public UserInfo(@JsonProperty("id") String id,
					@JsonProperty("name") String name,
					@JsonProperty("given_name") String givenName,
					@JsonProperty("family_name") String familyName,
					@JsonProperty("gender") String gender,
					@JsonProperty("picture") String picture,
					@JsonProperty("link") String link) {
		this.id = id
		this.name = name
		this.givenName = givenName
		this.familyName = familyName
		this.gender = gender
		this.picture = picture
		this.link = link
	}

}
