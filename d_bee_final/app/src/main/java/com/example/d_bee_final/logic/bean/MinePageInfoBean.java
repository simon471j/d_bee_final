package com.example.d_bee_final.logic.bean;

public class MinePageInfoBean {

    private Data data;
    private int errorCode;
    private String errorMsg;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "MinePageBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
    public class Data {

        private int coinCount;
        private int level;
        private String nickname;
        private String rank;
        private long userId;
        private String username;

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getCoinCount() {
            return coinCount;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return rank;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "coinCount=" + coinCount +
                    ", level=" + level +
                    ", nickname='" + nickname + '\'' +
                    ", rank='" + rank + '\'' +
                    ", userId=" + userId +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}