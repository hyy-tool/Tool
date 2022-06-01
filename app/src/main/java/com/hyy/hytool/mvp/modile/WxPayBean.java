package com.hyy.hytool.mvp.modile;

import com.google.gson.annotations.SerializedName;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.modile
 */
public class WxPayBean {
    /**
     * code : 200
     * msg :
     * data : {"appid":"wxc00a34aa118bafe4","partnerid":"1596911841","prepayid":"wx04145520812483986b718d371771077100","package":"Sign=WXPay","noncestr":"nRWSNHsmUiVac6yEeL3kbXrv8Qtg1OCA","timestamp":"1591253720","sign":"A89D66A43C7BBAEE6CC12E479E4080CE"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appid : wxc00a34aa118bafe4
         * partnerid : 1596911841
         * prepayid : wx04145520812483986b718d371771077100
         * package : Sign=WXPay
         * noncestr : nRWSNHsmUiVac6yEeL3kbXrv8Qtg1OCA
         * timestamp : 1591253720
         * sign : A89D66A43C7BBAEE6CC12E479E4080CE
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
