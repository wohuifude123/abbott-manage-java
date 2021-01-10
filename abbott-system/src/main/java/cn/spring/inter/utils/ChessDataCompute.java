package cn.spring.inter.utils;

import cn.spring.inter.bean.GridInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChessDataCompute {
    public Integer getUserStatus() {
        int aa = 123;
        return aa;
    }

    /**
     *
     * @param userNamesList 玩家列表
     * @param roomId 房间ID
     * @param roundIndex 当前回合
     * @param jsonObject 玩家出牌记录
     * @return
     */
    public JSONObject getComparedResult(List userNamesList, String roomId, String roundIndex, JSONObject jsonObject) {

        String usernameFirst = "";
        String usernameSecond = "";
        String userKey = "";
        JSONObject userFirstRecord = new JSONObject();
        JSONObject userSecondRecord = new JSONObject();
        int userFirstCardsNumber = 10;
        int userSecondCardsNumber = 10;

        JSONObject userFirstResult = new JSONObject();
        JSONObject userSecondResult = new JSONObject();

        for(int i=0; i<userNamesList.size(); i++) {
//            System.out.println(userNamesList.get(i));
//            JSONObject userChessData = new JSONObject();
//            System.out.println(Integer.toString(i) + userNamesList.get(i));
            String userComparedString = JSON.toJSONString(jsonObject);

//            System.out.println("userComparedString == " + userComparedString);

//            String userChessData = jsonObject.get(userNamesList.get(i)).toString();

            userKey = userNamesList.get(i) + "/" + roomId + "/" + roundIndex;

            String jsonObjectString = jsonObject.get(userKey).toString();
            JSONObject userChessData = JSON.parseObject(jsonObjectString);

//            JSONObject cards = JSON.parseObject(userChessData.get("cards").toString());

            JSONObject currentCardInfoJSON = userChessData.getJSONObject("currentCardInfo");

            int currentCardsNumber = userChessData.getIntValue("currentCardsNumber");

//            JSONArray entourageCardsObj = JSON.parseArray(cards.get("entourageCards").toString());

//            JSONObject entourageCardsObj = JSON.parseObject(cards.get("entourageCards").toString());

            JSONObject entourageCardsObj = currentCardInfoJSON.getJSONObject("entourageCards");

            JSONObject userResult = userChessData.getJSONObject("result");

//            System.out.println("userResultStr === " + JSON.toJSONString(userResult));


//            System.out.println("entourageCardsObjSize === " + entourageCardsObj.size());
//            String userChessData = JSON.toJSONString(jsonObject);


//            System.out.println("cards === " + userChessData.getString("cards"));
//            userChessArray.add(userChessData);
            if(i == 0) {
                usernameFirst = userNamesList.get(i).toString();
                userFirstRecord = JSON.parseObject(jsonObject.get(userKey).toString());
                userFirstCardsNumber = currentCardsNumber;
                userFirstResult = userResult;

                System.out.println("userFirstCardsNumber == " + userFirstCardsNumber);
            } else if(i == 1) {
                usernameSecond = userNamesList.get(i).toString();
                userSecondRecord = JSON.parseObject(jsonObject.get(userKey).toString());
                userSecondCardsNumber = currentCardsNumber;
                userSecondResult = userResult;
            }
        }

//        System.out.println(JSON.toJSONString(userFirstRecord.getJSONObject("cards").getJSONObject("entourageCards")));
//        System.out.println(JSON.toJSONString(userSecondRecord));

//        JSONObject userFirstRecordJSON = userFirstRecord.getJSONObject("cards").getJSONObject("entourageCards");
//        JSONObject userSecondRecordJSON = userSecondRecord.getJSONObject("cards").getJSONObject("entourageCards");

        JSONObject userFirstRecordJSON = userFirstRecord.getJSONObject("currentCardInfo").getJSONObject("entourageCards");
        JSONObject userSecondRecordJSON = userSecondRecord.getJSONObject("currentCardInfo").getJSONObject("entourageCards");

        Map<String, JSONObject> recordMap = new HashMap<>();

        JSONObject currentFirstRecord;
        JSONObject currentSecondRecord;

        for (Map.Entry<String, Object> entry : userFirstRecordJSON.entrySet()) {
//            System.out.println("first key值="+entry.getKey());
//            System.out.println("对应first key值的value="+entry.getValue());
            currentFirstRecord = JSON.parseObject(entry.getValue().toString());
            currentFirstRecord.put("owner", usernameFirst);
            currentFirstRecord.put("grid", Integer.parseInt(entry.getKey()));
            recordMap.put(entry.getKey(), currentFirstRecord);
        }

        for (Map.Entry<String, Object> entry : userSecondRecordJSON.entrySet()) {
//            System.out.println("second key值="+entry.getKey());
//            System.out.println("对应second key值的value="+entry.getValue());
//            recordMap.put(entry.getKey(), JSON.parseObject(entry.getValue().toString()));

            currentSecondRecord = userSecondRecordJSON.getJSONObject(entry.getKey());
            currentSecondRecord.put("owner", usernameSecond);
            currentSecondRecord.put("grid", Integer.parseInt(entry.getKey()));

            if(recordMap.containsKey(entry.getKey())) { // 占据相同格子
                System.out.println("占据相同格子");
                currentFirstRecord = recordMap.get(entry.getKey());

                if(currentFirstRecord.getIntValue("atk")>currentSecondRecord.getIntValue("atk")) {
//                    System.out.println("1 大 " + JSON.toJSONString(currentFirstRecord));
                } else {
//                    System.out.println("2 大 " + JSON.toJSONString(currentSecondRecord));
                    recordMap.put(entry.getKey(), currentSecondRecord);
                }
            } else { // 占据不同格子
                System.out.println("占据different格子");
                recordMap.put(entry.getKey(), currentSecondRecord);
            }
        }

//        String globalFightDataString = jsonObject.get("globalFightData").toString();
//        System.out.println("globalFightDataString === " + globalFightDataString);

//        JSONObject globalFightData = JSON.parseObject(jsonObject.get("globalFightData").toString());




//        String cards = globalFightData.getString("cards");
//
//        System.out.println("cards === " + cards);
//
//        JSONObject cardsObject = JSON.parseObject(cards);
//
//        JSONArray entourageCards = (JSONArray) cardsObject.get("entourageCards");
//
//        System.out.println("entourageCards === " + entourageCards);
//
//        List<String> entourageCardsList = JSONArray.parseArray(entourageCards.toJSONString(), String.class);
//
//        for(int ei=0; ei<entourageCardsList.size(); ei++){
//
//        }


        JSONArray usersArray = JSONArray.parseArray(JSON.toJSONString(userNamesList));

        JSONObject globalGameData = new JSONObject();
        globalGameData.put("index", 0);
        globalGameData.put("users", usersArray);

//
//        List<GridInfo> arrGridInfo = new ArrayList<>();
//        for(int gi = 0; gi<arrGirdIndex.size(); gi++) {
//            GridInfo gridInfo = new GridInfo();
//            gridInfo.setIndex(arrGirdIndex.get(gi));
//            if(gi == 0) {
//                gridInfo.setUsername(usernameFirst);
//            } else {
//                gridInfo.setUsername(usernameSecond);
//            }
//            arrGridInfo.add(gridInfo);
//        }

        int gridUserNum = 0;
        Set<String> gridUserProperty = new HashSet<>();
        List<Integer> arrGirdIndex = new ArrayList<>();
        List<JSONObject> arrGridInfo = new ArrayList<>();
        for (Map.Entry<String, JSONObject> entry : recordMap.entrySet()) {
            arrGirdIndex.add(Integer.parseInt(entry.getKey()));
            arrGridInfo.add(entry.getValue());
//            arrGirdIndex.add(2);
//            arrGirdIndex.add(3);
//            System.out.println("compare entry.getKey == " + Integer.parseInt(entry.getKey()));

            System.out.println("owner === " + entry.getValue().getString("owner"));
            gridUserProperty.add(entry.getValue().getString("owner"));
            gridUserNum = gridUserNum + 1;
        }
        globalGameData.put("gridsInfo", arrGridInfo);
        System.out.println(gridUserProperty.size() + "/gridUserNum == " + gridUserNum);

        if(gridUserProperty.size() == 1 && gridUserNum == 9) {
            String winnerName = "";

            String[] winnerArr = gridUserProperty.toArray(new String[0]);
            for (String string : gridUserProperty) {
//                System.out.println(string);
            }
            System.out.println("winnerArr == " + winnerArr[0]);
            globalGameData.put("winner", winnerArr[0]);
        } else if(userFirstCardsNumber == 0 && userSecondCardsNumber == 0){
            System.out.println("双方的卡牌已经出完了 == ");
            System.out.println(JSON.toJSONString(userFirstResult));



            for (Map.Entry<String, JSONObject> entry : recordMap.entrySet()) {

//                System.out.println(entry.getKey() + "/" + entry.getValue().toString());

//                JSON.parseObject(entry.getValue().toString());
                userFirstResult.put(entry.getKey(), entry.getValue());
            }

            System.out.println("userFirstResult === " + userFirstResult);

            int ownerFirstCounter = 0;
            int ownerSecondCounter = 0;

            for (Map.Entry<String, Object> entry : userFirstResult.entrySet()) {
                String ownerResult = JSONObject.parseObject(entry.getValue().toString()).getString("owner");
                if(ownerResult.equals(usernameFirst)) {
                    ownerFirstCounter = ownerFirstCounter + 1;
                } else if (ownerResult.equals(usernameSecond)) {
                    ownerSecondCounter = ownerSecondCounter + 1;
                }
            }

//            System.out.println("ownerFirstCounter == " + ownerFirstCounter);
//            System.out.println("ownerSecondCounter == " + ownerSecondCounter);
            if(ownerFirstCounter == ownerSecondCounter) {
                globalGameData.put("winner", "game-draw");
            } else if(ownerFirstCounter > ownerSecondCounter){
                globalGameData.put("winner", usernameFirst);
            } else {
                globalGameData.put("winner", usernameSecond);
            }
        } else {

        }



        return globalGameData;
    }
}
