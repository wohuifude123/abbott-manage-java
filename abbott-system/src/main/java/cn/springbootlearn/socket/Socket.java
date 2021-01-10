package cn.springbootlearn.socket;

import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.bean.RoundData;
import cn.spring.inter.bean.User;
import cn.spring.inter.bean.UserGridInfo;
import cn.spring.inter.utils.ChessDataCompute;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@Controller
@ServerEndpoint(value="/websocket/{info}")
public class Socket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    /*websocket 客户端会话 通过Session 向客户端发送数据*/
    private Session session;
    /*线程安全set 存放每个客户端处理消息的对象*/
    private static CopyOnWriteArraySet<Socket> webSocketSet = new CopyOnWriteArraySet();
    /*websocket 连接建立成功后进行调用*/


    private static Map<String, String> userList = new HashMap<String, String>();

    private static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//创建时间格式对象

    /**
     * 功能：创建一个房间的集合，用来存放房间
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     * 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Socket>> roomList = new  ConcurrentHashMap<String,ConcurrentHashMap<String, Socket>>();

    //重新加入房间的标示；
    private int rejoin = 0;
    static {
        roomList.put("room1", new ConcurrentHashMap<String, Socket>());
        roomList.put("room2", new ConcurrentHashMap<String, Socket>());
//        roomList.put("wuxian20200424", new ConcurrentHashMap<String, Socket>());
    }

    private static Map<String, String> recordMap = new HashMap<String,String>();
    Map<String, String[]> userMap = new HashMap<String,String[]>();

    @OnOpen
    public void onOpen(@PathParam(value = "info") String param, Session session, EndpointConfig config) {
        this.session = session;
        String flag = param.split("[|]")[0]; 		//标识
        String roomId = param.split("[|]")[1];		//成员名

        // 获取当前用户的session
//        System.out.println("sessionId = " + session.getId());
//        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//        User user  = (User) httpSession.getAttribute("user"); // 获得当前用户信息

//        System.out.println("flag === " + flag);

        if(flag.equals("join")){
//            String user = param.split("[|]")[2];
            String username = param.split("[|]")[2];		//成员名
            System.out.println("username === " + username);
            joinRoom(roomId, username);
        }
        String usernames[] = {};
        userMap.put(roomId, usernames);
//        System.out.println("session == " + session);
//        webSocketSet.add(this);
        System.out.println("websocket 有新的链接" + webSocketSet.size());

    }

    //加入房间
    public void joinRoom(String roomId, String user){
        if(roomList.containsKey(roomId)) {
            System.out.println("没有创建新的房间");
            ConcurrentHashMap<String, Socket> roomListMap = roomList.get(roomId);
            if(roomListMap.get(user) != null){//该用户有没有出
//            this.rejoin = 1;
                this.rejoin = 1;
            }
            roomListMap.put(user, this);//将此用户加入房间中
        } else {
            System.out.println("创建新的房间完成");
            ConcurrentHashMap<String, Socket> newRoom = new ConcurrentHashMap<String, Socket>();
            roomList.put(roomId, newRoom);
            newRoom.put(user, this);
        }
    }

    public void sendRoomMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam(value = "info") String param, Session session, CloseReason reason) {

        System.out.println("结束这局游戏 = " + param);
        String roomId = param.split("[|]")[1]; //用户账号
        String username = param.split("[|]")[2]; //用户账号
//        JSONObject obj = JSONObject.parseObject(message);

        //将用户从聊天室中移除
        int f2 = 1;
        roomList.get(roomId).remove(username);

        recordMap.clear();
        userMap.clear();
        webSocketSet.remove(this);
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        for (Socket socket : webSocketSet) {
//            socket.session.getBasicRemote().sendText("自己嘎给自己嘎发的消息："+message);
//            socket.session.getBasicRemote().sendText(message);
//        }
//        System.out.println("message === " + message);
        JSONObject obj = JSONObject.parseObject(message);
        ResponseData responseData = new ResponseData();
        String roomId = null;

        if (obj.containsKey("flag")) {
            if(obj.get("flag").toString().equals("chat-room")){
                roomId = obj.get("roomId").toString();
                String roundIndex = obj.get("roundIndex").toString();
                String username = obj.get("username").toString();
                String globalFightData = obj.get("globalFightData").toString();

                ConcurrentHashMap<String, Socket> r =roomList.get(roomId);
                List<String> userNames = new ArrayList<>();

//                System.out.println("userNameSize === "+ userNames.size());
                for(String userName: r.keySet()){
//                    System.out.println("u === " + userName);
                    userNames.add(userName);
                }

//                String usernames[] = {};
//                usernames = userMap.get(roomId);



//                System.out.println("usernames.length === " + usernames.length);

//                System.out.println("userListSize === " + userList.size());
//                boolean userFlag = userList.containsKey(username);
//                if(userFlag) {
//
//                } else {
//                    userList.put(username, username);
//                }

//                if(usernames.length == 0) {
//                    usernames = new String[1];
//                    usernames[0] = username;
//                    userMap.put(roomId, usernames);
//                } else if(usernames.length == 1) {
//                    usernames = userMap.get(roomId);
//                    System.out.println("usernames === " + usernames[0]);
//                    if(usernames[0].equals(username)) {
//                        //
//                    } else {
//                        String temporaryName = usernames[0];
//                        usernames = new String[2];
//                        usernames[0] = temporaryName;
//                        usernames[1] = username;
//                        userMap.put(roomId, usernames);
//                    }
//                }

                String roundInfoKey = username + "/" + roomId + "/" + roundIndex;
                String roundInfoValue = recordMap.get(roundInfoKey);
//                System.out.println("roundInfoValue === " + roundInfoValue);

                JSONObject resultCompared = new JSONObject();

                boolean recordMapFirst = false;
                boolean recordMapSecond = false;
                String recordFirstKey = "";
                String recordSecondKey = "";
                int userCount = 0;
                boolean recordMapKeyFlag = false;
                RoundData roundData = new RoundData();
                roundData.setIndex(0);

                ChessDataCompute chessDataCompute = new ChessDataCompute();
                JSONObject usersChessData = new JSONObject();

                UserGridInfo[] userGridInfoArray=new UserGridInfo[userNames.size()];
                String[] usersArray = new String[userNames.size()];
                List<String> userNamesList = new ArrayList<>();

                if(roundInfoValue == null) { // 用户没有传递消息

                    System.out.println("用户首次发送消息");
                    recordMap.put(roundInfoKey, globalFightData);

                    ConcurrentHashMap<String, Socket> roomListMap = roomList.get(roomId);

//                    for(String key : roomListMap.keySet()){
//                        System.out.println("roomListMap == " + key);
//                    }

//                    int roomListMapKeySize = roomListMap.keySet().size();

//                    System.out.println("roomListMapKeySize == " + roomListMapKeySize);

                    for(String key : roomListMap.keySet()){
//                        System.out.println("roomListMap == " + key);
                        roundInfoKey = key + "/" + roomId + "/" + roundIndex;

//                        System.out.println("userCount == " + userCount);
                        if(userCount == 0) {
                            recordMapKeyFlag = recordMap.containsKey(roundInfoKey);
                            if(recordMapKeyFlag) {
                                recordMapFirst = true;
                                recordFirstKey = roundInfoKey;
                            }
                            userCount = userCount + 1;
                        } else if(userCount == 1){
                            recordMapKeyFlag = recordMap.containsKey(roundInfoKey);
                            if(recordMapKeyFlag) {
                                recordMapSecond = true;
                                recordSecondKey = roundInfoKey;
                                userCount = 0;
                            }
                        }
                    }

                    if(recordMapFirst && recordMapSecond) {
                        usersChessData.put(recordFirstKey, recordMap.get(recordFirstKey));
                        usersChessData.put(recordSecondKey, recordMap.get(recordSecondKey));

                        responseData.setCode("0000");
                        responseData.setStatus("success");
                        responseData.setMessage("fight");
                        responseData.setDetail("init-fight two record");

                        for(int usersArrIndex = 0; usersArrIndex<userNames.size(); usersArrIndex++) {
                            UserGridInfo userGridInfo = new UserGridInfo();
                            userGridInfo.setName(userNames.get(usersArrIndex));
                            userGridInfo.setGridIndex(1);
                            userGridInfo.setColor("blue");
                            usersArray[usersArrIndex] = userNames.get(usersArrIndex);
                            userGridInfoArray[usersArrIndex] = userGridInfo;
                            userNamesList.add(userNames.get(usersArrIndex));
                        }

                        resultCompared = chessDataCompute.getComparedResult(userNamesList, roomId, roundIndex, usersChessData);
                        responseData.setResult(resultCompared);
                    } else {
                        responseData.setCode("0000");
                        responseData.setStatus("success");
                        responseData.setMessage("init-fight 1 record " + username);
                    }

                    String responseDataString = JSONObject.toJSONString(responseData);
                    this.session.getBasicRemote().sendText(responseDataString);

                } else { // 用户已经传递消息
//                    System.out.println("用户已经发送消息");
//                    System.out.println(roundInfoKey);
//                    System.out.println(roundInfoValue);


                    for (int i = 0; i < userNames.size(); i++) {
                        String k = userNames.get(i);
//                        System.out.println(k);
                        roundInfoKey = k + "/" + roomId + "/" + roundIndex;
                        recordMapKeyFlag = recordMap.containsKey(roundInfoKey);
//                        System.out.println("k === " + recordMapKeyFlag);
                        if(i == 0) {
                            recordMapFirst = recordMapKeyFlag;
                            recordFirstKey = roundInfoKey;
                        } else {
                            recordMapSecond = recordMapKeyFlag;
                            recordSecondKey = roundInfoKey;
                        }
                    }

                    if(recordMapFirst && recordMapSecond) {
                        System.out.println("two records");

                        usersChessData.put(recordFirstKey, recordMap.get(recordFirstKey));
                        usersChessData.put(recordSecondKey, recordMap.get(recordSecondKey));

//                        JSONObject messageObject = new JSONObject();
//                        messageObject.put("roundIndex", roundIndex);
//                        messageObject.put("same", 0);
//                        System.out.println("usernameFirst === " + usernames.get(0));
//                        System.out.println("usernameSecond === " + usernames.get(1));
//                        messageObject.put(usernames.get(0), "red");
//                        messageObject.put(usernames.get(1), "blue");
//
//                        String messageObjectString  = messageObject.toJSONString();




                        for(int usersArrIndex = 0; usersArrIndex<userNames.size(); usersArrIndex++) {
                            UserGridInfo userGridInfo = new UserGridInfo();
                            userGridInfo.setName(userNames.get(usersArrIndex));
                            userGridInfo.setGridIndex(1);
                            userGridInfo.setColor("blue");
                            usersArray[usersArrIndex] = userNames.get(usersArrIndex);
                            userGridInfoArray[usersArrIndex] = userGridInfo;
                            userNamesList.add(userNames.get(usersArrIndex));
                        }

                        resultCompared = chessDataCompute.getComparedResult(userNamesList, roomId, roundIndex, usersChessData);

                        roundData.setUsers(usersArray);
                        roundData.setResult(userGridInfoArray);
                        String roundDataString = JSONObject.toJSONString(roundData);
                        JSONObject roundDataObject = JSONObject.parseObject(roundDataString);

//                        messageObject.put("roundData", roundDataObject);

                        responseData.setCode("0000");
                        responseData.setStatus("success");
                        responseData.setMessage("fight");
                        responseData.setResult(resultCompared);

                        String responseDataString = JSONObject.toJSONString(responseData);
//                        String messageObjectString  = messageObject.toJSONString();

//                        this.session.getBasicRemote().sendText(messageObjectString);

                        this.session.getBasicRemote().sendText(responseDataString);
                    } else {
//                        System.out.println("1 records");
                        responseData.setCode("0001");
                        responseData.setStatus("success");
                        responseData.setMessage("fight");
                        responseData.setDetail("not first wait another user ");
                        String responseDataString = JSONObject.toJSONString(responseData);
                        this.session.getBasicRemote().sendText(responseDataString);
                    }

//                    String usernameFirst = usernames[0];
//                    String usernameFirstRoundInfoKey = usernameFirst + "/" + roomId + "/" + roundIndex;
//                    String usernameSecond = usernames[1];
//                    String usernameSecondRoundInfoKey = usernameSecond + "/" + roomId + "/" + roundIndex;
//
//                    if(recordMap.get(usernameFirstRoundInfoKey).equals("exist") && recordMap.get(usernameSecondRoundInfoKey).equals("exist")) {

//

//                    }


                }
            } else if(obj.get("flag").toString().equals("exit-room")){

                roomId = obj.get("roomId").toString();
                //将用户从聊天室中移除
                int f2 = 1;
                roomList.get(roomId).remove(obj.get("username").toString());

            } if(obj.get("flag").toString().equals("current-user")) { // 判断某个房间内的人数
                JSONObject roomInfoJson = new JSONObject();
                roomId = obj.get("roomId").toString();
                ConcurrentHashMap<String, Socket> roomListMap = roomList.get(roomId);
                Set userSet = new HashSet();
                for (Map.Entry<String, Socket> entry : roomListMap.entrySet()) {
//                    System.out.println("user getKey " + entry.getKey());
                    userSet.add(entry.getKey());
                }
//                System.out.println("userSet.size == " + userSet.size());

                if(userSet.size() == 1) {
                    roomInfoJson.put("users", userSet);
                } else if(userSet.size() == 2){
                    roomInfoJson.put("users", userSet);
                }

                responseData.setMessage("userOnline");
                responseData.setCode("0000");
                responseData.setStatus("success");
                responseData.setDetail("");
                responseData.setResult(roomInfoJson);
                String responseDataString = JSONObject.toJSONString(responseData);
                this.session.getBasicRemote().sendText(responseDataString);
            } else {

            }
        } else {
            System.out.println("123");
        }

    }
    /*websocket 发生错误时进行调用*/
    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }
    public void sendMessage(String message) throws IOException {
        for (Socket socket : webSocketSet) {
            socket.session.getBasicRemote().sendText(message);
        }
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }



}