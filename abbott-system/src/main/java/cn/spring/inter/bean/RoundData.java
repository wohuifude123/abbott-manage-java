package cn.spring.inter.bean;

import java.util.ArrayList;

public class RoundData {
    private int index;
    private String[] users;
    private boolean isDiff;
    private UserGridInfo[] result;

    public RoundData() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public boolean isDiff() {
        return isDiff;
    }

    public void setDiff(boolean diff) {
        isDiff = diff;
    }

    public UserGridInfo[] getResult() {
        return result;
    }

    public void setResult(UserGridInfo[] result) {
        this.result = result;
    }
}
