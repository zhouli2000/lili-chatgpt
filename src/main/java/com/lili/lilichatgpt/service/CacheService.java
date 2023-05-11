package com.lili.lilichatgpt.service;

import com.lili.lilichatgpt.bean.LoginUser;
import com.lili.lilichatgpt.bean.openai.request.chat.ChatRequestMessage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private static final Map<String, LoginUser> USER_MAP = new ConcurrentHashMap<>();

    private static final Map<String, List<ChatRequestMessage>> CAHT_RECORD = new ConcurrentHashMap<>();

    public List<ChatRequestMessage> getRecord(String name,String msg){
        final List<ChatRequestMessage> list = Optional.ofNullable(CAHT_RECORD.get(name)).orElseGet(ArrayList::new);
        final ChatRequestMessage chatMessage = new ChatRequestMessage();
        chatMessage.setRole("user");
        chatMessage.setContent(msg);
        chatMessage.setName(name);
        list.add(chatMessage);
        CAHT_RECORD.put(name,list);
        return list;
    }

    public void deleteRecord(String name){
        CAHT_RECORD.remove(name);
    }

    public void register(LoginUser user){
        USER_MAP.put(user.getUserName(),user);
    }

    public Boolean login(String userName,String password){
        if (! isRegistered(userName)){
            return false;
        }
        final LoginUser loginUser = USER_MAP.get(userName);
        return loginUser.getPassword().equals(password);
    }

    public Boolean isRegistered(String userName){
        return USER_MAP.containsKey(userName);
    }
}
