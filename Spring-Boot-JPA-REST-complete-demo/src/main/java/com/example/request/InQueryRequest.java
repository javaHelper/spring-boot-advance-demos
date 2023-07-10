package com.example.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InQueryRequest {

	private List<String> firstNames;
}
