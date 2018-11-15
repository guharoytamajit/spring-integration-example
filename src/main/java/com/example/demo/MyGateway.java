package com.example.demo;

import java.util.concurrent.Future;

public interface MyGateway {
	Future<String> method1(String msg);
}
