package com.systemdesign;

import java.util.*;

public class FileSystem implements FileOperation{
    @Override
    public void mkdir(String path) {
        String[] pathName = path.split("/");
        Directory root = this.root;
        for(String paths : pathName){
            if(root.children.containsKey(paths)){
                root = root.children.get(paths);
                continue;
            }
            root.children.put(paths, new Directory(false, null, paths));
            root = root.children.get(paths);
        }
        System.out.println("Directory "+path+" created");
    }

    @Override
    public List<String> ls(String path) {
        String[] pathName = path.split("/"); // "a/b/c"
        Directory root = this.root;
        List<String> dir = new ArrayList<>();
        for(int i=0; i<pathName.length-1;i++){
            root = root.children.get(pathName[i]);
        }
        if(root.isFile){
            dir.add(root.name);
            return dir;
        }
        //root points to b
        Set<String> keys = root.children.keySet(); //get all child of c
        //Sort child of c
        for(String key: keys){
            dir.add(key);
        }
        Collections.sort(dir);
        return dir;
    }

    @Override
    public void addContentToFile(String path, String content) {
        String[] pathName = path.split("/"); // "a/b/c"
        Directory root = this.root;
        for(int i=0; i<pathName.length-1;i++){
            if(root.children.containsKey(pathName[i])){
                root = root.children.get(pathName[i]);
                continue;
            }
            //root points to b
            root.children.put(pathName[i], new Directory(false, null, pathName[i]));
            root = root.children.get(pathName[i]);
        }
        //create/update file
        root.isFile = true;

       if(root.content == null){
            root.content = content;
        }
        else{
            root.content = root.content + content;
        }
        System.out.println("Content added to file "+path);

    }

    @Override
    public String readContent(String path) {
        String[] pathName = path.split("/"); // "a/b/c"
        Directory root = this.root;
        for(int i=0; i<pathName.length-1;i++){
            root = root.children.get(pathName[i]);
        }
        return root.content;
    }

    class Directory{
        Map<String, Directory> children;
        //False when it is dir
        boolean isFile;
        //null when it is dir
        String content;
        String name;
        Directory(boolean isFile, String content, String name){
            this.isFile = isFile;
            this.content = content;
            children = new HashMap<>();
            this.name = name;
        }
    }
    Directory root;
    FileSystem(){
        root = new Directory(false, null, "/");
    }

    class Directory2{
        Map<String, Directory> dir; //All Dirs
        Map<String, String> files; //All Files
    }
}

