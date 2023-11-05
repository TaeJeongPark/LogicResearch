package com.example.logicresearch.picks.easymovement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.example.logicresearch.picks.easymovement
 * fileName       : EasyMovement
 * author         : TaeJeongPark
 * date           : 2023-11-05
 * description    : 간편 이동 로직 연구
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-05        TaeJeongPark       최초 생성
 */
public class EasyMovement {

    public static void main(String[] args) {

        // 기존의 배송지 목록
        List<Map<String, Object>> shippingList = new ArrayList<>();
        shippingList.add(new HashMap<String, Object>() {{
            put("id", 6);
            put("sequence", 1);
        }});

        shippingList.add(new HashMap<String, Object>() {{
            put("id", 7);
            put("sequence", 2);
        }});

        shippingList.add(new HashMap<String, Object>() {{
            put("id", 8);
            put("sequence", 3);
        }});

        shippingList.add(new HashMap<String, Object>() {{
            put("id", 9);
            put("sequence", 4);
        }});

        shippingList.add(new HashMap<String, Object>() {{
            put("id", 10);
            put("sequence", 5);
        }});

        shippingList.add(new HashMap<String, Object>() {{
            put("id", 11);
            put("sequence", 6);
        }});

        shippingList.add(new HashMap<String, Object>() {{
            put("id", 12);
            put("sequence", 7);
        }});

        System.out.println("==========> 기존 배송지 목록 : " + shippingList);

        // 변경할 배송지 리스트
        List<Map<String, Object>> changeList = new ArrayList<>();

        changeList.add(new HashMap<String, Object>() {{
            put("id", 6);
            put("sequence", 2);
        }});

        changeList.add(new HashMap<String, Object>() {{
            put("id", 7);
            put("sequence", 3);
        }});

        changeList.add(new HashMap<String, Object>() {{
            put("id", 8);
            put("sequence", 4);
        }});

        System.out.println("==========> 변경할 배송지 리스트 : " + changeList);

        int changeNum = 3;   // 간편이동 할 번호

        if(changeNum == shippingList.size()) changeNum--;   // 간편이동 할 번호가 배송지 목록의 크기와 같을 경우 간편이동 할 번호를 1 감소

        int changeNumCnt = changeNum - 1;   // 간편이동 할 번호 인덱싱
        int maintainNum = 1; // 유지할 번호 인덱싱

        // 배송지 순서 변경 로직
        for(Map<String, Object> shippingListItem : shippingList) {
            shippingListItem.replace("sequence", 0);
        }
        for(Map<String, Object> changeListItem : changeList) {
            for(Map<String, Object> shippingListItem : shippingList) {
                if(changeListItem.get("id").equals(shippingListItem.get("id"))) {
                    shippingListItem.replace("sequence", changeNumCnt);
                    changeNumCnt++;
                }
            }
        }
        System.out.println("==========> 1차 변경된 배송지 목록 : " + shippingList);
        for(Map<String, Object> shippingListItem : shippingList) {
            if(shippingListItem.get("sequence").equals(0)) {
                shippingListItem.replace("sequence", maintainNum);
                maintainNum++;
                if(maintainNum == (changeNum - 1)) {
                    maintainNum += (changeList.size());
                }
            }
        }

        System.out.println("==========> 2차 변경된 배송지 목록 : " + shippingList);

    }

}
