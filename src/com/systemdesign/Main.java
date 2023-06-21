package com.systemdesign;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    FileSystem fs = new FileSystem();
	    fs.mkdir("/a");
        fs.mkdir("/a/b/c");
        System.out.println(fs.ls("/a/b/c"));
        fs.addContentToFile("/a/b/c/d", "hello world");
        System.out.println(fs.ls("/a/b/c/"));
        System.out.println(fs.readContent("/a/b/c/d"));
    }
}
