package com.systemdesign;
import java.util.List;

import java.util.List;



public interface FileOperation {
    void mkdir(String path);
    List<String> ls(String path);
    void addContentToFile(String path, String content);
    String readContent(String path);

}

